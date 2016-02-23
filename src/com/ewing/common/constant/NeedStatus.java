package com.ewing.common.constant;

/**
 * 是否需要状态
 * 
 * @author tansonlam
 * @createDate 2016年2月19日
 * 
 */
public enum NeedStatus {

    NOTNEED(0, "无需要"), NEED(1, "需要");

    private Integer status;
    private String message;

    private NeedStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

}
