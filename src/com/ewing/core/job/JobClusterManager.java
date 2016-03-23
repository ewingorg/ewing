package com.ewing.core.job;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import com.ewing.core.job.dao.JobDefineDao;
import com.ewing.core.job.model.JobDefine;
import com.ewing.util.PropertyUtil;

/**
 * 
 * 定时工作任务管理类。
 * <ul>
 * <li>节点都是以slave角色进行启动,并启动网络服务监听提供slave角色进行间隔touch</li>
 * <li>Slave和Master角色的切换都需要发起通知到本类</li>
 * 
 * 
 * @author tanson lam
 * @createDate 2015年2月28日
 * 
 */
public class JobClusterManager {

    private static AtomicBoolean isStart = new AtomicBoolean(false);
    private JobNode jobNode;
    private String cluster = "billing";
    private ReentrantLock roleChangeLock = new ReentrantLock(false);
    private static SynchronousQueue roleQueue = new SynchronousQueue(false);
    private int jobServerPort = 11100;
    private String node;
    private final static JobClusterManager instance = new JobClusterManager();
    private List<JobDefine> jobDefineList;

    private JobClusterManager() {
        initNetServer();
        String cluster = PropertyUtil.getProperty("job.cluster");
        String networkPrefix = PropertyUtil.getProperty("job.network.prefix");
        String propertyDefine = PropertyUtil.getProperty("job.property.define");
        JobLogger.info("init JobClusterManager cluster:" + cluster + " networkPrefix:"
                + networkPrefix + " propertyDefine:" + propertyDefine);
        try {
            if (propertyDefine.equals("false"))
                jobDefineList = JobDefineDao.listEffJob(cluster);
            else
                jobDefineList = JobPropDefine.getPropDefineJobList();
            if (cluster != null)
                this.cluster = cluster.trim();

            if (networkPrefix == null) {
                node = IpUtil.getLocalIpAddress() + ":" + jobServerPort;
            } else {
                node = IpUtil.getLocalIpAddress(networkPrefix) + ":" + jobServerPort;
            }
        } catch (SocketException e) {
            JobLogger.error(e, "error occur in get local ip address");
        }
    }

    public static JobClusterManager getInstance() {
        return instance;
    }

    public void notifyUpdateRole(JobRole jobRole) {
        try {
            roleQueue.put(jobRole);
        } catch (InterruptedException e) {
            JobLogger.error(e, e.getMessage());
        }
    }

    class ClusterBackground extends Thread {
        private JobClusterManager jobClusterManager;

        public ClusterBackground(JobClusterManager jobClusterManager) {
            this.jobClusterManager = jobClusterManager;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    JobRole jobRole = (JobRole) roleQueue.take();
                    if (jobNode.getRole().equals(jobRole))
                        continue;
                    JobLogger.info("current job node will be updated to role[" + jobRole.name()
                            + "]");
                    roleChangeLock.lock();
                    try {
                        if (jobRole.equals(JobRole.MASTER)) {
                            jobNode = new JobMasterImpl(jobClusterManager, cluster, node,
                                    jobDefineList);
                            jobNode.startUp();
                        } else if (jobRole.equals(JobRole.SLAVE)) {
                            jobNode = new JobSlaveImpl(jobClusterManager, cluster, node);
                            jobNode.startUp();
                        }
                    } finally {
                        roleChangeLock.unlock();
                    }
                    JobLogger.info("current job node successfully updated to role["
                            + jobRole.name() + "]");
                } catch (Throwable e) {
                    JobLogger.error(e, "fail to switchover in distributed job node.");
                    // 当切换成败后，还是保持本节点为slave角色
                    jobNode = new JobSlaveImpl(jobClusterManager, cluster, node);
                    jobNode.startUp();
                }
            }
        }
    }

    public void startUp() {
        if (!isStart.getAndSet(true)) {
            JobLogger.info("JobClusterManager[" + Thread.currentThread().getName()
                    + "] start........................................");
            jobNode = new JobSlaveImpl(this, cluster, node);
            jobNode.startUp();
            new ClusterBackground(this).start();
        }
    }

    private void initNetServer() {
        while (true) {
            try {
                NetServer server = new NetServer(jobServerPort);
                JobLogger.info("job net server launch at port:" + jobServerPort);
                break;
            } catch (IOException e) {
                // do not print IOException.
            }
            jobServerPort++;
        }
    }

    public JobRole getRole() {
        if (jobNode == null)
            return null;
        return jobNode.getRole();
    }
}
