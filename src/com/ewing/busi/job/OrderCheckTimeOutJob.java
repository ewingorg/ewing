package com.ewing.busi.job;

import static com.ewing.logger.LoggerManager.ordercheckjoblogger;
import com.ewing.busi.order.service.OrderService;
import com.ewing.core.factory.SpringCtx;
import com.ewing.core.job.Job;

/**
 * 订单检查定时超时未付款作业。
 * 
 * @author tansonlam
 * @createDate 2016年3月23日
 * 
 */
public class OrderCheckTimeOutJob extends Job {

    private OrderService orderService = (OrderService) SpringCtx.getByBeanName("orderService");
    private final Integer maxNoPayTimeOut = 24 * 60 * 3600;
    private final Integer maxNoConfirmTimeOut = 15 * 24 * 60 * 3600;

    public OrderCheckTimeOutJob() {
        super.needLog = false;
    }

    @Override
    public String jobName() {
        return "ResourceStoreChecker";
    }

    @Override
    public void execute() {
        try {
            orderService.processTimeOutOrder(maxNoPayTimeOut);
            orderService.processNoconfirmOrder(maxNoConfirmTimeOut);
        } catch (Exception e) {
            ordercheckjoblogger.error(e.getMessage(), e);
        }
    }
}