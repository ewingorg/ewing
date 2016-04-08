package com.ewing.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.order.dto.ExpressRespDto;
import com.ewing.busi.order.dto.OrderRefundDto;
import com.ewing.busi.order.service.OrderRefundService;
import com.ewing.busi.order.service.OrderService;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;

/**
 * 订单
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
public class OrderRefundAction extends BaseAction {
    private static Logger logger = Logger.getLogger(OrderRefundAction.class);
    @Resource
    private OrderService orderService;
    @Resource
    private OrderRefundService orderRefundService;

    private static final String ORDER_SEND_PAGE = "/admin/order/orderrefund.html";

    /**
     * 显示退款表单
     */
    public void showRefundForm() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        try {
            Integer orderDetailId = getIntegerParameter("orderDetailId");
            OrderRefundDto orderRefund = orderRefundService.findRefund(getLoginUserId(),
                    orderDetailId);
            ExpressRespDto expressRespDto = orderRefundService.queryRefundCargoInfo(
                    getLoginUserId(), orderDetailId);
            dataModel.put("expressResp", expressRespDto);
            dataModel.put("orderRefund", orderRefund);
            render(ORDER_SEND_PAGE, dataModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 商户同意退款
     */
    public void sellerArgeeRefund() {
        ResponseData responseData = null;
        try {
            Integer orderDetailId = getIntegerParameter("orderDetailId");
            orderRefundService.sellerArgeeRefund(getLoginUserId(), orderDetailId);
            responseData = ResponseUtils.success("保存成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData = ResponseUtils.fail("保存失败！");
        }
        this.outResult(responseData);
    }

    /**
     * 商户确认收货
     * 
     */
    public void sellerReceiveGood() {
        ResponseData responseData = null;
        try {
            Integer orderDetailId = getIntegerParameter("orderDetailId");
            orderRefundService.sellerArgeeRefund(getLoginUserId(), orderDetailId);
            responseData = ResponseUtils.success("保存成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData = ResponseUtils.fail("保存失败！");
        }
        this.outResult(responseData);
    }

    /**
     * 商户确认退款
     */
    public void sellerRefund() {
        ResponseData responseData = null;
        try {
            Integer orderDetailId = getIntegerParameter("orderDetailId");
            orderRefundService.sellerArgeeRefund(getLoginUserId(), orderDetailId);
            responseData = ResponseUtils.success("保存成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData = ResponseUtils.fail("保存失败！");
        }
        this.outResult(responseData);
    }

}
