package com.ewing.web.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class DateFormat {
	protected static final String sDefaultDateFormat = "yyyy-MM-dd HH:mm:ss";

	// 日期时间全格式 24小时制
	public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String DATETIME_FORMAT2 = "yyyyMMddHHmmss";

	public static String DATE_ZEROTIME_FORMAT = "yyyy-MM-dd 00:00:00";

	public static String DATE_ZEROTIME_FORMAT2 = "yyyyMMdd000000";

	public static String DATE_FULLTIME_FORMAT = "yyyy-MM-dd 23:59:59";

	public static String DATE_FULLTIME_FORMAT2 = "yyyyMMdd235959";

	// 日期时间全格式 12小时制
	public static String DATETIME12_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public static String DATETIME12_FORMAT2 = "yyyyMMddhhmmss";

	// 日期全格式
	public static String DATE_FORMAT = "yyyy-MM-dd";

	public static String DATE_FORMAT2 = "yyyyMMdd";

	public static String DATE_FORMAT_CN = "yyyy年MM月dd日";

	// 年月
	public static String YEAR_MONTH_FORMAT = "yyyy-MM";

	public static String YEAR_MONTH_FORMAT2 = "yyyyMM";

	public static String YEAR_MONTH_FORMAT_CN = "yyyy年MM月";

	// 某年某月的第一天
	public static String YEAR_MONTH_FIRSTDAY = "yyyy-MM-01";

	// 年、月、日
	public static String YEAR_FORMAT = "yyyy";

	public static String MONTH_FORMAT = "MM";

	public static String DAY_FORMAT = "dd";

	// 时间全格式 24小时制
	public static String TIME_FORMAT = "HH:mm:ss";

	public static String TIME_FORMAT2 = "HHmmss";

	// 时间全格式 12小时制
	public static String TIME12_FORMAT = "hh:mm:ss";

	public static String TIME12_FORMAT2 = "hhmmss";

	// 营业cs版本日期时间格式
	public static String DATETIME_SLASH_FORMAT = "yyyy/MM/dd HH:mm:ss";

	public static String DATE_SLASH_FORMAT = "yyyy/MM/dd";

	private static final Logger log = Logger.getLogger(DateFormat.class);

	public DateFormat() {
	}

	public static String cvtDateString(String strDate, String strOldFormat,
			String strNewFormat) {
		try {
			SimpleDateFormat sdfOld = new SimpleDateFormat(strOldFormat);
			SimpleDateFormat sdfNew = new SimpleDateFormat(strNewFormat);
			Date date = sdfOld.parse(strDate);
			return sdfNew.format(date);
		} catch (Exception ex) {
			return "";
		}

	}

	public static Date stringToDate(String inputDate, String pattern) {
		if (inputDate == null || inputDate.equalsIgnoreCase(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(inputDate, new ParsePosition(0));
		} catch (Exception ex) {
			SimpleDateFormat sdfs = new SimpleDateFormat(DATE_SLASH_FORMAT);
			date = sdfs.parse(inputDate, new ParsePosition(0));
			return date;
		}
		return date;
	}

	public static Date stringToDate(String inputDate) {
		if (inputDate == null || inputDate.equalsIgnoreCase(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(inputDate, new ParsePosition(0));
		} catch (Exception ex) {
			SimpleDateFormat sdfs = new SimpleDateFormat(DATE_SLASH_FORMAT);
			date = sdfs.parse(inputDate, new ParsePosition(0));
			return date;
		}
		return date;
	}

	public static String DateToString(java.util.Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(time);
	}

	public static String DateToString(java.util.Date time, String strPattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		return sdf.format(time);
	}

	/**
	 * 根据yyyy-mm格式的月份字符串得到yyyy-mm-dd|yyyy-mm-dd格式的月开始日期和月结束日期字符串。
	 * 
	 * @param strMon
	 *            String
	 * @return String
	 */
	public static String monToStartEndDate(String strMon) {
		if (strMon == null || strMon.length() != 7 || strMon.charAt(4) != '-')
			return "";

		int iYear = 0;
		int iMonth = 0;

		try {
			iYear = Integer.parseInt(strMon.substring(0, 4));
			iMonth = Integer.parseInt(strMon.substring(5, 7));
		} catch (Exception exp) {
			return "";
		}
		if (iYear < 1970 || iYear > 2999 || iMonth < 1 || iMonth > 12)
			return "";

		String strStartDate = strMon + "-01";
		String strNextMonStartDate = new String();

		if (iMonth == 12)
			strNextMonStartDate = "" + (iYear + 1) + "-01-01";
		else {
			String strNextMon = new String();
			if (10 > (iMonth + 1))
				strNextMon = "0" + (iMonth + 1);
			else
				strNextMon = "" + (iMonth + 1);
			strNextMonStartDate = strMon.substring(0, 4) + "-" + strNextMon
					+ "-01";
		}

		String strEndDate = DateToString(new java.sql.Date(
				(stringToDate(strNextMonStartDate)).getTime() - 86400000));

		return strStartDate + "|" + strEndDate;

	}

	/**
	 * <p>
	 * 判断日期格式是否合法
	 * </p>
	 * 
	 * @param s
	 * @param cTimeFormat
	 * @return
	 */
	public static boolean isValidDate(String s,
			java.text.SimpleDateFormat cTimeFormat) {
		try {
			if (cTimeFormat == null) {
				cTimeFormat = new java.text.SimpleDateFormat(sDefaultDateFormat);
			}
			cTimeFormat.parse(s);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 返回两个时间相差的月份
	 * 
	 * @param g1
	 * @param g2
	 * @return
	 */
	public static int getDiffMonths(Calendar g1, Calendar g2) {
		int elapsed = 0;
		GregorianCalendar gc1, gc2;
		if (g2.after(g1)) {
			gc2 = (GregorianCalendar) g2.clone();
			gc1 = (GregorianCalendar) g1.clone();
		} else {
			gc2 = (GregorianCalendar) g1.clone();
			gc1 = (GregorianCalendar) g2.clone();
		}

		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);
		gc1.clear(Calendar.DATE);
		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);
		gc2.clear(Calendar.DATE);

		while (gc1.before(gc2)) {
			gc1.add(Calendar.MONTH, 1);
			elapsed++;
		}
		return elapsed;
	}

	/**
	 * 把Calendar类型的日期转换成指定格式的字符串。
	 * 
	 * @param calendar
	 * @param strFormatTo
	 * @return
	 */
	public static String cvtFormattedCalendar(Calendar calendar,
			String strFormatTo) {
		if (calendar == null) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			if (Integer.parseInt(formatter.format(calendar.getTime())) < 1900) {
				return "";
			} else {
				formatter = new SimpleDateFormat(strFormatTo);
				return formatter.format(calendar.getTime());
			}
		} catch (Exception e) {
			log.error("转换日期字符串格式时出错;" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得目前的时间
	 * 
	 * @return
	 */
	public static Calendar getCurrentCalendar() {
		return new GregorianCalendar();
	}

	/**
	 * 比较时间是否相等
	 * 
	 * @return
	 */
	public static boolean equalCalendar(Calendar calendar1, Calendar calendar2) {
		if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
				&& calendar1.get(Calendar.MONTH) == calendar2
						.get(Calendar.MONTH)
				&& calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE)) {
			return true;
		}
		return false;
	}

	/**
	 * 输入秒数,得到时分秒 从3.0网厅中dataformat中迁移到此
	 * 
	 * @param second
	 * @return
	 */
	public static String calTime(double tmpSecond) {
		int second = (int) tmpSecond;
		int h = 0;
		int d = 0;
		int s = 0;
		if (second < 1) {
			return "0秒";
		}
		int temp = second % 3600;
		if (second > 3600) {
			h = second / 3600;
			if (temp != 0) {
				if (temp > 60) {
					d = temp / 60;
					if (temp % 60 != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			d = second / 60;
			if (second % 60 != 0) {
				s = second % 60;
			}
		}
		if (h == 0 && d == 0) {
			return s + "秒";
		} else if (h == 0) {
			return d + "分" + s + "秒";
		} else {
			return h + "小时" + d + "分" + s + "秒";
		}
	}
}
