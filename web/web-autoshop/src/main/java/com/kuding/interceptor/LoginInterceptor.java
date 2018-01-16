package com.kuding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.login.Constants;

public class LoginInterceptor implements HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!Constants.IS_NEED_LOGIN || request.getSession()
				.getAttribute(Constants.KEY_LOGIN_USER) != null) {
			logger.info("LoginInterceptor->preHandle login successfully");
			return true;
		}else {
			logger.info("LoginInterceptor->preHandle login fail");
			request.getRequestDispatcher(Constants.LOGIN_URL).forward(request, response);
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
