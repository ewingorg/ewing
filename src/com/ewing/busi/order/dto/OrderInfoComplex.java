package com.ewing.busi.order.dto;

import java.util.List;

/**
 * 订单复杂体
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
public class OrderInfoComplex {

    private OrderInfoDto orderInfo;

    private List<OrderDetailDto> orderDetailList;

    public OrderInfoDto getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoDto orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderDetailDto> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailDto> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

}
