package com.ewing.common.constant;

import com.ewing.util.PropertyUtil;

/**
 * 系统参数
 * 
 * @author tansonlam
 * @createDate 2016年3月1日
 * 
 */
public class SystemProperty {
    /**
     * 免登
     */
    public final static Boolean LOGIN_DEBUG = Boolean.valueOf(PropertyUtil.getProperty(
            "login.debug", "false"));

    public final static Integer LOGIN_SELLERID = Integer.valueOf(PropertyUtil
            .getProperty("login.sellerId"));
    /**
     * 商鋪域名
     */
    public final static String SHOPDOAMIN = PropertyUtil.getProperty("shop.domain");

    /** 快递查询接口调用的appid **/
    public final static String EXPRESS_APPID = PropertyUtil.getProperty("express_appid");

}
