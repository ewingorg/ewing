package com.ewing.busi.system.model;

import java.sql.Timestamp;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String iseff;
	private Timestamp createTime;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(String name, String iseff, Timestamp createTime,
			Timestamp lastUpdate) {
		this.name = name;
		this.iseff = iseff;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIseff() {
		return this.iseff;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}