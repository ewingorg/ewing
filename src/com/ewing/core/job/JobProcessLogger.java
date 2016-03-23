package com.ewing.core.job;

import com.ewing.core.job.dao.JobProcessLogDao;
import com.ewing.core.job.model.JobProcessLog;

/**
 * 定时作业执行日志记录
 * 
 * @author tanson lam
 * @createDate 2015年3月2日
 * 
 */
public class JobProcessLogger {
    /**
     * 任务开始记录日志
     * 
     * @param name 任务名称
     */
    public static JobProcessLog logBegin(String name) {
        return JobProcessLogDao.logBegin(name);
    }

    /**
     * 任务出错时记录日志
     * 
     * @param logId 日志主键id
     * @param message 执行结果信息
     */
    public static void logErr(Integer logId, String message) {
        JobProcessLogDao.logErr(logId, message);
    }

    /**
     * 任务成功执行后记录日志
     * 
     * @param logId 日志主键id
     * @param message 执行结果信息
     */
    public static void logSuc(Integer logId, String message) {
        JobProcessLogDao.logSuc(logId, message);
    }
}