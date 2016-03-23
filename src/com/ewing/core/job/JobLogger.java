package com.ewing.core.job;

import java.util.logging.Level;

import org.apache.log4j.Logger;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2015年3月2日
 * 
 */
public class JobLogger {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger("cluster-job");

    public static Logger getLogger() {
        return logger;
    }

    public static void error(String message) {
        logger.error(Thread.currentThread().getName() + message);
    }

    public static void error(Throwable e, String message, Object... args) {
        logger.error(Thread.currentThread().getName() + message, e);
    }

    public static void info(String message) {
        logger.info(Thread.currentThread().getName() + message);
    }

    public static void warn(String message) {
        logger.warn(Thread.currentThread().getName() + message);
    }

    public static void warn(Throwable e, String message) {
        logger.warn(message, e);
    }

    public static void debug(String message) {
        logger.debug(Thread.currentThread().getName() + message);
    }

    public static void debug(Throwable e, String message) {
        logger.debug(Thread.currentThread().getName() + message, e);
    }

    public static void main(String[] args) {
        info("ddd");
    }
}
