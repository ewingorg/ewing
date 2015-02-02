package com.admin.dto;

import java.sql.Timestamp;

/**
 * 分类与资源关系封装类
 * 
 * @author tanson lam
 * @creation 2015年1月30日
 */
public class RelResourceDto {

	private Integer categoryId;
	private String categoryName;
	private String iseff;
	private Integer resourceId;
	private String resourceName;
	private String resourceIseff;
	private String resourceShortdesc;
	private String template;
	private Timestamp createTime;
	private Timestamp lastUpdate;

	public String getResourceShortdesc() {
		return resourceShortdesc;
	}

	public void setResourceShortdesc(String resourceShortdesc) {
		this.resourceShortdesc = resourceShortdesc;
	}

	public String getResourceIseff() {
		return resourceIseff;
	}

	public void setResourceIseff(String resourceIseff) {
		this.resourceIseff = resourceIseff;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIseff() {
		return iseff;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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

}
