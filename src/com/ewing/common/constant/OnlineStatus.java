package com.ewing.common.constant;
 
/**
 * 
 * 资源在线状态
 * 
 * @author tansonlam
 * @createDate 2016年2月24日
 *
 */
public enum OnlineStatus {

    PUBLICING(0, "发布中"),

    ONLINE(1, "上架"),

    OFFLINE(2, "下架");

    private int value;
    private String msg;

    OnlineStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsgByStatus(int status) {
        switch (status) {
        case 0:
            return PUBLICING.getMsg();
        case 1:
            return ONLINE.getMsg();
        case 2:
            return OFFLINE.getMsg();
        default:
            return "未知";
        }
    }
}
