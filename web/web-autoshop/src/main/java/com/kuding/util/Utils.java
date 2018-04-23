package com.kuding.util;

import java.sql.Date;
import java.util.Calendar;

public class Utils {
	
	/**
	 * 根据车辆注册日期判断是否需要提醒年检
	 * @param date 车辆注册日期 
	 * @return
	 */
	public static boolean isNeedAnnualVertification(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);
			
			//年检截止日期
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			
			//车辆购买时常
			long days = (deadline.getTime().getTime()-regCal.getTime().getTime())/1000/60/60/24;
			//六年内新车
			if(days/365<=6) {
				//新车每隔两年需要年检
				if(days/365%2==0) {
					Calendar curCal = Calendar.getInstance();
					//距离年检天数
					long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
					//距离年检前30天提醒
					if(daybeforEnd<=30 && daybeforEnd >=0 ) {
						return true;
					}
				}
			}else {
				Calendar curCal = Calendar.getInstance();
				//距离年检天数
				long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
				//距离年检前30天提醒
				if(daybeforEnd<=30 && daybeforEnd >=0 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 根据车辆注册日期判断是否需要提醒保险
	 * @param date
	 * @return
	 */
	public static boolean isNeedInsurance(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);

			//保险到期日
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));

			Calendar curCal = Calendar.getInstance();
			//距离保险到期日天数
			long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
			//前45天提醒
			if(daybeforEnd<=45 && daybeforEnd >=0 ) {
				return true;
			}
		}
		return false;
	}
	
	/**按半年一次保养进行提醒
	 * 判断是否需要提醒进行保养
	 * @param date
	 * @return
	 */
	public static boolean isNeedMaintaine(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);

			//到期日
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			
			Calendar curCal = Calendar.getInstance();
			//距离保养到期日天数
			long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
			//前10天提醒
			if(daybeforEnd<=10 && daybeforEnd >=0 ) {
				return true;
			}
			
			//年内第二次保养
			deadline.add(Calendar.MONTH, 6);
			daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
			//前10天提醒
			if(daybeforEnd<=10 && daybeforEnd >=0 ) {
				return true;
			}
			
		}
		return false;
	}

}
