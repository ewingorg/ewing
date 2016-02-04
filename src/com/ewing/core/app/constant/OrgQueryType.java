package com.ewing.core.app.constant;


/**
 * 组织查询类型
 * 
 */
public enum OrgQueryType {
	PERSONAL("0"), // 个人
	ROLE("1"), // 审批
	DEPARTMENT("2"); // 审批
	private String type;

	private OrgQueryType(String type) {
		this.type = type;
	}

	public String getValue() {
		return type;
	}

	public static OrgQueryType getOrgQueryType(String type) {
		for (OrgQueryType orgQueryType : OrgQueryType.values()) {
			if (orgQueryType.getValue().equals(type))
				return orgQueryType;
		}
		return null;
	}
}
