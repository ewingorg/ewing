package com.ewing.busi.web.model;

import java.sql.Timestamp;

import com.ewing.core.app.anno.IgnoreField;

/**
 * WebTemplate entity. @author MyEclipse Persistence Tools
 */

public class WebTemplate implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String templatePath;
	private String groupKeys;
	private String iseff;
	private Timestamp createTime;
	private String templateType;
	private Integer templatePackageId;
	
	@IgnoreField
	private String groupKeysTranslate;

	public Integer getTemplatePackageId() {
        return templatePackageId;
    }

    public void setTemplatePackageId(Integer templatePackageId) {
        this.templatePackageId = templatePackageId;
    }

    public String getGroupKeysTranslate() {
		return groupKeysTranslate;
	}

	public void setGroupKeysTranslate(String groupKeysTranslate) {
		this.groupKeysTranslate = groupKeysTranslate;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	private Timestamp lastUpdate;

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

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getGroupKeys() {
		return groupKeys;
	}

	public void setGroupKeys(String groupKeys) {
		this.groupKeys = groupKeys;
	}

	public String getIseff() {
		return iseff;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}