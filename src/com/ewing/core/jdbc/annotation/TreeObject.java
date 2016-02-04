package com.ewing.core.jdbc.annotation;

import java.io.Serializable;

public class TreeObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tId;
	private String tName;
	private String parentId;
	private String type;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTId() {
		return this.tId;
	}

	public void setTId(String id) {
		this.tId = id;
	}

	public String getTName() {
		return this.tName;
	}

	public void setTName(String name) {
		this.tName = name;
	}
}