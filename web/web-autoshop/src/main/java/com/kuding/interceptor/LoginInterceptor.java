package com.kuding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.Constants;
import com.kuding.commons.KdLogger;

public class LoginInterceptor implements HandlerInterceptor {
	
	private KdLogger logger = KdLogger.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!Constants.IS_NEED_LOGIN || request.getSession()
				.getAttribute(Constants.KEY_LOGIN_USER) != null) {
			logger.i("LoginInterceptor->preHandle login successfully");
			return true;
		}else {
			logger.i("LoginInterceptor->preHandle login fail");
			if(Constants.IS_DEBUG) {
				request.setAttribute("req_uri", getReqUri(request));
			}
			request.getRequestDispatcher(Constants.LOGIN_URL).forward(request, response);
			return false;
		}
	}
	
	private String getReqUri(HttpServletRequest request) {
		String reqUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		if(!reqUri.equalsIgnoreCase(contextPath+"/")) {
			int index = reqUri.indexOf(contextPath);
			if(index >= 0) {
				reqUri = reqUri.substring(index+contextPath.length());
			}
		}else {
			reqUri = "";
		}
		return reqUri;
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
