package com.core.jbpm.model;

import java.sql.Timestamp;

/**
 * FlowTask entity. @author MyEclipse Persistence Tools
 */

public class FlowTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private String processName;
	private String taskName;
	private String assignType;
	private String assigner;
	private Timestamp createTime;
	private Timestamp lastUpdate;
	private String busiPageName;
	private String type;

	// Constructors

	/** default constructor */
	public FlowTask() {
	}

	/** minimal constructor */
	public FlowTask(String processName, String taskName, Timestamp createTime,
			Timestamp lastUpdate, String type) {
		this.processName = processName;
		this.taskName = taskName;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.type = type;
	}

	/** full constructor */
	public FlowTask(String processName, String taskName, String assignType,
			String assigner, Timestamp createTime, Timestamp lastUpdate,
			String busiPageName, String type) {
		this.processName = processName;
		this.taskName = taskName;
		this.assignType = assignType;
		this.assigner = assigner;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.busiPageName = busiPageName;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssignType() {
		return this.assignType;
	}

	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}

	public String getAssigner() {
		return this.assigner;
	}

	public void setAssigner(String assigner) {
		this.assigner = assigner;
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

	public String getBusiPageName() {
		return this.busiPageName;
	}

	public void setBusiPageName(String busiPageName) {
		this.busiPageName = busiPageName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}