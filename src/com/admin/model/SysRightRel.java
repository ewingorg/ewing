package com.admin.model;

import java.sql.Timestamp;

/**
 * SysRightRel entity. @author MyEclipse Persistence Tools
 */

public class SysRightRel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String relId;
	private Integer menuId;
	private Timestamp createTime;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public SysRightRel() {
	}

	/** minimal constructor */
	public SysRightRel(String type, Timestamp createTime, Timestamp lastUpdate) {
		this.type = type;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public SysRightRel(String type, String relId, Integer menuId,
			Timestamp createTime, Timestamp lastUpdate) {
		this.type = type;
		this.relId = relId;
		this.menuId = menuId;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelId() {
		return this.relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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