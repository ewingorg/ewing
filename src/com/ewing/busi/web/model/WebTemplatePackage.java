package com.ewing.busi.web.model;

import java.sql.Timestamp;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebTemplatePackage implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String imageUrl;

    private String iseff;

    private Timestamp createTime;

    private Timestamp lastUpdate;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getIseff() {
        return iseff;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIseff(String iseff) {
        this.iseff = iseff;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setName(String name) {
        this.name = name;
    }

}
