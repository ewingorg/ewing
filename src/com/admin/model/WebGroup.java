/**
 * 
 */
package com.admin.model;

import java.sql.Timestamp;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public class WebGroup implements java.io.Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private Timestamp createTime;

	private String groupKey;

	private Integer id;

	private String iseff;

	private Timestamp lastUpdate;

	private String name;

	private String groupType;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getGroupKey() {
		return groupKey;
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

	public String getName() {
		return name;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

}
