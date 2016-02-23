package com.ewing.busi.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dao.OrderDao;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.dto.OrderDetailDto;
import com.ewing.busi.order.dto.OrderInfoComplex;
import com.ewing.busi.order.dto.OrderInfoDto;
import com.ewing.busi.order.dto.OrderQueryReq;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.common.constant.NeedStatus;
import com.ewing.common.constant.OrderStatus;
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
        orderInfo.setStatus(OrderStatus.WAIT_RECEIVE.getStatus());
        orderInfo.setCargoTime(new Date());
        orderInfo.setCargoName(cargoName == null ? "" : cargoName);
        orderInfo.setCargoNumber(cargoNumber == null ? "" : cargoNumber);
        orderInfo.setNeedCargo(needCargo);
        baseDao.update(orderInfo);
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
                .findDetailList(new Integer[] { orderInfo.getId() });
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
        List<OrderDetailDto> orderDetailList = orderDetailDao.findDetailList(orderIds
                .toArray(new Integer[orderIds.size()]));
        // 重新组装每张订单，以及归属的订单详情
        List<OrderInfoComplex> complexList = new ArrayList<OrderInfoComplex>();
        for (OrderInfoDto orderInfo : orderList) {
            OrderInfoComplex orderInfoComplex = new OrderInfoComplex();
            orderInfoComplex.setOrderInfo(orderInfo);
            List<OrderDetailDto> detailList = new ArrayList<OrderDetailDto>();
            for (OrderDetailDto detailDto : orderDetailList) {
                if (detailDto.getOrderId() == orderInfo.getId()) {
                    detailList.add(detailDto);
                }
            }
            orderInfoComplex.setOrderDetailList(detailList);
            complexList.add(orderInfoComplex);
        }
        return new PageBean<OrderInfoComplex>(orderPageBean.getPage(),
                orderPageBean.getTotalCount(), orderPageBean.getPageSize(), complexList);
    }
}
