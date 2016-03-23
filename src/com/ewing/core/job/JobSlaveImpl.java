package com.ewing.core.job;

import java.io.IOException;

import org.apache.commons.net.telnet.TelnetClient;

import com.ewing.core.job.dao.JobVoteDao;
import com.ewing.core.job.model.JobVote;

/**
 * 定时作业集群slave的实现类.
 * <ul>
 * <li>集群作业所有节点都会以slave角色进行启动</li>
 * <li>获取集群中master的信息，检查是否需要重新投票竞争master,通过touch到master是否正常或者检查当前master的job_vote.update_time的时间是否超时， 任何一个条件不正常都让slave发起竞争成为master角色</li>
 * <li>当slave节点竞争成为master后则发起角色切换通知到{@link JobClusterManager},让它来切换本节点到master角色</li>
 * <li>当slave节点竞争失败后则获取最新的master信息，继续touch到master</li>
 * </ul>
 * 
 * @author tanson lam
 * @createDate 2015年2月28日
 * 
 */
class JobSlaveImpl extends Thread implements JobSlave {
    private final long masterTimeOut = 5 * 60 * 1000;
    private final int maxFailTouchMater = 3;
    private final long sleepInterval = 10 * 1000;
    private final String cluster;
    private String node;
    private long masterLastUpdate;
    private String masterNode;
    private final JobClusterManager jobManger;

    public JobSlaveImpl(final JobClusterManager JobManger, String cluster, String node) {
        this.jobManger = JobManger;
        this.cluster = cluster;
        this.node = node;
        super.setName("JobSlaveImpl cluster[" + cluster + "] ");
    }

    @Override
    public String getNodeName() {
        return node;
    }

    @Override
    public JobRole getRole() {
        return JobRole.SLAVE;
    }

    @Override
    public boolean switchOver() {
        jobManger.notifyUpdateRole(JobRole.MASTER);
        return true;
    }

    @Override
    public boolean hasMaster() {
        JobVote master = JobVoteDao.getMaster(cluster);
        if (master == null)
            return false;
        masterLastUpdate = master.getUpdateTime();
        long intervalTime = System.currentTimeMillis() - masterLastUpdate;
        boolean isTimeOut = intervalTime < masterTimeOut;
        JobLogger.info("master node[" + masterNode + "] test timeout=" + !isTimeOut);
        return isTimeOut;
    }

    @Override
    public boolean compete() {
        return JobVoteDao.compete(cluster, node, masterNode);
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 1.获取master节点信息.
                if (masterNode == null)
                    masterNode = JobVoteDao.getMaster(cluster).getNode();
                JobLogger.info("current[" + jobManger.getRole() + "] master node[" + masterNode
                        + "]");
                // 2.判断网络检测master节点是否存在或者汇报超时
                if (!touchMaster() || !hasMaster()) {
                    JobLogger.info("compete for leader node");
                    // 3.竞争成为master
                    boolean isSuc = compete();
                    JobLogger.info("compete result=" + isSuc);
                    if (isSuc) {
                        // 4.如竞争成功为 leader则进行切换通知
                        switchOver();
                        break;
                    } else {
                        // 5.如竞争失败则获取新的master节点信息
                        masterNode = JobVoteDao.getMaster(cluster).getNode();
                        JobLogger.info("get new master node[" + masterNode + "]");
                    }
                }
            } catch (Throwable e) {
                JobLogger.error(e, e.getMessage());
            }

            try {
                this.sleep(sleepInterval);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void startUp() {
        this.start();
    }

    @Override
    public boolean touchMaster() {
        boolean isAlive = false;
        int touchNum = 0;

        while (!isAlive) {
            try {
                if (touchNum >= maxFailTouchMater)
                    break;
                isAlive = touchMaster(masterNode);
                if (isAlive)
                    break;
                touchNum++;
                this.sleep(100);
            } catch (InterruptedException e) {

            }
        }

        return isAlive;
    }

    private boolean touchMaster(String node) {
        TelnetClient client = new TelnetClient();
        try {
            // 如果当前节点和master节点信息，则返回false.标识自已不能touch自已
            if (this.node.equals(node)) {
                JobLogger.info("not allow to touch master for the same node");
                return false;
            }
            String ip = node.split(":")[0];
            Integer port = Integer.valueOf(node.split(":")[1]);
            client.setReaderThread(true);
            client.setConnectTimeout(2000);
            client.connect(ip, port);
            JobLogger.info("successfully to touch master node[" + masterNode + "],currentNode["+this.node+"]");
            return true;
        } catch (Exception e) {
            JobLogger.warn("fail to touch master node[" + masterNode + "]");
            return false;
        } finally { 
            try {
                client.disconnect();
            } catch (IOException e) {

            }
        }
    }
}
