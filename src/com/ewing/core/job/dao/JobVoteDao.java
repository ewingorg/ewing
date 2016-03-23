package com.ewing.core.job.dao;

import java.util.Date;
import java.util.List;

import com.ewing.core.factory.SpringCtx;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.job.JobLogger;
import com.ewing.core.job.model.JobVote;

/**
 * 定时作业进程投票选举dao类
 * 
 * @author tanson lam
 * @createDate 2015年2月28日
 * 
 */
public class JobVoteDao {
    private static BaseDao baseDao = (BaseDao) SpringCtx.getByBeanName("baseDao");

    /**
     * 检验当前节点是否集群的leader.
     * 
     * @param cluster 集群名称
     * @param node 节点名称
     * @return
     */
    public static boolean isLeader(String cluster, String node) {
        List<JobVote> list = findVote(cluster);
        if (list != null && list.get(0) != null) {
            JobVote jobVote = list.get(0);
            if (jobVote.getNode().equals(node))
                return true;
        }
        return false;
    }

    private static List<JobVote> findVote(String cluster) {
        String sql = "cluster ='" + cluster + "'";
        return baseDao.find(sql, JobVote.class);

    }

    /**
     * 检验当前节点是否集群的leader.
     * 
     * @param cluster 集群名称
     * @return
     */
    public static JobVote getMaster(String cluster) {

        try {
            List<JobVote> list = findVote(cluster);
            if (list != null && list.get(0) != null) {
                return list.get(0);
            }
        } catch (Exception e) {
            JobLogger.error("fail to get Master for cluster[" + cluster + "]");
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 集群的leader心跳汇报.
     * 
     * @param cluster 集群名称
     * @param node 节点名称
     * @return
     */
    public static boolean reportTime(String cluster, String node) {
        String sql = "update job_vote set update_time = '" + new Date().getTime()
                + "' where cluster='" + cluster + "' and node = '" + node + "'";
        int ret = baseDao.executeSql(sql);
        return ret > 0;
    }

    /**
     * 节点竞争成为master.
     * 
     * @param cluster 集群名称
     * @param node 节点名称
     * @param previousMasterNode 上次的节点名称
     * @return
     */
    public static boolean compete(String cluster, String node, String previousMasterNode) {

        String sql = "update job_vote set node = '" + node + "' ,update_time='"
                + new Date().getTime() + "' where cluster='" + cluster + "' and node = '"
                + previousMasterNode + "'";
        int ret = baseDao.executeSql(sql);
        return ret > 0;
    }
}
