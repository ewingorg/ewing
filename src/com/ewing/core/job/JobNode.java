package com.ewing.core.job;

/**
 * 定时作业集群节点
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 * 
 */
interface JobNode {
    /**
     * 节点名称，一般为ip:port
     * 
     * @return
     */
    public String getNodeName();

    /**
     * 节点启动
     */
    public void startUp();

    /**
     * 获取节点角色
     * 
     * @return
     */
    public JobRole getRole();
}
