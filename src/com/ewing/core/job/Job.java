package com.ewing.core.job;

import com.ewing.core.job.model.JobProcessLog;
 

/**
 * 
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 * 
 */
public abstract class Job implements Runnable {

    public abstract String jobName();

    public abstract void execute();

    protected boolean needLog = true;

    private JobProcessLog log;

    @Override
    public void run() {
        JobLogger.info("ScheduleJob[" + jobName() + "] start running now...");

        if (needLog)
            log = JobProcessLogger.logBegin(jobName());
        try {
            execute();
            if (needLog)
                JobProcessLogger.logSuc(log.getId(), "successfully");
        } catch (Throwable e) {
            JobLogger.error(e, "error occur in scheduleJob[" + jobName() + "]");
            if (needLog)
                JobProcessLogger.logErr(log.getId(), e.getMessage());
        }

        JobLogger.info("ScheduleJob[" + jobName() + "] finished!");
    }
}
