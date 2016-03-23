package com.ewing.core.job;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2015年2月28日
 * 
 */
class JobException extends Exception {

    public JobException() {
        super();
    }

    public JobException(String message) {
        super(message);
    }

    public JobException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobException(Throwable cause) {
        super(cause);
    }
}
