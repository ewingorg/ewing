package com.ewing.dto;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
public class OrderInfoDto {
    private Integer id;
    /**
     * 流水号
     */
    private String bizId;
    /**
     * 消费者ID
     */
    private int customerId;
    /**
     * 消费者名称
     */
    private String customerName;
     
    /**
     * 商户用户ID
     */
    private int userId;
    /**
     * 产品费用
     */
    private float productPrice;
    /**
     * 运费
     */
    private float cargoPrice;
    /**
     * 总费用
     */
    private float totalPrice;
    /**
     * 购物状态 0:待付款 1:待发货 2:待收货
     */
    private char status;
    /**
     * 收件人
     */
    private String receiver;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 具体位置
     */
    private String address;
    /**
     * 电话号码
     */
    private String phone;
    
    
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
    public char getStatus() {
        return status;
    }
    public void setStatus(char status) {
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
    
    
}
