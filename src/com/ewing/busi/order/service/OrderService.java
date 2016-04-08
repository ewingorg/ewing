package com.ewing.busi.order.service;

import static com.ewing.logger.LoggerManager.ordercheckjoblogger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.express.service.ExpressApiService;
import com.ewing.busi.order.contant.OrderStatus;
import com.ewing.busi.order.dao.OrderDao;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.dao.OrderRefundDao;
import com.ewing.busi.order.dto.ExpressRespDto;
import com.ewing.busi.order.dto.OrderDetailDto;
import com.ewing.busi.order.dto.OrderInfoComplex;
import com.ewing.busi.order.dto.OrderInfoDto;
import com.ewing.busi.order.dto.OrderQueryReq;
import com.ewing.busi.order.helper.OrderHelper;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.system.service.SysParamService;
import com.ewing.common.constant.CargoStatus;
import com.ewing.common.constant.NeedStatus;
import com.ewing.common.constant.SysParamCode;
import com.ewing.common.exception.OrderException;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

@Repository("orderService")
public class OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private BaseDao baseDao;
    @Resource
    private OrderDetailDao orderDetailDao;
    @Resource
    private WebResourceDao webResourceDao;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private OrderRefundDao orderRefundDao;

    /**
     * 查询
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public OrderInfo findOneOrder(Integer userId, Integer orderId) {
        return orderDao.findOne(userId, orderId);
    }

    /**
     * 更新订单为已经发货
     * 
     * @param userId
     * @param orderId
     * @param needCargo
     * @param cargoName
     * @param cargoNumber
     * @throws OrderException
     */
    public void update2Send(Integer userId, Integer orderId, Integer needCargo, String cargoName,
            String cargoNumber) throws OrderException {
        OrderInfo orderInfo = orderDao.findOne(userId, orderId);
        if (orderInfo == null)
            throw new OrderException("没有找到匹配的订单");

        if (NeedStatus.NEED.getStatus().equals(needCargo)) {
            if (StringUtils.isEmpty(cargoNumber) || StringUtils.isEmpty(cargoName))
                throw new OrderException("物流相关信息不能为空！");
        }
        if (!OrderStatus.WAIT_SEND.getStatus().equals(orderInfo.getStatus())) {
            throw new OrderException("订单不是等待发货状态！");
        }
        // 更新订单的状态为等待收货，记录物流公司、物流编号
        orderInfo.setStatus(OrderStatus.WAIT_RECEIVE.getStatus());
        orderInfo.setCargoTime(new Date());
        orderInfo.setCargoName(cargoName == null ? "" : cargoName);
        orderInfo.setCargoNumber(cargoNumber == null ? "" : cargoNumber);
        orderInfo.setNeedCargo(needCargo);
        baseDao.update(orderInfo);

        // 更新订单详情的状态为等待收货，过滤状态不是等待发货的订单详情
        List<OrderDetail> detailList = orderDetailDao.findDetailList(orderId);
        for (OrderDetail orderDetail : detailList) {
            if (OrderStatus.WAIT_SEND.getStatus().equals(orderDetail.getStatus())) {
                orderDetail.setStatus(OrderStatus.WAIT_RECEIVE.getStatus());
                baseDao.update(orderDetail);
            }
        }
    }

    /**
     * 查找订单
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public OrderInfoComplex findOneComplexOrder(Integer userId, Integer orderId) {
        OrderInfoDto orderInfo = orderDao.findOneMoreInfo(userId, orderId);
        List<OrderDetailDto> orderDetailList = orderDetailDao
                .findDetailDtoList(new Integer[] { orderInfo.getId() });
        OrderInfoComplex orderInfoComplex = new OrderInfoComplex();
        orderInfoComplex.setOrderInfo(orderInfo);
        List<OrderDetailDto> detailList = new ArrayList<OrderDetailDto>();
        for (OrderDetailDto detailDto : orderDetailList) {
            if (detailDto.getOrderId() == orderInfo.getId()) {
                detailList.add(detailDto);
            }
        }
        orderInfoComplex.setOrderDetailList(detailList);
        return orderInfoComplex;
    }

    /**
     * 分页查询订单
     * 
     * @param userId
     * @param orderQueryReq
     * @return
     */
    public PageBean<OrderInfoComplex> pageQueryOrder(Integer userId, OrderQueryReq orderQueryReq) {
        PageBean<OrderInfoDto> orderPageBean = orderDao.pageQueryOrder(userId, orderQueryReq);
        List<OrderInfoDto> orderList = orderPageBean.getResult();
        if (orderList.isEmpty())
            return new PageBean<OrderInfoComplex>();

        List<Integer> orderIds = new ArrayList<Integer>();
        for (OrderInfoDto orderInfo : orderList) {
            orderIds.add(orderInfo.getId());
        }
        // 查找订单详情
        List<OrderDetailDto> orderDetailList = orderDetailDao.findDetailDtoList(orderIds
                .toArray(new Integer[orderIds.size()]));

        // 重新组装每张订单，以及归属的订单详情
        List<OrderInfoComplex> complexList = new ArrayList<OrderInfoComplex>();
        for (OrderInfoDto orderInfo : orderList) {
            OrderInfoComplex orderInfoComplex = new OrderInfoComplex();
            orderInfoComplex.setOrderInfo(orderInfo);
            List<OrderDetailDto> detailList = new ArrayList<OrderDetailDto>();
            // 匹配对应的订单详情，以及如果指定订单状态则继续匹配
            for (OrderDetailDto detailDto : orderDetailList) {
                if (detailDto.getOrderId() == orderInfo.getId()
                        && (!StringUtils.isEmpty(orderQueryReq.getStatus()))
                        && orderQueryReq.getStatus().equals(detailDto.getStatus())) {
                    detailList.add(detailDto);
                }
            }
            orderInfoComplex.setOrderDetailList(detailList);
            complexList.add(orderInfoComplex);
        }
        return new PageBean<OrderInfoComplex>(orderPageBean.getPage(),
                orderPageBean.getTotalCount(), orderPageBean.getPageSize(), complexList);
    }

    /**
     * 处理已经超时的订单信息
     * 
     * @param maxTimeOut
     * @return
     * @throws Exception
     */
    public void processTimeOutOrder(Integer maxTimeOut) throws Exception {
        // 1.查询超时没有付款的记录
        List<OrderInfo> orderInfoList = orderDao.findTimeOutOrder(maxTimeOut);
        ordercheckjoblogger.info("timeout orderInfo size:" + orderInfoList.size());
        for (OrderInfo orderInfo : orderInfoList) {
            ordercheckjoblogger.info("process orderInfo id[" + orderInfo.getId() + "]");
            try {
                List<OrderDetailDto> orderDetailList = orderDetailDao.findDetailDtoList(orderInfo
                        .getId());
                if (orderDetailList.isEmpty())
                    continue;
                // 2.把待付款的订单更改为“关闭”状态
                orderDao.updateTimeOutOrder2Close(orderInfo.getId());
                // 3.恢复商品的库存数
                for (OrderDetailDto orderDetail : orderDetailList) {
                    webResourceDao.recoverStockNum(orderDetail.getResourceId(),
                            orderDetail.getItemCount());
                }
            } catch (Exception e) {
                throw new Exception("error in process order[" + orderInfo.getId() + "]", e);
            }
        }
    }

    /**
     * 查询订单的物流情况
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public ExpressRespDto queryOrderCargoInfo(Integer userId, Integer orderId) {

        ExpressApiService expressApiService = new ExpressApiService();
        OrderInfo order = findOneOrder(userId, orderId);
        if (order == null || StringUtils.isEmpty(order.getCargoName())
                || StringUtils.isEmpty(order.getCargoNumber()))
            return new ExpressRespDto(CargoStatus.NODATA.message);

        SysParam sysParam = sysParamService.findByRootCodeAndParamName(SysParamCode.CARGO_LIST,
                order.getCargoName());
        String com = null != sysParam ? sysParam.getParamValue() : null;
        return new ExpressRespDto(OrderHelper.toAddress(order), expressApiService.request(com,
                order.getCargoNumber()));
    }
}
