package com.ewing.core.job.dao;

import java.util.List;

import com.ewing.core.factory.SpringCtx;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.job.model.JobDefine;

/**
 * 定时任务定义表dao类
 * 
 * @author tanson lam
 * @createDate 2015年2月28日
 * 
 */
public class JobDefineDao {

    private static BaseDao baseDao = (BaseDao) SpringCtx.getByBeanName("baseDao");

    /**
     * 显示所有生效的定义任务
     * 
     * @return
     */
    public static List<JobDefine> listEffJob(String cluster) {
        String sql = "cluster='" + cluster + "' and is_enable=1";
        return baseDao.find(sql, JobDefine.class);
    }
}
