package com.ewing.busi.pay.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dao.OrderDao;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.pay.contant.PayBusiType;
import com.ewing.busi.pay.contant.PayStatus;
import com.ewing.busi.pay.contant.PayWay;
import com.ewing.busi.pay.model.PayHistory;
import com.ewing.common.exception.OrderException;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.BizGenerator;

/**
 * 支付历史服务类
 * 
 * @author tansonlam
 * @createDate 2016年4月7日
 * 
 */
@Repository("payHistoryService")
public class PayHistoryService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private BaseDao baseDao;
    @Resource
    private OrderDetailDao orderDetailDao;

    /**
     * 
     * @param orderId
     * @return
     * @throws OrderException
     */
    public Boolean addRefundHistory(Integer userId, Integer orderDetailId, PayWay payWay)
            throws OrderException {
        OrderDetail orderInfo = orderDetailDao.findDetail(userId, orderDetailId);
        if (orderInfo == null)
            throw new OrderException("没有匹配的订单");
        PayHistory payHistory = new PayHistory();
        payHistory.setBizId(BizGenerator.generateBizNum());
        payHistory.setBusiType(PayBusiType.REFUND.getValue());
        payHistory.setBusiId(orderInfo.getId());
        payHistory.setPayFee(orderInfo.getTotalPrice());
        payHistory.setStatus(PayStatus.INIT.getValue());
        payHistory.setPayWay(payWay.getValue());
        payHistory.setCustomerId(orderInfo.getCustomerId());
        payHistory.setUserId(orderInfo.getUserId());
        baseDao.save(payHistory);
        return true;
    }
}
