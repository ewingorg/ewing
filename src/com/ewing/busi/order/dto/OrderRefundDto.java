package com.ewing.busi.order.dto;

import java.util.Date;

import com.ewing.busi.order.contant.RefundStatus;
import com.ewing.busi.order.contant.RefundType;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年4月6日
 * 
 */
public class OrderRefundDto {
    private Integer id;
    private String bizId;
    private int orderId;
    private int orderDetailId;
    private String type;
    private String typeStr;
    private int customerId;
    private String customerName;
    private String customerPhone;
    private int userId;
    private int resourceId;
    private String reasonType;
    private String reasonTypeStr;
    private String reason;
    private String cargoName;
    private String cargoNumber;
    private float refundMoney;
    private String status;
    private String statusStr;
    private String iseff;
    private Date createTime;
    private Date lastUpdate;
    private String rejectReason;
    
    

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getReasonTypeStr() {
        return reasonTypeStr;
    }

    public void setReasonTypeStr(String reasonTypeStr) {
        this.reasonTypeStr = reasonTypeStr;
    }

    public void translate() {
        if (status != null)
            statusStr = RefundStatus.getMsg(status);
        if (type != null)
            typeStr = RefundType.getMsg(type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public float getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(float refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getIseff() {
        return iseff;
    }

    public void setIseff(String iseff) {
        this.iseff = iseff;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
