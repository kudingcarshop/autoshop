package com.kuding.commons.login;

public class Constants {
	
	/**登录用户*/
	public static final String KEY_LOGIN_USER = "login_user";
	
	/**登录页面*/
	public static final String LOGIN_URL = "/jsp/pub/login.jsp";
	
	/**是否需要登录开关*/
	public static final boolean IS_NEED_LOGIN = true;
	
	
	/**用户状态：正常*/
	public static final Integer STATE_NORMAL = 0;
	
	/**用户状态：已删除*/
	public static final Integer STATE_DELETED = 1;
	
	
	/**平台系统管理员*/
	public static final String ROLE_TYPE_SYS_ADMIN = "sys_admin";
	
	/**车行管理员*/
	public static final String ROLE_TYPE_GARAGE_ADMIN = "garage_admin";
	
	/**车主客户*/
	public static final String ROLE_TYPE_PUB_CUSTOMER = "pub_customer";
	
	/**车行接待员*/
	public static final String ROLE_TYPE_GARAGE_RECEPTIONIST = "garage_receptionist";
	
}
