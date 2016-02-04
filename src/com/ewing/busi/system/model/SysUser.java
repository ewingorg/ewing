package com.ewing.busi.system.model;

import java.sql.Timestamp;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private Integer depId;
	private String password;
	private String sex;
	private String position;
	private String phone;
	private String addr;
	private String zipcode;
	private String iseff;
	private Timestamp createTime;
	private Timestamp lastUpdate;
	private Integer roleId;
	private String email;

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String userName, String password, Timestamp createTime,
			Timestamp lastUpdate, String email) {
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.email = email;
	}

	/** full constructor */
	public SysUser(String userName, Integer depId, String password, String sex,
			String position, String phone, String addr, String zipcode,
			String iseff, Timestamp createTime, Timestamp lastUpdate,
			Integer roleId, String email) {
		this.userName = userName;
		this.depId = depId;
		this.password = password;
		this.sex = sex;
		this.position = position;
		this.phone = phone;
		this.addr = addr;
		this.zipcode = zipcode;
		this.iseff = iseff;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.roleId = roleId;
		this.email = email;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDepId() {
		return this.depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}