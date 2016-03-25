package com.ewing.busi.order.dto;

public class LightAddressInfoResp implements java.io.Serializable  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 收件人
     */
    private String receiver;
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
     * 邮编
     */
    private String postCode;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 是否默认 0:不是 1:是
     */
    private char isDefault;

    
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public char getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(char isDefault) {
        this.isDefault = isDefault;
    }

}
