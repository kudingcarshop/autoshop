package com.kuding.garage.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kuding.commons.Constants;
import com.kuding.commons.login.UserInfo;

public class BasicAction {
	
	private Logger logger = Logger.getLogger(getClass());
	
	public UserInfo getUserInfo(HttpSession session) {
		UserInfo user = null;
		if(session != null) {
			user = (UserInfo) session.getAttribute(Constants.KEY_LOGIN_USER);
		}
		return user;
	}
	
	
	public Logger getLogger() {
		return logger;
	}

}
