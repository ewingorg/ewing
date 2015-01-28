package com.admin.model;

import java.sql.Timestamp;

/**
 * SysLogTrace entity. @author MyEclipse Persistence Tools
 */

public class SysLogTrace implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String moduleName;
	private Timestamp loginTime;

	// Constructors

	/** default constructor */
	public SysLogTrace() {
	}

	/** full constructor */
	public SysLogTrace(Integer userId, String moduleName, Timestamp loginTime) {
		this.userId = userId;
		this.moduleName = moduleName;
		this.loginTime = loginTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

}