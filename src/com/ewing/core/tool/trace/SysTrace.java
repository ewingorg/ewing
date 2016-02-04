package com.ewing.core.tool.trace;

public class SysTrace {
	public SysTrace(int userId, String menuUrl) {
		super();
		this.userId = userId;
		this.menuUrl = menuUrl;
	}

	private int userId;
	private String menuUrl;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
}
