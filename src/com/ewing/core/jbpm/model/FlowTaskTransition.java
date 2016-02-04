package com.ewing.core.jbpm.model;

import java.sql.Timestamp;

/**
 * FlowTaskTransition entity. @author MyEclipse Persistence Tools
 */

public class FlowTaskTransition implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taskId;
	private String name;
	private String to;
	private Timestamp createTime;
	private Timestamp lastUpdate;
	private String needArrange;
	private Integer toId;

	// Constructors

	/** default constructor */
	public FlowTaskTransition() {
	}

	/** minimal constructor */
	public FlowTaskTransition(Integer taskId, Timestamp createTime,
			Timestamp lastUpdate, String needArrange, Integer toId) {
		this.taskId = taskId;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.needArrange = needArrange;
		this.toId = toId;
	}

	/** full constructor */
	public FlowTaskTransition(Integer taskId, String name, String to,
			Timestamp createTime, Timestamp lastUpdate, String needArrange,
			Integer toId) {
		this.taskId = taskId;
		this.name = name;
		this.to = to;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.needArrange = needArrange;
		this.toId = toId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
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

	public String getNeedArrange() {
		return this.needArrange;
	}

	public void setNeedArrange(String needArrange) {
		this.needArrange = needArrange;
	}

	public Integer getToId() {
		return this.toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

}