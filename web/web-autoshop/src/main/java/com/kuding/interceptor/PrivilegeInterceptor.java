package com.kuding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.Constants;
import com.kuding.commons.login.UserInfo;

public class PrivilegeInterceptor implements HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(PrivilegeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if(uri!= null) {
			int index = uri.indexOf("?");
			if(index > 0) {
				uri = uri.substring(0,index);
			}
		}
		logger.info("the request uri is " + uri);
		UserInfo user = (UserInfo) request.getSession().getAttribute(Constants.KEY_LOGIN_USER);
		boolean isAllow = !Constants.IS_NEED_LOGIN  || isSystemAdmin(user) || isUriAllow(user, uri);
		if(!isAllow) {
			response.sendError(404);
		}
		return isAllow;
	}
	
	/**
	 * 平台系统管理员具有所有权限
	 * @param user
	 * @return
	 */
	private boolean isSystemAdmin(UserInfo user) {
		return  user != null && user.getRoles() != null && user.getRoles().contains(Constants.ROLE_TYPE_SYS_ADMIN);
	}
	
	/**
	 * 检查uri是否被授权访问
	 * @param user
	 * @param uri
	 * @return
	 */
	private boolean isUriAllow(UserInfo user,String uri) {
		boolean isUriAllow = false;
		if(user != null && uri != null
				&& user.getFunctions() != null
				&& user.getFunctions().contains(uri)) {
			isUriAllow = true;
		}
		
		return isUriAllow;
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
