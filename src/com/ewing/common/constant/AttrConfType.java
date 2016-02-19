package com.ewing.common.constant;

/**
 * 资源属性类型
 *
 * @author tanson lam
 * @creation 2015年5月20日
 */
public enum AttrConfType {
	TEXT("0"),
	TEXTAREA("1"),
	SELECT("2"),
	CHECKBOX("3"),
	RADIO("4");
	
	private String type;
	
	private AttrConfType(String type){
		this.type=type;
	}

	public String getType() {
		return type;
	}
	
}
