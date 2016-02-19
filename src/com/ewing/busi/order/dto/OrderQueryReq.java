package com.ewing.busi.order.dto;

import com.ewing.common.dto.PageRequest;

/**
 * 订单查询请求
 * 
 * @author tansonlam
 * @createDate 2016年2月16日
 * 
 */
public class OrderQueryReq extends PageRequest {
    /**
     * 订单状态 0:待付款 1:待发货 2:待收货 3:退款中 4:已完成 5:已关闭 
     */
    private String status;
    /**
     * 订单流水号
     */
    private String bizId;
    /**
     * 消费者名称
     */
    private String customerName;

    /**
     * 物流名称
     */
    private String cargoName;
    /**
     * 物流编号
     */
    private String cargoNumber;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 订单时间，开始日期
     */
    private String startDate;
    /**
     * 订单时间，结束日期
     */
    private String endDate;
    
    
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBizId() {
        return bizId;
    }
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCargoName() {
        return cargoName;
    }
    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
    public String getCargoNumber() {
        return cargoNumber;
    }
    public void setCargoNumber(String cargoNumber) {
        this.cargoNumber = cargoNumber;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    
}
