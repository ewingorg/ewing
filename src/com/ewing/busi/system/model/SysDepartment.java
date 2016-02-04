package com.ewing.busi.system.model;

import java.sql.Timestamp;

/**
 * SysDepartment entity. @author MyEclipse Persistence Tools
 */

public class SysDepartment implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String des;
	private Integer parentid;
	private String iseff;
	private Timestamp createTime;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public SysDepartment() {
	}

	/** minimal constructor */
	public SysDepartment(String name, String iseff, Timestamp createTime,
			Timestamp lastUpdate) {
		this.name = name;
		this.iseff = iseff;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public SysDepartment(String name, String des, Integer parentid,
			String iseff, Timestamp createTime, Timestamp lastUpdate) {
		this.name = name;
		this.des = des;
		this.parentid = parentid;
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

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
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