/**
 * 
 */
package com.core.tag;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.util.CommonUtil;

/**
 * EL函数的代理转调<br>
 * 此类封装接口给aiFn函数库调用
 * 
 */
public class ELFunctionHelper {

	/**
	 * 用于两个字符串合并
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String append(Object str1, Object str2) {
		return CommonUtil.append(str1, str2);
	}

	/**
	 * 格式化日期成字符串（yyyy-MM-dd）
	 * 
	 * @param date
	 * @return
	 */
	public static String formateDate(Date date) {
		return CommonUtil.formateDate(date);
	}

	/**
	 * 格式化日期成字符串（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String formateDatetime(Date date) {
		return CommonUtil.formateDatetime(date);
	}

	/**
	 * 格式化日期成自定义字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formateDateSelf(Date date, String pattern) {
		return CommonUtil.formateDatetime(date, pattern);
	}

	/**
	 * 格式化账务金额
	 * 
	 * @param num
	 * @return
	 */
	public static String formatFee(Long num) {
		return CommonUtil.formatFee(num);
	}

	/**
	 * 如果str为空则展示defVal
	 * 
	 * @param str
	 * @param defVal
	 * @return
	 */
	public static String showValue(String str, String defVal) {
		return CommonUtil.showValue(str, defVal);
	}

	/**
	 * 替换变量方法,将value字段中含有#{varKey}的地方替换成varValue
	 * 
	 * @param value
	 * @param varKey
	 * @param varValue
	 * @return
	 */
	public static String getReplaceValue(String value, String varKey,
			String varValue) {
		Map varMap = new HashMap();
		varMap.put(varKey, varValue);
		return CommonUtil.replaceParamValue(value, varMap);
	}
}
