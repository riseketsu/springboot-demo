/**
 * Copyright © 2018 RSKT. All rights reserved. 
 */
package com.rskt.demo.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

/**
 * Class:DateUtils.java<br>
 *
 * Class Summary：<br>
 * 
 * Class Feature:<br>
 *
 * @Author rskt
 * @CreateDate 2018-08-07
 *
 */
public class DateUtils {

	public static void main(String args[]) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = stringToDate("2018-12-31", "yyyy-MM-dd");
		System.out.println(df.format(d));
	}

	/**
	 * 
	 * 
	 * @param date 日期
	 * @param pattern 格式
	 * @return
	 */
	public static Date stringToDate(String date, String pattern) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		if ("00000000".equals(date)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINESE);
		Date d = null;
		try {
			d = simpleDateFormat.parse(date.trim());
		} catch (ParseException e) {
		}
		return d;
	}

	public static Date LongToDate(Long date, String pattern) {
		Date date1 = new Date();
		if (date != null && date != 0) {
			date1.setTime(date);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("", Locale.CHINESE);
			simpleDateFormat.applyPattern(pattern);
		}
		return date1;
	}

	/**
	 * 日期转换成毫
	 * 
	 * @param date 日期或时
	 * @param pattern 格式
	 * @return
	 */
	public static Long stringToLong(String date, String pattern) {
		if ("00000000".equals(date)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINESE);
		Date date1 = null;
		try {
			if (StringUtils.isNotBlank(date)) {
				date1 = simpleDateFormat.parse(date);
			}
		} catch (Exception e) {
		}
		return (date1 == null ? 0L : date1.getTime());
	}

	/**
	 * 获取当前时间并转换成毫秒
	 * 
	 * @return
	 */
	public static Long sysTimeToLong() {
		Date date = new Date();
		return date.getTime();
	}

	public static String longToString(Long date, String pattern) {
		Date date1 = new Date();
		String timeStr = "";
		if (date != null && date != 0) {
			date1.setTime(date);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("", Locale.CHINESE);
			simpleDateFormat.applyPattern(pattern);
			timeStr = simpleDateFormat.format(date1);
		}
		return timeStr;
	}

	public static String dateToString(Date date, String pattern) {
		String timeStr = "";
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("", Locale.CHINESE);
			simpleDateFormat.applyPattern(pattern);
			timeStr = simpleDateFormat.format(date);
		}
		return timeStr;
	}

	/**
	 * 是否是周的最后一天（周日）
	 * 
	 * @param calendar
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean isLastDayOfWeek(Calendar calendar) {
		return calendar.get(calendar.DAY_OF_WEEK) == calendar.SUNDAY;
	}

	/**
	 * 是否是月的最后一天
	 * 
	 * @return
	 */
	public static boolean isLastDayOfMonth(Calendar calendar) {
		return false;
	}

	/**
	 * 是否是中旬的最后一天
	 * 
	 * @return
	 */
	public static boolean isLastDayOfMidMonth(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_MONTH) == 15;
	}

	public static String getStrByPattern(Date date, String str) {
		int index1 = StringUtils.indexOf(str, "%d{");
		if (index1 != -1) {
			String before = StringUtils.substringBefore(str, "%d{");
			String after = StringUtils.substring(str, index1 + 3, str.length());
			int index2 = StringUtils.indexOf(str, "}");
			String datef = StringUtils.substring(after, 0, index2);
			String end = StringUtils.substring(after, index2 + 1, after.length());
			datef = DateUtils.dateToString(date, datef);
			return before + datef + end;
		}
		return str;
	}

	public static String deToString(String cutOffDate) {
		Date date = new Date();
		// 注意format的格式要与日期String的格式相匹配
		DateFormat dateFormat1 = null;
		if (cutOffDate.indexOf(":") != -1) {
			dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		}

		DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		try {
			date = dateFormat1.parse(cutOffDate);
		} catch (Exception e) {
		}
		return dateFormat2.format(date);
	}

	// public static void main(String[] args) {
	// String s=getStrByPattern(new Date(),"${bankId}-%d{yyyyMMdd}-01.txt");
	// System.out.println(s);
	// }

	public static String getHeaderDateTime(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		String datestr = sdf.format(new Date());
		String[] ds = datestr.split(" ");
		datestr = ds[0] + "T" + ds[1];
		return datestr;
	}

	public static String getCurrentSystemDateTime(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		return sdf.format(new Date());
	}

	public static String getCurrentSystemTime(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");// 设置时间格式
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		return sdf.format(new Date());
	}

	public static String getCurrentSystemDateTime() {
		SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return currentDate.format(new Date());
	}

	public static String getCurrentSystemDate() {
		SimpleDateFormat currentDate = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		return currentDate.format(new Date());
	}

	public static String getCurrentSystemTime() {
		SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");// 设置时间格式
		return currentTime.format(new Date());
	}

	/**
	 * Date转ISO时间
	 * 
	 * @param dtTime
	 * @return
	 */
	public static String TransferToISODateTime(Date dtTime) {
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dtTime);
		String[] ds = dateStr.split(" ");
		dateStr = ds[0] + "T" + ds[1];
		return dateStr;
	}

	/**
	 * ISO时间（String型）转Date型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date TransferToDateTime(String dateStr) {
		String[] ds = dateStr.split("T");
		dateStr = ds[0] + " " + ds[1];
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = null;
		try {
			date = fmt.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 根据时区获取日期
	 * 
	 * @param zone
	 * @return
	 */
	public static String getDateTime(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		return sdf.format(new Date());
	}

	public static String getYYMMDD(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		return sdf.format(new Date());
	}

	/**
	 * 根据时区获取日期
	 * 
	 * @param zone
	 * @return
	 */
	public static String getZoneServiceTime(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		return sdf.format(new Date());
	}

	/**
	 * 根据时区获取时间
	 * 
	 * @param zone
	 * @return
	 */
	public static String getCurrentTime(String zone) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		sdf.setTimeZone(TimeZone.getTimeZone(zone));
		return sdf.format(new Date());
	}

	/**
	 * HHmmss sample : 115302
	 * 
	 * @Date 2016-4-22 10:50:56
	 * @return
	 */
	public static String getHHmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(date);
	}

	/**
	 * HH:mm:ss sample : 11:53:02
	 * 
	 * @Date 2016-4-22 10:50:56
	 * @return
	 */
	public static String getHHmmss2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * YYYYMMDD
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDD2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 根据失去获取Date型当前日期
	 * 
	 * @param zone
	 * @return
	 */
	// public static Date getCurrentSystemDateTimeWithDateType(String zone) {
	// Date zoneDate = null;
	// try {
	// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	// sdf1.setTimeZone(TimeZone.getTimeZone(zone));
	// String dateString = sdf1.format(new Date());
	// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	//
	// zoneDate = sdf2.parse(dateString);
	// } catch (Exception ex){
	// ex.printStackTrace();
	// }
	// return zoneDate;
	// }
	public static Date getCurrentSystemDateTimeWithDateType(String _timeZone) {
		TimeZone timeZone = null;
		if (StringUtils.isEmpty(_timeZone)) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = TimeZone.getTimeZone(_timeZone);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// 设置日期格式
		sdf.setTimeZone(timeZone);
		String dateString = sdf.format(new Date());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// 设置日期格式
		Date zoneDate = null;
		try {
			zoneDate = sdf2.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// TimeZone.setDefault(timeZone);
		return zoneDate;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param _timeZone
	 * @return
	 */
	public static String getSystemDateTime(String _timeZone) {
		TimeZone timeZone = null;
		if (StringUtils.isBlank(_timeZone)) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = TimeZone.getTimeZone(_timeZone);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// 设置日期格式
		sdf.setTimeZone(timeZone);
		String dateString = sdf.format(new Date());
		return dateString;
	}

	/**
	 * 获得主机IP
	 *
	 * @return String
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机ip地址，并自动区分Windows还是linux操作系统
	 * 
	 * @return String
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if (ip.getHostAddress().indexOf(":") == -1 && !ip.getHostAddress().startsWith("127")) {
							bFindIP = true;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	/**
	 * get hour minutes sample : 1153
	 * 
	 * @Date 2016-12-05 17:50:56
	 * @return
	 */
	public static String getHHMM(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		return sdf.format(date);
	}

	public static String convertCurrentTimeZoneToUTC(String currDateStr) {

		if (null == currDateStr)
			return null;

		String converted_date = "";
		try {

			// nansion change
			DateFormat currentTFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			currentTFormat.setTimeZone(TimeZone.getTimeZone(getCurrentTimeZone()));

			Date currDate = currentTFormat.parse(currDateStr);

			DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			converted_date = utcFormat.format(currDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return converted_date;
	}

	public static String convertCurrentTimeZoneToUTCForAhHeader(String currDateStr) {

		if (null == currDateStr)
			return null;

		String converted_date = "";
		try {

			// nansion change
			DateFormat currentTFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			currentTFormat.setTimeZone(TimeZone.getTimeZone(getCurrentTimeZone()));

			Date currDate = currentTFormat.parse(currDateStr);

			DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			converted_date = utcFormat.format(currDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return converted_date;
	}

	public static String convertCurrentTimeZoneToUTC(String currDateStr, String currFormatStr, String utcFormatStr) {

		if (null == currDateStr || null == currFormatStr || utcFormatStr == null)
			return null;

		String converted_date = "";
		try {
			DateFormat currentTFormat = new SimpleDateFormat(currFormatStr);
			currentTFormat.setTimeZone(TimeZone.getTimeZone(getCurrentTimeZone()));

			Date currDate = currentTFormat.parse(currDateStr);

			DateFormat utcFormat = new SimpleDateFormat(utcFormatStr);
			utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			converted_date = utcFormat.format(currDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return converted_date;
	}

	// get the current time zone
	public static String getCurrentTimeZone() {
		TimeZone tz = Calendar.getInstance().getTimeZone();
		return tz.getID();
	}
}
