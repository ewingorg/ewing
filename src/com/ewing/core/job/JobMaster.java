package com.ewing.core.job;

/**
 * 定时作业集群master接口
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 */
interface JobMaster extends JobNode {
  
    /**
     * 切换到slave
     * @return
     */
    public boolean switchOver();
    /**
     * 为表示正常运行，作为集群的master需要定时进行心跳汇报
     * @return
     */
    public boolean heartBeat(); 
    /**
     * 触发配置定时作业
     */
    public void triggerDefineJob();
}
