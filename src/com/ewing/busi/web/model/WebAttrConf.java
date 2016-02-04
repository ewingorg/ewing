/**
 * 
 */
package com.ewing.busi.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.ewing.busi.system.model.SysParam;
import com.ewing.core.app.anno.IgnoreField;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebAttrConf implements Serializable {

	private Timestamp createTime;

	private Integer id;

	private String iseff;

	private String attrKey;

	private String require;

	private String attrType;

	private String paramCode;

	private Integer sort;

	private String attrName;

	private Integer templateId;

	private String longDesc;

	private Timestamp lastUpdate;
	@IgnoreField
	private List<SysParam> paramCodeList;
	@IgnoreField
	private String attrValue;

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public List<SysParam> getParamCodeList() {
		return paramCodeList;
	}

	public void setParamCodeList(List<SysParam> paramCodeList) {
		this.paramCodeList = paramCodeList;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIseff() {
		return iseff;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
