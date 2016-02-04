package com.ewing.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 工具类
 * 
 */
public class CommonUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommonUtil.class);

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 将资源文件中的中文格式化
	 * 
	 * @param sourceString
	 * @return
	 */
	public static String fromChinese(String sourceString) {
		if (sourceString == null || "".equals(sourceString)) {
			return "";
		} else {
			String result;
			try {
				result = new String(sourceString.getBytes("iso8859-1"));
			} catch (UnsupportedEncodingException e) {
				logger.error("字符串的中文化异常 fromChinese : " + e);
				result = "";
			}
			return result;
		}
	}

	/**
	 * 生成随机码
	 * 
	 * @param length
	 * @return
	 */
	public static String createRandomCode(int length) {
		Random random = new Random();
		String result = String.valueOf(Math.abs(random.nextLong()));// 绝对值

		if (result.length() >= length) {
			return result.substring(0, length);
		} else {
			return result + createRandomCode(length - result.length());
		}

	}

	/**
	 * 用于两个字符串合并
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String append(Object str1, Object str2) {
		return String.valueOf(str1) + String.valueOf(str2);
	}

	/**
	 * 生成随机码图片
	 * 
	 * @param rand
	 * @param heigh
	 * @param width
	 * @return
	 */
	public static BufferedImage createImage(String rand, int heigh, int width) {
		BufferedImage image = new BufferedImage(width, heigh,
				BufferedImage.TYPE_INT_RGB);
		Random random = new Random();

		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(new Color(0xfafafa));
		g.fillRect(0, 0, width, heigh);

		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, heigh - 1);

		g.setColor(Color.RED);
		for (int i = 0; i < rand.length(); i++) {
			g.setFont(new Font("Gungsuh", Font.PLAIN, heigh - 2
					- random.nextInt(1)));
			g.drawString(rand.substring(i, i + 1), 2 + width / rand.length()
					* i, heigh - 2 - random.nextInt(2));
		}

		g.setColor(Color.black);
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(heigh);
			g.drawOval(x, y, 0, 0);
		}
		g.dispose();
		return image;
	}

	/**
	 * 格式化日期成字符串（yyyy-MM-dd）
	 * 
	 * @param date
	 * @return
	 */
	public static String formateDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期成字符串（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String formateDatetime(Date date) {
		return datetimeFormat.format(date);
	}

	/**
	 * 格式化日期成自定义格式字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formateDatetime(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 把时间字符串格式化成Calendar类型（yyyy-MM-dd）
	 * 
	 * @param date
	 * @param pattem
	 * @return
	 */
	public static Calendar changeDate(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			logger.error("日期[" + date + "]格式化出错.", e);
		}

		return calendar;
	}

	/**
	 * 把时间字符串格式化成Calendar类型（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar changeDateTime(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(datetimeFormat.parse(date));
		} catch (ParseException e) {
			logger.error("日期[" + date + "]格式化出错.", e);
		}

		return calendar;
	}

	/**
	 * 获取足够小的时间
	 * 
	 * @return
	 */
	public static Calendar getZeroDate() {
		return changeDateTime("1000-01-01 00:00:00");
	}

	/**
	 * 得到monthCount个月之后的时间(天,小时,分,秒都为零)
	 * 
	 * @param monthCount
	 * @return
	 */
	public static Calendar getMonthFirst(int monthCount) {
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(year, month, 1, 0, 0, 0);
		calendar.set(Calendar.MONTH, month + monthCount);
		return calendar;
	}

	/**
	 * 返回非NULL的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String removeNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmptyStr(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * <p>
	 * 判断是否空
	 * </p>
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		if (value == null || "".equals(value.trim()))
			return true;
		return false;
	}

	/**
	 * XML中有些特殊字符需要转换，否则XML不能正确显示
	 * 
	 * @param strXML
	 * @return
	 */
	public static String dealXMLSpecialCharacter(String strXML) {
		String strDealedXML = strXML;
		strDealedXML = replaceStr(strDealedXML, "&", "&amp;");
		strDealedXML = replaceStr(strDealedXML, "<", "&lt;");
		strDealedXML = replaceStr(strDealedXML, ">", "&gt;");
		strDealedXML = replaceStr(strDealedXML, "\"", "&quot;");
		strDealedXML = replaceStr(strDealedXML, "'", "&apos;");
		strDealedXML = replaceStr(strDealedXML, "\n", " ");
		strDealedXML = replaceStr(strDealedXML, "\r", " ");
		return strDealedXML;
	}

	/**
	 * 将str中str1替换为str2
	 * 
	 * @param str
	 * @param str1
	 * @param str
	 * @return
	 */
	public static String replaceStr(String str, String str1, String str2) {
		if (str == null || str1 == null || str2 == null)
			return str;
		if (str1.equals(""))
			return str;

		StringBuffer sbReplaced = new StringBuffer("");
		String strSwap = new String(str);
		while (strSwap.indexOf(str1) >= 0) {
			int i = strSwap.indexOf(str1);
			sbReplaced.append(strSwap.substring(0, i));
			sbReplaced.append(str2);
			strSwap = strSwap.substring(i + str1.length());
		}
		sbReplaced.append(strSwap);
		return sbReplaced.toString();
	}

	static String repeatChar(char ch, int len) {
		char chArray[] = new char[len];
		for (int i = 0; i < len; ++i) {
			chArray[i] = ch;
		}
		return String.valueOf(chArray);
	}

	/**
	 * 返回一串参数中的值，如showMainIndex=true;name=话费查询;中，paramCode=name,则返回"话费查询"
	 * 
	 * @param param
	 * @param paramCode
	 * @param symbol
	 * @return
	 */
	public static String getParamValue(String param, String paramCode,
			char symbol) {
		if (StringUtils.isEmpty(param)) {
			return null;
		}
		int nPos = param.indexOf(paramCode + "=");
		if (nPos == -1) {
			return null;
		}
		int nPosBegin = nPos + (paramCode + "=").length();
		int nPosEnd = param.indexOf(symbol, nPos);
		if (nPosEnd == -1) {
			nPosEnd = param.length();
		}
		return param.substring(nPosBegin, nPosEnd);
	}

	/**
	 * getArrayFromStr 根据分隔符号，分割成字符数组,用set重复能够去掉。
	 * 
	 * @param str
	 * @return 数组
	 */
	public static String[] getArrayFromStr(String str) {
		String ary[] = null;
		// 用LinkedHashSet不自动排序
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		if (!StringUtils.isEmpty(str)) {
			String aryTmp[] = str.split(",");
			if (aryTmp != null && aryTmp.length > 0) {
				for (int i = 0; i < aryTmp.length; i++) {
					if (!StringUtils.isEmpty(aryTmp[i])) {
						set.add(aryTmp[i]);
					}
				}
			}
		}
		// 返回数组
		ary = (String[]) set.toArray(new String[0]);
		return ary;
	}

	/**
	 * getArrayFromStr 根据分隔符号，分割成字符数组,用set重复能够去掉。
	 * 
	 * @param str
	 * @param tocken
	 *            标记符号
	 * @return
	 */
	public static String[] getArrayFromStr(String str, String tocken) {
		String ary[] = null;
		// 用LinkedHashSet不自动排序
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		if (!StringUtils.isEmpty(str)) {
			String aryTmp[] = str.split(tocken);
			if (aryTmp != null && aryTmp.length > 0) {
				for (int i = 0; i < aryTmp.length; i++) {
					if (!StringUtils.isEmpty(aryTmp[i])) {
						set.add(aryTmp[i]);
					}
				}
			}
		}
		// 返回数组
		ary = (String[]) set.toArray(new String[0]);
		return ary;
	}

	/**
	 * <p>
	 * 获取URl中的请求参数
	 * </p>
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String getUrlParam(String url, String param) {
		if (url == null || url.length() == 0 || param == null
				|| param.length() == 0)
			return null;
		String sign1 = "?" + param + "=";
		String sign2 = "&" + param + "=";
		if (url.indexOf(sign1) != -1) {
			String partStr = url.substring(url.indexOf(sign1) + sign1.length());
			if (partStr.indexOf("&") == -1) {
				return partStr;
			}
			return partStr.substring(0, partStr.indexOf("&"));
		} else if (url.indexOf(sign2) != -1) {
			String partStr = url.substring(url.indexOf(sign2) + sign2.length());
			if (partStr.indexOf("&") == -1) {
				return partStr;
			}
			return partStr.substring(0, partStr.indexOf("&"));
		} else {
			return null;
		}
	}

	// 使用正则表达式验证Email地址的合法性
	public static boolean checkValidEmail(String strEmail) {
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return PatternMatch(strPattern, strEmail);
	}

	/**
	 * 用途：正则表达式检查函数
	 * 
	 * @param lpszPattern
	 *            ---检查模式
	 * @param lpszVerifyString
	 *            ---检查字符串
	 * @return true or false
	 */
	public static boolean PatternMatch(String strPattern, String strVerifyString) {
		try {
			Pattern p = Pattern.compile(strPattern);
			Matcher m = p.matcher(strVerifyString);
			return m.find();
		} catch (PatternSyntaxException e) {
			logger.error("无效的正则表达式：" + strPattern, e);
		}
		return false;
	}

	/**
	 * jiangwei 将空格清除后,检测字符串是否为空 2007-08-08
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (StringUtils.isEmpty(StringUtils.trim(str))) {
			return true;
		}
		return false;
	}

	/**
	 * 构建随机密码函数
	 * 
	 * @return
	 */
	public static String buildDynNmb() {
		int nLength = 6;
		Random rm = new Random();
		StringBuffer strPassWd = new StringBuffer();
		for (int i = 0; i < nLength; i++) {
			strPassWd.append(rm.nextInt(10) % 10);
		}
		return strPassWd.toString();
	}

	/**
	 * 构建随机字符串函数
	 * 
	 * @param length
	 *            要生成字符串的长度
	 * @return
	 */
	public static String buildDynStr(int length) {
		String result = "";
		if (length > 0) {
			String str = "ABCDEFGHIJKLMNPQRSTUVWXYZ0123456789abcdefghijklmnpqrstuvwxyz";
			Random random = new Random();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(60);
				sb.append(str.substring(index, index + 1));
			}
			result = sb.toString();
		}
		return result;
	}

	/**
	 * 解决SQL注入的问题
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeSql(String str) {
		if (str == null) {
			return null;
		}
		return StringUtils.replace(str, "'", "''");
	}

	/**
	 * 转换从WAP网关获取的MSISDN号码
	 * 
	 * @param msisdn
	 * @return
	 */
	public static String trimWapMsisdn(HttpServletRequest request) {

		String msisdn = request.getHeader("x-up-calling-line-id");

		if (msisdn == null || msisdn.equals("")) {
			return null;
		}
		msisdn = msisdn.trim();

		// 86打头的情况
		if (msisdn.length() == 13 && msisdn.startsWith("86")) {
			return msisdn.substring(2, msisdn.length());
		}

		return msisdn;

	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * 
	 * @param origin
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String substring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > length(origin)) {
			return origin + c;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把字符串转化成数字
	 * 
	 * @param source
	 *            源字符串
	 * @param defaultValue
	 *            出错时的默认值
	 */
	public static int parseInt(String source, int defaultValue) {
		// 对输入字符 source 修剪空前后字符
		source = StringUtils.trim(source);
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException ex) {
			// 出错，返回定义的默认值 defaultValue
			return defaultValue;
		}
	}

	/**
	 * 返回一串参数中的值，如showMainIndex=true;name=话费查询;中，paramCode=name,则返回"话费查询"
	 * 不区分大小写；返回值不是null
	 * 
	 * @purpose :
	 * @author : hesw@asiainfo.com
	 * @date : Apr 9, 2009
	 * @param param
	 * @param paramCode
	 * @param symbol
	 * @return
	 */
	public static String getSafeParamValue(String param, String paramCode,
			char symbol) {
		if (StringUtils.isEmpty(param) || StringUtils.isEmpty(paramCode)) {
			return "";
		}

		String upParam = param.toUpperCase();
		String upParamCode = paramCode.toUpperCase();

		int nPos = upParam.indexOf(upParamCode + "=");
		if (nPos == -1) {
			return "";
		}
		int nPosBegin = nPos + (upParamCode + "=").length();
		int nPosEnd = upParam.indexOf(symbol, nPosBegin);
		if (nPosEnd == -1) {
			nPosEnd = upParam.length();
		}

		// 返回时使用源字符串
		return param.substring(nPosBegin, nPosEnd);
	}

	/**
	 * 格式化账务金额
	 * 
	 * @param num
	 * @return
	 */
	public static String formatFee(Long num) {
		return new java.text.DecimalFormat("#0").format(num / 100)
				+ "."
				+ new java.text.DecimalFormat("#00")
						.format(Math.abs(num) % 100);
	}

	/**
	 * 将客户端传入的金额转化成后台需要的长整形数字
	 * 
	 * @param strFee
	 * @return
	 */
	public static long changeFee(String strFee) {

		try {
			return (long) (Float.parseFloat(strFee) * 100);
		} catch (Exception e) {
			logger.error("金额[" + strFee + "]转换错误.", e);
			return 0;
		}
	}

	/**
	 * 如果str为空则展示defVal
	 * 
	 * @param str
	 * @param defVal
	 * @return
	 */
	public static String showValue(String str, String defVal) {
		if (str != null && !str.equals("")) {
			return str;
		}

		return defVal;
	}

	/**
	 * 替换掉html标记符为空字符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHTML(String str) {
		if (str == null) {
			return null;
		}

		return str.replaceAll("<([^>]+)>", "");
	}

	/**
	 * 从菜单知识参数替换定义的变量值, 说明: 格式为: #{password} map中放入passowrd=2222,就可以把字符替换
	 * 
	 * @param str
	 * @param valueMap
	 * @return
	 */
	public static String replaceParamValue(String str, Map valueMap) {
		Map<String, String> replaceMap = new HashMap<String, String>();
		find(str, replaceMap, valueMap);
		return replaceValue(str, replaceMap);
	}

	/**
	 * 寻找需要替换的字段,如:{retcode}
	 * 
	 * @param str
	 * @param keylist
	 * @param valuelist
	 * @param valueMap
	 */
	private static void find(String str, Map<String, String> replaceMap,
			Map valueMap) {
		String regEx = "\\#\\{([^\\}]+)\\}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		boolean rs = matcher.find();
		if (rs) {
			String replaceStr = matcher.group(0);
			String key = matcher.group(1);

			str = str.substring(matcher.end());
			String value = "";
			if (valueMap.get(key) != null) {
				value = String.valueOf(valueMap.get(key));
				replaceMap.put(replaceStr, value);
			}
			find(str, replaceMap, valueMap);
		}
	}

	/**
	 * 替换值
	 * 
	 * @param str
	 * @param keylist
	 * @param valuelist
	 * @return
	 */
	private static String replaceValue(String str,
			Map<String, String> replaceMap) {
		String temp = str;
		for (Iterator iterator = replaceMap.keySet().iterator(); iterator
				.hasNext();) {
			String strKey = (String) iterator.next();
			String strValue = replaceMap.get(strKey);
			// 去掉value的句号或者逗号
			if (!StringUtils.isBlank(strValue)) {
				temp = temp.replace(strKey, strValue);
			} else {
				temp = temp.replace(strKey, "");
			}
		}
		return temp;
	}

	/**
	 * 得到真实的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (isEmpty(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isEmpty(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isEmpty(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null) {
			ip = "";
		}
		return ip;
	}

	/**
	 * 字符组转整形数组
	 * 
	 * @param strArray
	 * @return
	 */
	public static Integer[] strArray2InArray(String[] strArray) {
		if (strArray == null)
			return null;
		List<Integer> intList = new ArrayList<Integer>();
		for (String str : strArray) {
			intList.add(Integer.parseInt(str));
		}
		return intList.toArray(new Integer[intList.size()]);
	}

}
