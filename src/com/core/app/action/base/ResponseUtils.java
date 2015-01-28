package com.core.app.action.base;

/**
 * @author tanson lin
 * 
 * @create:2012-2-23
 * @description:在action中提供不同返回信息的工具类。
 */
public class ResponseUtils {

	/**
	 * 成功
	 * 
	 * @param retInfo
	 * @return
	 */
	public static ResponseData success(String... retInfo) {
		ResponseData responseData = new ResponseData();
		responseData.setSuccess(true);
		responseData.setRetinfo(retInfo[0]);
		return responseData;
	}

	/**
	 * 失败
	 * 
	 * @param retInfo
	 * @return
	 */
	public static ResponseData fail(String... retInfo) {
		ResponseData responseData = new ResponseData();
		responseData.setSuccess(false);
		responseData.setRetinfo(retInfo[0]);
		return responseData;
	}
}
