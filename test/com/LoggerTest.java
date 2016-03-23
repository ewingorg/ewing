package com;

import org.junit.Test;

public class LoggerTest {
    public static org.apache.log4j.Logger ORDERCHECKJOB = org.apache.log4j.Logger
            .getLogger("orderinfojob");
    @Test
    public void test1() {
        ORDERCHECKJOB.info(1111);
       
    }
}
