package com.kuding.garage.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

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

	/**
	 * 汇集错误信息
	 * @param bindingResult
	 * @return
	 */
	public String combineErrors(BindingResult bindingResult) {
		if(bindingResult != null && bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			StringBuffer sb = new StringBuffer();
			for(ObjectError err : errors) {
				sb.append(err.getDefaultMessage());
				sb.append("\\n\\r");
			}
			return sb.toString();
		}
		
		return null;
	}
}
