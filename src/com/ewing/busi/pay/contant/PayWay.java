package com.ewing.busi.pay.contant;

/**
 * 支付方式
 * 
 * @author tansonlam
 * @createDate 2016年4月7日
 * 
 */
public enum PayWay {
    // 支付方式 0:微信 1:支付宝
    WEIXIN("0", "微信"), ZHIFUBAO("1", "支付宝");

    private String value;
    private String message;

    private PayWay(String value, String message) {
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
