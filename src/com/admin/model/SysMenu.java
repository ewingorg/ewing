package com.admin.model;

import java.sql.Timestamp;

/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */

public class SysMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String url;
	private String level;
	private Integer parentid;
	private String iseff;
	private String isleaf;
	private String des;
	private Timestamp createtime;
	private Timestamp lastUpdate;
	private String icon;
	private Integer sort; 

	// Constructors

	/** default constructor */
	public SysMenu() {
	}

	/** minimal constructor */
	public SysMenu(String name, String level, Integer parentid, String iseff,
			String isleaf, Timestamp createtime, Timestamp lastUpdate) {
		this.name = name;
		this.level = level;
		this.parentid = parentid;
		this.iseff = iseff;
		this.isleaf = isleaf;
		this.createtime = createtime;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public SysMenu(String name, String url, String level, Integer parentid,
			String iseff, String isleaf, String des, Timestamp createtime,
			Timestamp lastUpdate, String icon, Integer sort) {
		this.name = name;
		this.url = url;
		this.level = level;
		this.parentid = parentid;
		this.iseff = iseff;
		this.isleaf = isleaf;
		this.des = des;
		this.createtime = createtime;
		this.lastUpdate = lastUpdate;
		this.icon = icon;
		this.sort = sort;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public String getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}