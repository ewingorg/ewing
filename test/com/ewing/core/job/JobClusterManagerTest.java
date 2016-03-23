package com.ewing.core.job;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年3月23日
 * 
 */
public class JobClusterManagerTest {
    @Test
    public void testStartUp() {
        JobClusterManager.getInstance().startUp();
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
