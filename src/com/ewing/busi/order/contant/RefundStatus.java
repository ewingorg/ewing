package com.ewing.busi.order.contant;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 客户订单状态<br/>
 * 
 * INIT ==提交订单==> WAIT_PAY ==支付==> WAIT_SEND ==发货，填写快递号==> WAIT_RECEIVE ==收货==> FINISHED
 * 
 * @author Joeson
 */
public enum RefundStatus {

    WAIT_CONFIRM("1", "待商户确认"),

    CONFIRMED("2", "商户已确认"),

    WAIT_RECEIVED("3", "客户寄货"),

    RECEIVED("4", "商户已收货"),

    FINISHED("5", "已经退款"),

    CANCELED("6", "用户取消");

    private String value;
    private String msg;

    RefundStatus(String value, String msg) {
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

        for (RefundStatus v : RefundStatus.values()) {
            if (ObjectUtils.equals(v.getValue(), value)) {
                return v.getMsg();
            }
        }

        return StringUtils.EMPTY;
    }
}
