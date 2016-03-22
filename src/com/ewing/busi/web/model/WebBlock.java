/**
 * 
 */
package com.ewing.busi.web.model;

import java.sql.Timestamp;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebBlock {

    private Timestamp createTime;
    private Integer userId;
    private Integer shopId;
    private String groupKey;
    private Integer id;
    private String imageUrl;
    private String iseff;
    private Timestamp lastUpdate;
    private String linkUrl;
    private String linkType;
    private String name;
    private Integer parentId;
    private Integer rank;
    private String groupType;
    private String tempalte;
    private String shortDesc;

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getTempalte() {
        return tempalte;
    }

    public void setTempalte(String tempalte) {
        this.tempalte = tempalte;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getGroupKey() {
        return groupKey;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
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

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
