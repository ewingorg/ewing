package com.ewing.busi.pay.contant;

/**
 * 支付业务类型
 * 
 * @author tansonlam
 * @createDate 2016年2月19日
 * 
 */
public enum PayBusiType {
    
    ORDER("1", "订单"), REFUND("2", "退款");

    private String value;
    private String message;

    private PayBusiType(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getValue() {
        return value;
    }
}
