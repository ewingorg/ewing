/**
 * 
 */
package com.admin.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.core.jdbc.annotation.Column;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebResource implements Serializable {
	@Column(fieldName = "create_time")
	private Timestamp createTime;
	@Column(fieldName = "id")
	private Integer id;
	@Column(fieldName = "image_url")
	private String imageUrl;
	@Column(fieldName = "iseff")
	private String iseff;
	@Column(fieldName = "last_update")
	private Timestamp lastUpdate;
	@Column(fieldName = "long_desc")
	private String longDesc;
	@Column(fieldName = "name")
	private String name;
	@Column(fieldName = "short_desc")
	private String shortDesc;
	@Column(fieldName = "category_id")
	private Integer categoryId;
	@Column(fieldName = "tag_id")
	private String tagId;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getIseff() {
		return iseff;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public String getName() {
		return name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setIseff(String iseff) {
		this.iseff = iseff;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
}
