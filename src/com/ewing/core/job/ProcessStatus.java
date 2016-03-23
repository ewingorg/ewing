package com.ewing.core.job;

/**
 * 定时任务作业执行状态
 * 
 * @author tanson lam
 * @createDate 2015年3月2日
 *
 */
public enum ProcessStatus {
    /**
     * 任务开始
     */
    BEGIN(0), 
    /**
     * 任务执行成功
     */
    SUCCESS(1), 
    /**
     * 任务执行失败
     */
    ERROR(2);

    private Integer code;

    private ProcessStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
