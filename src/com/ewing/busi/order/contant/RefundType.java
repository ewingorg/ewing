package com.ewing.busi.order.contant;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 退款类型
 * 
 */
public enum RefundType {
    // 0:退货退款 1:仅退款
    GOOD("0", "退货退款"),

    MONEY("1", "仅退款");

    private String value;
    private String msg;

    RefundType(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(String value) {
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }

        for (RefundType v : RefundType.values()) {
            if (ObjectUtils.equals(v.getValue(), value)) {
                return v.getMsg();
            }
        }

        return StringUtils.EMPTY;
    }
}
