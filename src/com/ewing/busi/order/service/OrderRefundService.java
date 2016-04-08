package com.ewing.busi.order.service;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.dao.CustomerDao;
import com.ewing.busi.customer.model.Customer;
import com.ewing.busi.express.service.ExpressApiService;
import com.ewing.busi.order.contant.RefundStatus;
import com.ewing.busi.order.contant.RefundType;
import com.ewing.busi.order.dao.OrderRefundDao;
import com.ewing.busi.order.dto.ExpressRespDto;
import com.ewing.busi.order.dto.OrderRefundDto;
import com.ewing.busi.order.model.OrderRefund;
import com.ewing.busi.pay.service.PayHistoryService;
import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.system.service.SysParamService;
import com.ewing.common.constant.CargoStatus;
import com.ewing.common.constant.SysParamCode;
import com.ewing.common.exception.OrderException;
import com.ewing.core.jdbc.BaseDao;

/**
 * 订单退款服务类
 * 
 * @author tansonlam
 * @createDate 2016年4月5日
 * 
 */
@Repository("orderRefundService")
public class OrderRefundService {
    @Resource
    private OrderRefundDao orderRefundDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private PayHistoryService payHistoryService;
    @Resource
    private BaseDao baseDao;

    /**
     * 查找退款
     * 
     * @param orderDetailId
     * @return
     */
    public OrderRefundDto findRefund(Integer userId, Integer orderDetailId) {
        OrderRefund orderRefund = orderRefundDao.findRefund(userId, orderDetailId);
        if (orderRefund == null)
            return null;
        OrderRefundDto orderRefundDto = new OrderRefundDto();
        try {
            BeanUtils.copyProperties(orderRefundDto, orderRefund);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Customer customer = customerDao.findUserById(orderRefund.getUserId());
        orderRefundDto.setCustomerName(customer.getName());
        orderRefundDto.setStatusStr(RefundStatus.getMsg(orderRefund.getStatus()));
        return orderRefundDto;
    }

    /**
     * 查询退款订单的物流情况
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public ExpressRespDto queryRefundCargoInfo(Integer userId, Integer orderDetailId) {
        ExpressApiService expressApiService = new ExpressApiService();
        OrderRefund order = orderRefundDao.findRefund(userId, orderDetailId);
        if (order == null || StringUtils.isEmpty(order.getCargoName())
                || StringUtils.isEmpty(order.getCargoNumber()))
            return new ExpressRespDto(CargoStatus.NODATA.message);

        SysParam sysParam = sysParamService.findByRootCodeAndParamName(SysParamCode.CARGO_LIST,
                order.getCargoName());
        String com = null != sysParam ? sysParam.getParamValue() : null;
        return new ExpressRespDto(null, expressApiService.request(com, order.getCargoNumber()));
    }

    /**
     * 商户同意退款
     * 
     * @param userId
     * @param orderDetailId
     * @return
     * @throws OrderException
     */
    public Boolean sellerArgeeRefund(Integer userId, Integer orderDetailId) throws OrderException {
        OrderRefund orderRefund = orderRefundDao.findRefund(userId, orderDetailId);
        if (orderRefund == null)
            return false;
        // 如是是仅退款的则直接返回钱给用户
        if (orderRefund.getType() == RefundType.MONEY.getValue()) {
            payHistoryService.addRefundHistory(userId, orderRefund.getOrderId(), null);
        }
        orderRefund.setStatus(RefundStatus.CONFIRMED.getValue());
        baseDao.save(orderRefund);
        return true;
    }

    /**
     * 商户确认收货
     * 
     * @param userId
     * @param orderDetailId
     * @return
     * @throws OrderException
     */
    public Boolean sellerReceiveGood(Integer userId, Integer orderDetailId) throws OrderException {
        OrderRefund orderRefund = orderRefundDao.findRefund(userId, orderDetailId);
        if (orderRefund == null)
            return false;
        orderRefund.setStatus(RefundStatus.RECEIVED.getValue());
        baseDao.save(orderRefund);
        return true;
    }

    /**
     * 商户确认退款
     * 
     * @param userId
     * @param orderDetailId
     * @return
     * @throws OrderException
     */
    public Boolean sellerRefund(Integer userId, Integer orderDetailId) throws OrderException {
        OrderRefund orderRefund = orderRefundDao.findRefund(userId, orderDetailId);
        if (orderRefund == null)
            return false;
        orderRefund.setStatus(RefundStatus.FINISHED.getValue());
        payHistoryService.addRefundHistory(userId, orderRefund.getOrderId(), null);
        baseDao.save(orderRefund);
        return true;
    }
}
