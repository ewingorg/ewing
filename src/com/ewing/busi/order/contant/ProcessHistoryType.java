package com.ewing.busi.order.contant;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 处理历史类型<br/>
 * 
 * @author Joeson
 */
public enum ProcessHistoryType {

    ORDER("1", "订单"),

    REFUND("2", "退款");

    private String value;
    private String msg;

    ProcessHistoryType(String value, String msg) {
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

        for (ProcessHistoryType v : ProcessHistoryType.values()) {
            if (ObjectUtils.equals(v.getValue(), value)) {
                return v.getMsg();
            }
        }

        return StringUtils.EMPTY;
    }
}
