/**
 * 
 */
package com.admin.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebResource implements Serializable {

    private Timestamp createTime;

    private Integer id;

    private String imageUrl;

    private String iseff;

    private Timestamp lastUpdate;

    private String longDesc;

    private String name;

    private String shortDesc;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getIseff() {
        return iseff;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIseff(String iseff) {
        this.iseff = iseff;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
}
