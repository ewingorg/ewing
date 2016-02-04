package com.ewing.busi.order.model;

// Generated 2016-2-4 17:41:09 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * OrderDetail generated by hbm2java
 */
public class OrderDetail implements java.io.Serializable {

    private Integer id;
    private int orderId;
    private int customerId;
    private String bizId;
    private int userId;
    private int resourceId;
    private int itemCount;
    private int unitPrice;
    private float cargoPrice;
    private float totalPrice;
    private char status;
    private char iseff;
    private Date createTime;
    private Date lastUpdate;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int customerId, String bizId, int userId, int resourceId,
            int itemCount, int unitPrice, float cargoPrice, float totalPrice, char status,
            char iseff, Date createTime, Date lastUpdate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.bizId = bizId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.itemCount = itemCount;
        this.unitPrice = unitPrice;
        this.cargoPrice = cargoPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.iseff = iseff;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getCargoPrice() {
        return this.cargoPrice;
    }

    public void setCargoPrice(float cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public char getStatus() {
        return this.status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getIseff() {
        return this.iseff;
    }

    public void setIseff(char iseff) {
        this.iseff = iseff;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
