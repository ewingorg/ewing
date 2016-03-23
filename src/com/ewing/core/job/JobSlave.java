package com.ewing.core.job;

/**
 *  定时作业集群slave接口
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 * 
 */
interface JobSlave extends JobNode {
    /**
     * 获取节点角色
     * @return
     */
    public JobRole getRole();
    /**
     * 角色切换
     * @return
     */
    public boolean switchOver();
    /**
     * 检验是否已有master
     * @return
     */
    public boolean hasMaster();
    /**
     * 竞争成为集群的master
     * @return
     */
    public boolean compete();
    /**
     * touch到master，检查是否正常运行
     * @return
     */
    public boolean touchMaster();
}
