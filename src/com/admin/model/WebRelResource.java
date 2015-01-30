/**
 * 
 */
package com.admin.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebRelResource implements Serializable {
	private Integer categoryId;
	private Timestamp createTime;
	private Integer id;
	private String iseff;
	private Timestamp lastUpdate;
	private Integer resourceId;
	private String tempalte;

	public String getTempalte() {
		return tempalte;
	}

	public void setTempalte(String tempalte) {
		this.tempalte = tempalte;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public String getIseff() {
		return iseff;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

}
