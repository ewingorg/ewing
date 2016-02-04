/**
 * 
 */
package com.ewing.busi.resource.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebResourceAttr implements Serializable {

	private Timestamp createTime;

	private Integer id;

	private String iseff;

	private String attrKey;

	private String attrName;

	private String attrValue;

	private Timestamp lastUpdate;

	private Integer rank;

	private Integer resourceId;

	private String value;

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

	public Integer getRank() {
		return rank;
	}

	public String getValue() {
		return value;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
