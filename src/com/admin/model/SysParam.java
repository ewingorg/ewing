package com.admin.model;

import java.sql.Timestamp;

/**
 * SysParam entity. @author MyEclipse Persistence Tools
 */

public class SysParam implements java.io.Serializable {

	// Fields

	private String paramCode;
	private String paramName;
	private String paramValue;
	private String rootCode;
	private String des;
	private String seq;
	private String iseff;
	private Timestamp createTime;
	private Timestamp lastUpdate;

	// Constructors

	/** default constructor */
	public SysParam() {
	}

	/** minimal constructor */
	public SysParam(String paramCode, String paramName, String iseff,
			Timestamp createTime, Timestamp lastUpdate) {
		this.paramCode = paramCode;
		this.paramName = paramName;
		this.iseff = iseff;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
	}

	/** full constructor */
	public SysParam(String paramCode, String paramName, String paramValue,
			String rootCode, String des, String seq, String iseff,
			Timestamp createTime, Timestamp lastUpdate) {
		this.paramCode = paramCode;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.rootCode = rootCode;
		this.des = des;
		this.seq = seq;
		this.iseff = iseff;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
	}

	// Property accessors

	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
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

	public String getRootCode() {
		return this.rootCode;
	}

	public void setRootCode(String rootCode) {
		this.rootCode = rootCode;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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