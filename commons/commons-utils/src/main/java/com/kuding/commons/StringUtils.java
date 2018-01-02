package com.kuding.commons;

public class StringUtils {
	
	/**
	 * 判断是否空字符
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str != null && str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否空白字符
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str != null && str.length() > 0){
			return true;
		}
		return false;
	}

}
