package com.admin.model;

// Generated 2015-10-10 22:43:59 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * WebResourceParam generated by hbm2java
 */
public class WebResourceParam implements java.io.Serializable {

	private Integer id;
	private int resourceId;
	private int categoryId;
	private String paramName;
	private String paramValue;
	private String rootParamName;
	private String des;
	private int rank;
	private String iseff;
	private Date createTime;
	private Date lastUpdate;

	public WebResourceParam() {
	}

	public WebResourceParam(int resourceId, String paramName, int rank,
			String iseff, Date createTime, Date lastUpdate) {
		this.resourceId = resourceId;
		this.paramName = paramName;
		this.rank = rank;
		this.iseff = iseff;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getRootParamName() {
		return rootParamName;
	}

	public void setRootParamName(String rootParamName) {
		this.rootParamName = rootParamName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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
