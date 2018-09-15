package com.zglc.reconciliation.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final DateTime START = DateTime.parse("2014-01-01");
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm:ss";
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String NO_SEPARATOR_DATETIME_PATTERN = "yyyyMMddHHmmss";

	/**
	 * 计算指定日期距离2014-01月份的间隔月数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthInterval(Date date) {
		return getMonthInterval(START.toDate(), date);
	}

	/**
	 * 计算两个日期之间的间隔月数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonthInterval(Date start, Date end) {
		DateTime s = new DateTime(start);
		DateTime e = new DateTime(end);

		return (e.getYear() - s.getYear()) * 12 + e.getMonthOfYear();
	}

	public static int getDayInterval(Date startDate, Date endDate) {
		DateTime start = new DateTime(startDate.getTime());
		DateTime end = new DateTime(endDate.getTime());
		Days days = Days.daysBetween(start, end);
		return days.getDays();
	}

	/**
	 * 计算指定日期的当周第一天，如2015-06-03 00:00:00
	 * 
	 * @param timepoint
	 * @return
	 */
	public static Date getWeekStart(Date timepoint) {
		DateTime dt = new DateTime(timepoint);
		return dt.dayOfWeek().withMinimumValue().toDate();
	}

	/**
	 * 计算指定日期的当周最后一天，如2015-06-09 23:59:59
	 * 
	 * @param timepoint
	 * @return
	 */
	public static Date getWeekEnd(Date timepoint) {
		DateTime dt = new DateTime(timepoint);
		Date end = dt.dayOfWeek().withMaximumValue().toDate();
		return buildMaxOfDate(end);
	}

	/**
	 * 计算指定日期的当月第一天，如2015-08-01 00:00:00
	 * 
	 * @param timepoint
	 * @return
	 */
	public static Date getMonthStart(Date timepoint) {
		DateTime dt = new DateTime(timepoint);
		Date start = dt.dayOfMonth().withMinimumValue().toDate();
		return buildMinOfDate(start);
	}

	/**
	 * 计算指定日期的当月最后一天，如2015-08-31 23:59:59
	 * 
	 * @param timepoint
	 * @return
	 */
	public static Date getMonthEnd(Date timepoint) {
		DateTime dt = new DateTime(timepoint);
		Date end = dt.dayOfMonth().withMaximumValue().toDate();
		return buildMaxOfDate(end);
	}

	/**
	 * 日期按日加减计算
	 * 
	 * @param timepoint
	 * @param period
	 * @return
	 */
	public static Date addDatesByDay(Date timepoint, int period) {
		DateTime dt = new DateTime(timepoint);
		return dt.plusDays(period).toDate();
	}

	/**
	 * 日期按周加减计算
	 * 
	 * @param timepoint
	 * @param period
	 * @return
	 */
	public static Date addDatesByWeek(Date timepoint, int period) {
		DateTime dt = new DateTime(timepoint);
		return dt.plusWeeks(period).toDate();
	}

	/**
	 * 日期按月加减计算
	 * 
	 * @param timepoint
	 * @param period
	 * @return
	 */
	public static Date addDatesByMonth(Date timepoint, int period) {
		DateTime dt = new DateTime(timepoint);
		return dt.plusMonths(period).toDate();
	}

	/**
	 * 计算指定日期的最小时间，如2015-08-01 00:00:00
	 * 
	 * @param d
	 * @return
	 */
	public static Date buildMinOfDate(Date d) {
		DateTime time = new DateTime(d);
		return time.millisOfDay().withMinimumValue().toDate();
	}

	/**
	 * 计算指定日期的最大时间，如2015-08-01 23:59:59
	 * 
	 * @param d
	 * @return
	 */
	public static Date buildMaxOfDate(Date d) {
		DateTime time = new DateTime(d);
		return time.millisOfDay().withMaximumValue().toDate();
	}

	/**
	 * 计算指定时间内上一小时最小分钟和秒，如2015-08-01 00:00:00
	 * 
	 * @param d
	 * @return
	 */
	public static Date buildMinMinuteOfHour(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 计算指定时间内上一小时最大分钟和秒，如2015-08-01 00:59:59
	 * 
	 * @param d
	 * @return
	 */
	public static Date buildMaxMinuteOfHour(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 转换日期为字符串类型（pattern自行指定）
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
		try {
			DateTime dt = new DateTime(date.getTime());
			return dt.toString(pattern);
		} catch (Exception e) {
			logger.info("Failed to output as string, pattern is: " + pattern);
			return null;
		}
	}

	/**
	 * 转换日期为字符串（不带时间）
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		if(date == null){
			return null;
		}
		
		return date2String(date, DATE_PATTERN);
	}
	
	/**
	 * 转换日期为字符串（仅时间）
	 * 
	 * @param date
	 * @return
	 */
	public static String time2String(Date date) {
		return date2String(date, TIME_PATTERN);
	}
	
	/**
	 * 转换日期为字符串（带时间）
	 * 
	 * @param date
	 * @return
	 */
	public static String datetime2String(Date date) {
		return date2String(date, DATETIME_PATTERN);
	}

	/**
	 * 转换long为字符串（带时间）
	 * 
	 * @param date
	 * @return
	 */
	public static String long2String(long date) {
	SimpleDateFormat sdf= new SimpleDateFormat(DATE_PATTERN);
	//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
	Date dt = new Date(date);
	String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
	return sDateTime;
	}
	
	
	/**
	 * 转换字符串为日期格式（pattern自行指定）
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String str, String pattern) {
		try {
			DateTime dt = DateTime.parse(str,
					DateTimeFormat.forPattern(pattern));
			return dt.toDate();
		} catch (Exception e) {
			logger.error("Failed parse to Date type, string is " + str, e);
			return null;
		}

	}

	/**
	 * 判断两个日期是否是同一日期
	 * 
	 * @param data1
	 * @param data2
	 * @return
	 */
	public static boolean compareToDate(Date data1, Date data2) {
		if (data1 == null && data2 == null) {
			return true;
		}
		if (data1 == null || data2 == null) {
			return false;
		}
		try {
			String data1Str = date2String(data1);
			String data2Str = date2String(data2);
			return data1Str.equals(data2Str);
		} catch (Exception e) {
			logger.error("Failed compare to Date.", e);
			return false;
		}

	}

	/**
	 * 转换字符串为不带时间的日期格式，如"2015-08-10"
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		return str2Date(str, DATE_PATTERN);
	}

	/**
	 * 转换字符串为带时间的日期格式，如"2015-08-10 20:11:20"
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Datetime(String str) {
		return str2Date(str, DATETIME_PATTERN);
	}

	/**
	 * 获取指定日期指定Field 值
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public static int get(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getYesterday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return c.getTime();
	}

	public static Date getDate(Date date, int addMminute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + addMminute);
		return c.getTime();
	}

	public static int dayForWeek(String statDate) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(statDate));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	
	public static Date getPrevMonthStartData(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return DateUtil.buildMinOfDate(calendar.getTime());
	}
	
	public static Date getPrevMonthEndData(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return DateUtil.buildMaxOfDate(calendar.getTime());
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getDayInterval(str2Datetime("2017-01-01 00:00:00"), str2Datetime("2017-01-31 23:59:59")));
		System.out.println(str2Datetime("2017-01-01 00:00:00").getTime());
		System.out.println(str2Datetime("2017-01-31 23:59:59").getTime());
	}

}
