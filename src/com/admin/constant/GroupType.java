/**
 * 
 */
package com.admin.constant;

/**
 * 組分类，对应Web_group.type
 * 
 * @author tanson lam
 * @createDate 2014年12月2日
 * 
 */
public enum GroupType {
    NAV(0, "导航栏"), BANNER(1, "栏目"), PRODUCT(2, "产品");

    private Integer code;
    private String msg;

    private GroupType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
