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
	 * 推算车辆年间日期
	 * @param date
	 * @return
	 */
	public static Date assumeVertificationDate(Date date) {
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
				//不到两年间隔，则下一年年检
				if(days/365%2>0) {
					deadline.add(Calendar.YEAR, 1);
				}
			}
			
			return new Date(deadline.getTimeInMillis());
		}
		
		return null;
	}
	
	/**
	 * 推测是否需要提醒保险到期
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
	
	/**
	 * 推算车辆保险日期
	 * @param date
	 * @return
	 */
	public static Date assumeInsuranceDate(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);

			//保险到期日
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			return new Date(deadline.getTimeInMillis());
		}
		return null;
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
	
	/**
	 * 推算下一个保养日期
	 * @param date
	 * @return
	 */
	public static Date assumeMaintaineDate(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);
			
			//当前
			Calendar curcal = Calendar.getInstance();

			//到期日
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			
			//到期日之前
			Calendar preCal = Calendar.getInstance();
			preCal.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			preCal.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			preCal.add(Calendar.MONTH, -6);
			long diff = preCal.getTimeInMillis()-curcal.getTimeInMillis();
			if(diff>=0) {
				return new Date(preCal.getTimeInMillis());
			}
			
			diff = deadline.getTimeInMillis() - curcal.getTimeInMillis();
			if(diff>=0) {
				return new Date(deadline.getTimeInMillis());
			}
			
			//到期日之后
			Calendar afterCal = Calendar.getInstance();
			afterCal.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			afterCal.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			afterCal.add(Calendar.MONTH, 6);
			diff = afterCal.getTimeInMillis() - curcal.getTimeInMillis();
			if(diff>=0) {
				return new Date(afterCal.getTimeInMillis());
			}
			
		}
		return null;
	}

}
