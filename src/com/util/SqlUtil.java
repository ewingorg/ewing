package com.util;
/**
 * Sql工具类
 */
public class SqlUtil {
	/**
	 * 字符数组转成in查询条件
	 * @param array
	 * @return
	 */
	public static String array2InCondition(String[] array) {
		StringBuffer sb = new StringBuffer();
		for (String str : array) {
			sb.append("'").append(str).append("'").append(",");
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1)
				.toString();
	}
	/**
	 * 整形数组转成in查询条件
	 * @param array
	 * @return
	 */
	public static String array2InCondition(Integer[] array) {
		StringBuffer sb = new StringBuffer();
		for (Integer str : array) {
			sb.append(str).append(",");
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1)
				.toString();
	}
	 
}
