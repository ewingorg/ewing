package com.ewing.busi.order.dto;

import java.util.Date;

import com.ewing.core.jdbc.annotation.Column;

/**
 * 订单详情
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
public class OrderDetailDto {
    @Column(fieldName = "id")
    private Integer id;
    @Column(fieldName = "order_id")
    private int orderId;
    @Column(fieldName = "customer_id")
    private int customerId;
    @Column(fieldName = "biz_id")
    private String bizId;
    private int userId;
    @Column(fieldName = "resource_id")
    private int resourceId;
    @Column(fieldName = "resource_name")
    private String resourceName;
    @Column(fieldName = "resource_image_url")
    private String resourceImageUrl;
    @Column(fieldName = "item_count")
    private int itemCount;
    @Column(fieldName = "unit_price")
    private int unitPrice;
    @Column(fieldName = "cargo_price")
    private float cargoPrice;
    @Column(fieldName = "total_price")
    private float totalPrice;
    @Column(fieldName = "status")
    private String status;
    @Column(fieldName = "iseff")
    private String iseff;
    @Column(fieldName = "create_time")
    private Date createTime;
    @Column(fieldName = "last_update")
    private Date lastUpdate;

    public String getResourceImageUrl() {
        return resourceImageUrl;
    }

    public void setResourceImageUrl(String resourceImageUrl) {
        this.resourceImageUrl = resourceImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(float cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
