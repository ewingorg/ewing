package com.ewing.busi.order.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.contant.OrderStatus;
import com.ewing.busi.order.contant.ProcessHistoryType;
import com.ewing.busi.order.contant.RefundStatus;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.order.model.OrderProcessHistory;
import com.ewing.busi.order.model.OrderRefund;
import com.ewing.core.jdbc.BaseDao;

/**
 * 订单流程历史DAO
 * 
 * @author tansonlam
 * @createDate 2016年4月15日
 * 
 */
@Repository("orderProcessHistoryDao")
public class OrderProcessHistoryDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 增加退款历史记录
     * 
     * @param orderRefund
     * @param refundStatus
     */
    public void addRefundHistory(OrderRefund orderRefund, RefundStatus refundStatus) {
        OrderProcessHistory history = new OrderProcessHistory();
        history.setBizId(orderRefund.getBizId());
        history.setBusiId(orderRefund.getId());
        history.setBusiType(ProcessHistoryType.REFUND.getValue());
        history.setCustomerId(orderRefund.getCustomerId());
        history.setUserId(orderRefund.getUserId());
        history.setStatus(refundStatus.getValue());
        history.setStatusString(refundStatus.getMsg());
        baseDao.save(history);
    }

    /**
     * 增加订单历史记录
     * 
     * @param orderRefund
     * @param refundStatus
     */
    public void addOrderHistory(OrderInfo orderInfo, OrderStatus orderStatus) {
        OrderProcessHistory history = new OrderProcessHistory();
        history.setBizId(orderInfo.getBizId());
        history.setBusiId(orderInfo.getId());
        history.setBusiType(ProcessHistoryType.REFUND.getValue());
        history.setCustomerId(orderInfo.getCustomerId());
        history.setUserId(orderInfo.getUserId());
        history.setStatus(orderStatus.getStatus());
        history.setStatusString(orderStatus.getMessage());
        baseDao.save(history);
    }

}
