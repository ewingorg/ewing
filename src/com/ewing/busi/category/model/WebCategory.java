package com.ewing.busi.category.model;

import java.sql.Timestamp;

import com.ewing.core.app.anno.IgnoreField;

/**
 * WebResourceCategory entity. @author MyEclipse Persistence Tools
 */

public class WebCategory implements java.io.Serializable {

	// Fields

	private Integer id;

	private Integer userId;

	private String name;

	private String level;

	private Integer parentid;

	private String iseff;

	private Timestamp createTime;

	private Timestamp lastUpdate;

	private Integer sort;
	@IgnoreField
	private String parentName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}