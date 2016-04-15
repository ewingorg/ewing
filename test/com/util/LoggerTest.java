package com.util;


import org.apache.log4j.Logger;
import org.junit.Test;

import com.ewing.core.app.action.base.BaseAction;

public class LoggerTest {
    public static org.apache.log4j.Logger ORDERCHECKJOB = org.apache.log4j.Logger
            .getLogger("orderinfojob");
    private static Logger logger = Logger.getLogger(BaseAction.class);
    @Test
    public void test1() {
        /*ORDERCHECKJOB.info(1111);
        ORDERCHECKJOB.error(2222);*/
        logger.info(1111);
        logger.error(222);
    }
}
