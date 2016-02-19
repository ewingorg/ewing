package com.ewing.common.constant;

/**
 * 模板类型
 * 
 * @author tanson lam
 * @creation 2015年5月18日
 */
public enum TemplateType {
	NORMAL("0"), RES("1");

	private String type;

	private TemplateType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
