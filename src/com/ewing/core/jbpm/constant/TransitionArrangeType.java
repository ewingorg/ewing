package com.ewing.core.jbpm.constant;


public enum TransitionArrangeType {
	UNARRANGE("0"), // 不需要指定安排
	ARRANGE("1"), // 需要指定安排
	CONFIRM("2");// 已安排的人员确认
	private String type;

	TransitionArrangeType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public static TransitionArrangeType getArrangeType(String oper) {
		for (TransitionArrangeType type : TransitionArrangeType.values()) {
			if (type.getType().equals(oper))
				return type;
		}
		return null;
	}
}
