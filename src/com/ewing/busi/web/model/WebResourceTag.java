package com.ewing.busi.web.model;

import java.sql.Timestamp;

/**
 * WebResourceCategory entity. @author MyEclipse Persistence Tools
 */

public class WebResourceTag implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private String iseff;

	private Timestamp createTime;

	private Timestamp lastUpdate;

	/** default constructor */
	public WebResourceTag() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIseff() {
		return iseff;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}