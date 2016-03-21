package com.ewing.busi.seller.model;

// Generated 2016-2-16 11:51:43 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * SellerShop generated by hbm2java
 */
public class SellerShop implements java.io.Serializable {

    private Integer id;
    private Integer userId;
    private String shopName;
    private String shopIcon;
    private String shopDesc;
    private String email;
    private String phone;
    private String addr;
    private Integer templatePackageId;
    private String iseff;
    private Date createTime;
    private Date lastUpdate;

    public SellerShop() {
    }

    public Integer getTemplatePackageId() {
        return templatePackageId;
    }

    public void setTemplatePackageId(Integer templatePackageId) {
        this.templatePackageId = templatePackageId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopIcon() {
        return this.shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getShopDesc() {
        return this.shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIseff() {
        return this.iseff;
    }

    public void setIseff(String iseff) {
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
