package com.core.json;

import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

import com.core.json.config.DateFormatProcessor;
import com.core.json.config.JSONProcessor;
import com.core.json.config.NullStringProcessor;

/**
 * json处理工具类
 * 
 */
public class JsonUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * json转换
	 * 
	 * @param resultObj
	 * @param jsonConfig
	 * @return
	 */
	private static Object tranBean2String(Object resultObj,
			JsonConfig jsonConfig) {
		Object obj;

		if (resultObj instanceof Long || resultObj instanceof Integer
				|| resultObj instanceof String || resultObj instanceof Float
				|| resultObj instanceof Double || resultObj instanceof Number) {// 基本类型
			obj = resultObj;
		} else if (resultObj instanceof List || resultObj instanceof Set) {// 集合类型的
			if (jsonConfig == null) {
				obj = (JSONArray.fromObject(resultObj));
			} else {
				obj = (JSONArray.fromObject(resultObj, jsonConfig));
			}
		} else {
			if (jsonConfig == null) {
				obj = (JSONObject.fromObject(resultObj));
			} else {
				obj = (JSONObject.fromObject(resultObj, jsonConfig));
			}
		}

		// logger.debug("转换成json对象:[" + obj + "].");
		return obj;
	}

	/**
	 * json转换成字符
	 * 
	 * @param resultObj
	 * @return
	 */
	public static Object tranBean2String(Object resultObj) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,
				new DateFormatProcessor());
		config.registerJsonValueProcessor(java.util.Calendar.class,
				new DateFormatProcessor());
		config.registerJsonValueProcessor(java.util.GregorianCalendar.class,
				new DateFormatProcessor());
		config.registerJsonValueProcessor(java.lang.String.class,
				new NullStringProcessor());
		config
				.registerJsonValueProcessor(JSONObject.class,
						new JSONProcessor());
		config.registerJsonValueProcessor(JSONNull.class, new JSONProcessor());
		config.registerJsonValueProcessor(JSONArray.class, new JSONProcessor());
		return tranBean2String(resultObj, config);
	} 
	
 
}
