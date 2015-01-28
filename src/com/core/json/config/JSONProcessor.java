package com.core.json.config;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON对象转换处理：压制config对JSON对象的重复包装
 * 
 * 
 */
public class JSONProcessor implements JsonValueProcessor {

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
		return obj;
	}

}
