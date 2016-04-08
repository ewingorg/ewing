package com.ewing.busi.pay.contant;

/**
 * 支付状态
 * 
 * @author tansonlam
 * @createDate 2016年4月7日
 * 
 */
public enum PayStatus {
    //0:发起 1:成功 2:失败
    INIT("0", "发起"), SUCCESS("1", "成功"),FAIL("2", "失败");

    private String value;
    private String message;

    private PayStatus(String value, String message) {
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
 
