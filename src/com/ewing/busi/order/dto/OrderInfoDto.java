package com.ewing.busi.order.dto;

import java.util.Date;

import com.ewing.common.constant.OrderStatus;
import com.ewing.core.jdbc.annotation.Column;

/**
 * 订单DTO
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
public class OrderInfoDto {
    @Column(fieldName = "id")
    private Integer id;
    @Column(fieldName = "customer_id")
    private int customerId;
    @Column(fieldName = "customer_name")
    private String customerName;
    @Column(fieldName = "customer_phone")
    private String customerPhone;
    @Column(fieldName = "user_id")
    private int userId;
    @Column(fieldName = "user_name")
    private String userName;
    @Column(fieldName = "biz_id")
    private String bizId;
    @Column(fieldName = "product_price")
    private float productPrice;
    @Column(fieldName = "cargo_price")
    private float cargoPrice;
    @Column(fieldName = "total_price")
    private float totalPrice;
    @Column(fieldName = "status")
    private String status;
    @Column(fieldName = "receiver")
    private String receiver;
    @Column(fieldName = "post_code")
    private String postCode;
    @Column(fieldName = "province")
    private String province;
    @Column(fieldName = "city")
    private String city;
    @Column(fieldName = "region")
    private String region;
    @Column(fieldName = "address")
    private String address;
    @Column(fieldName = "phone")
    private String phone;
    @Column(fieldName = "cargo_name")
    private String cargoName;
    @Column(fieldName = "cargo_number")
    private String cargoNumber;
    @Column(fieldName = "iseff")
    private String iseff;
    @Column(fieldName = "create_time")
    private Date createTime;
    @Column(fieldName = "last_update")
    private Date lastUpdate;
    /**
     * 是否需要物流
     */
    @Column(fieldName = "need_cargo")
    private Integer needCargo;

    /**
     * 修改后运费
     */
    @Column(fieldName = "modify_cargo_price")
    private float modifyCargoPrice;
    /**
     * 修改后的总价格
     */
    @Column(fieldName = "modify_total_price")
    private float modifyTotalPrice;
    /**
     * 付款时间
     */
    @Column(fieldName = "pay_time")
    private Date payTime;
    /**
     * 付款时间
     */
    @Column(fieldName = "cargo_time")
    private Date cargoTime;
    /**
     * 确定收货时间
     */
    @Column(fieldName = "confirm_cargo_time")
    private Date confirmCargoTime;

    public Integer getNeedCargo() {
        return needCargo;
    }

    public void setNeedCargo(Integer needCargo) {
        this.needCargo = needCargo;
    }

    public float getModifyCargoPrice() {
        return modifyCargoPrice;
    }

    public void setModifyCargoPrice(float modifyCargoPrice) {
        this.modifyCargoPrice = modifyCargoPrice;
    }

    public float getModifyTotalPrice() {
        return modifyTotalPrice;
    }

    public void setModifyTotalPrice(float modifyTotalPrice) {
        this.modifyTotalPrice = modifyTotalPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCargoTime() {
        return cargoTime;
    }

    public void setCargoTime(Date cargoTime) {
        this.cargoTime = cargoTime;
    }

    public Date getConfirmCargoTime() {
        return confirmCargoTime;
    }

    public void setConfirmCargoTime(Date confirmCargoTime) {
        this.confirmCargoTime = confirmCargoTime;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getOrderStatusTanslate() {
        return OrderStatus.fromStatus(status).getMessage();
    }

}
