package com.kuding.garage.action;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

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
			for(int i = 0; i<errors.size();i++) {
				ObjectError err = errors.get(i);
				if(i > 0) {
					sb.append("<br>");
				}
				sb.append(err.getDefaultMessage());
			}
			return sb.toString();
		}
		
		return null;
	}
	
	/**
	 * 根据可获取message信息
	 * @param req
	 * @param key
	 * @return
	 */
	public String getMessage(HttpServletRequest req,String key) {
		WebApplicationContext context = RequestContextUtils.findWebApplicationContext(req, req.getServletContext());
		return context.getMessage(key, null, Locale.CHINA);
	}
}
