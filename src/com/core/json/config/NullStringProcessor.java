package com.core.json.config;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 空字符串转换处理
 * 
 * 
 */
public class NullStringProcessor implements JsonValueProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object,
	 *      net.sf.json.JsonConfig)
	 */
	public Object processArrayValue(Object obj, JsonConfig config) {
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String,
	 *      java.lang.Object, net.sf.json.JsonConfig)
	 */
	public Object processObjectValue(String name, Object obj, JsonConfig config) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}
	}

}
