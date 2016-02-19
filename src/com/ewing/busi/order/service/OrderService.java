package com.ewing.busi.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dao.OrderDao;
import com.ewing.busi.order.dao.OrderDetailDao;
import com.ewing.busi.order.dto.OrderDetailDto;
import com.ewing.busi.order.dto.OrderInfoComplex;
import com.ewing.busi.order.dto.OrderInfoDto;
import com.ewing.busi.order.dto.OrderQueryReq;
import com.ewing.core.jdbc.util.PageBean;

@Repository("orderService")
public class OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderDetailDao orderDetailDao;
    
    /**
     * 查找订单
     * @param userId
     * @param orderId
     * @return
     */
    public OrderInfoComplex findOneOrder(Integer userId, Integer orderId) {
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
