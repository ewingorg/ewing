package com.ewing.core.job;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.ewing.core.job.dao.JobVoteDao;
import com.ewing.core.job.model.JobDefine;

/**
 * 定时作业集群master的实现类
 * <ul>
 * <li>启动配置在job_define的定时作业任务</li>
 * <li>定时汇报心跳更新job_vote.update_time, 从而表明该master的节点是正常运行，</li>
 * <li>心跳汇报失败时候则提交角色更新通知到{@link JobClusterManager}，由它负责切换到slave角色</li>
 * </ul>
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 * 
 */
class JobMasterImpl extends Thread implements JobMaster {

    private final long maxFailHeartBeatTime = 3;
    private final long sleepInterval = 10 * 1000;
    private final String cluster;
    private String node;
    private AtomicBoolean isStarted;
    private ScheduledExecutorService jobExecutor;
    private final JobClusterManager jobManger;
    private final List<JobDefine> jobDefineList;

    public JobMasterImpl(final JobClusterManager JobManger, String cluster, String node,
            List<JobDefine> jobDefineList) {
        this.jobManger = JobManger;
        this.cluster = cluster;
        this.isStarted = new AtomicBoolean(false);
        this.node = node;
        this.jobDefineList = jobDefineList;
        super.setName("JobMasterImpl cluster["+cluster+"] ");
    }

    @Override
    public JobRole getRole() {
        return JobRole.MASTER;
    }

    @Override
    public boolean switchOver() {
        try {
            Boolean isLeader = JobVoteDao.isLeader(cluster, node);
            if (!isLeader) {
                jobExecutor.shutdown();
            }
            jobManger.notifyUpdateRole(JobRole.SLAVE);
            return true;
        } catch (Exception e) {
            JobLogger.error(e, "error occur while switchover to slave mode.");
        }
        return false;
    }

    @Override
    public boolean heartBeat() {
        int failheartBeatTime = 0;
        boolean ret = false;
        while (!ret) {
            if (failheartBeatTime >= maxFailHeartBeatTime)
                break;
            ret = JobVoteDao.reportTime(cluster, node);
            if (ret)
                break;
            failheartBeatTime++;
        }
        return ret;
    }

    @Override
    public void triggerDefineJob() {
        if (!isStarted.getAndSet(true)) {
            Date nowDate = new Date();

            jobExecutor = Executors.newScheduledThreadPool(jobDefineList.size());
            Map<String, JobDefine> jobDefineMap = new HashMap<String, JobDefine>();
            for (JobDefine jobDefine : jobDefineList) {
                jobDefineMap.put(jobDefine.getName(), jobDefine);
            }

            for (String jobName : jobDefineMap.keySet()) {
                JobDefine jobDefine = jobDefineMap.get(jobName);
                try {
                    CronExpression cron = new CronExpression(jobDefine.getJobCron());
                    Date delayDate = cron.getNextValidTimeAfter(nowDate);
                    Date intervalDate = cron.getNextValidTimeAfter(delayDate);
                    long intervalTime = intervalDate.getTime() - delayDate.getTime();
                    long delayTime = delayDate.getTime() - System.currentTimeMillis();
                    Class<? extends Job> jobClazz = (Class<? extends Job>) ClassLoaderUtil
                            .loadClass(jobDefine.getJobClass());
                    Job jobInstance = jobClazz.newInstance();
                    JobLogger.info("set schedule job[" + jobName + "] cron["
                            + jobDefine.getJobCron() + "] jobClass[" + jobClazz.getName()
                            + "] delay[" + delayTime + "] intervalTime[" + intervalTime + "]");
                    jobExecutor.scheduleAtFixedRate(jobInstance, delayTime, intervalTime,
                            TimeUnit.MILLISECONDS);
                } catch (ParseException e) {
                    JobLogger.error(e, "error in parse the job's cron configuration");
                } catch (ClassNotFoundException e) {
                    JobLogger.error(e, "error in find class[" + jobDefine.getJobClass() + "]");
                } catch (InstantiationException e) {
                    JobLogger
                            .error(e, "error in initialize class[" + jobDefine.getJobClass() + "]");
                } catch (IllegalAccessException e) {
                    JobLogger
                            .error(e, "error in initialize class[" + jobDefine.getJobClass() + "]");
                }
            }
        }
    }

    public void run() {
        while (true) {
            try {
                // 1.心跳汇报，更新job_vote的update_time
                boolean isSuccess = heartBeat();
                // 2.汇报失败则切换成slave
                if (!isSuccess) {
                    switchOver();
                    break;
                } else {
                    // 3.如是首次启动则激活配置的定时任务
                    triggerDefineJob();
                }
            } catch (Exception e1) {
                JobLogger.error(e1, e1.getMessage());
            }

            try {
                this.sleep(sleepInterval);
            } catch (InterruptedException e) {
                JobLogger.error(e, e.getMessage());
            }
        }
    }

    @Override
    public String getNodeName() {
        return node;
    }

    @Override
    public void startUp() {
        this.start();
    }
}
