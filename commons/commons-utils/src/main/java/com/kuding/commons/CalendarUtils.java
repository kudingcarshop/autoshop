package com.kuding.commons;

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

}
