package com.kuding.commons;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalendarUtils {
	
	/**
	 * 生成今天0点日期
	 * @return
	 */
	public static Calendar getToday() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today;
	}
	
	/**
	 * 生成明天0点日期
	 * @return
	 */
	public static Calendar getTomorrow() {
		Calendar cal = CalendarUtils.getToday();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal;
	}
	
	/***
	 * 生成当月首天0点日期,如:2018-1-1 0:0:0
	 * @return
	 */
	public static Calendar getThisMonth() {
		Calendar cal = CalendarUtils.getToday();
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal;
	}
	
	/**
	 * 判断两个时间是否同一天
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isSameDate(Timestamp time1,Timestamp time2) {
		if(time1 != null && time2 != null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(time1);
			cal1.set(Calendar.HOUR_OF_DAY, 0);
			cal1.set(Calendar.MINUTE, 0);
			cal1.set(Calendar.SECOND, 0);
			cal1.set(Calendar.MILLISECOND, 0);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(time2);

			cal2.set(Calendar.HOUR_OF_DAY, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.SECOND, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			return cal2.compareTo(cal1) == 0;
		}
		
		return false;
	}

}
