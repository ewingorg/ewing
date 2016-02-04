package com.ewing.busi.order.model;

// Generated 2016-2-4 17:41:09 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * OrderCart generated by hbm2java
 */
public class OrderCart implements java.io.Serializable {

    private Integer id;
    private int customerId;
    private int userId;
    private int resourceId;
    private int itemCount;
    private float unitPrice;
    private float totalPrice;
    private char iseff;
    private Date createTime;
    private Date lastUpdate;

    public OrderCart() {
    }

    public OrderCart(int customerId, int userId, int resourceId, int itemCount, float unitPrice,
            float totalPrice, char iseff, Date createTime, Date lastUpdate) {
        this.customerId = customerId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.itemCount = itemCount;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
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

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public float getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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
