package com.kuding.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.Constants;
import com.kuding.commons.EncryptUtils;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.action.BasicAction;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.system.model.FunctionEntity;
import com.kuding.system.model.RoleEntity;
import com.kuding.system.model.UserEntity;
import com.kuding.system.service.UserService;

@Controller
@RequestMapping("/pub")
public class AuthAction extends BasicAction{
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="login",method= RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, String loginName,String password, String reqUri) {
		ModelAndView mv = new ModelAndView();
		if(loginName == null || password == null) {
			throw new BusinessException("loginName or password should not be null");
		}
		
		UserEntity user = userService.login(loginName, EncryptUtils.digestWithMD5(password));
		if(user == null) {
			throw new BusinessException("loginName or password is not valid");
		}
		request.getSession().setAttribute(Constants.KEY_LOGIN_USER, prepareUserInfo(user));
		if(reqUri != null && reqUri.trim().length() > 0) {
			mv.setViewName("redirect:" + reqUri);
		}else {
			mv.setViewName("forward:/jsp/basic/index_mobile.jsp");
		}
		return mv;
	}
	
	private UserInfo prepareUserInfo(UserEntity user) {
		UserInfo userInfo = null;
		if(user != null) {
			userInfo = new UserInfo();
			userInfo.setUserId(user.getId());
			userInfo.setState(user.getState());
			userInfo.setUserName(user.getLoginName());
			userInfo.setType(user.getType());
			GarageInfoEntity garage = userService.queryGarageInfo(user.getId());
			if(garage != null) {
				userInfo.setGarageId(garage.getId());
			}
			if(user.getRoles() != null && user.getRoles().size() > 0) {
				for(RoleEntity role : user.getRoles()) {
					//获取确色
					if(role.getRoleCode() != null) {
						userInfo.getRoles().add(role.getRoleCode());
					}
					//获取功能uri
					if(role.getFunctions() != null && role.getFunctions().size() > 0) {
						for(FunctionEntity fun : role.getFunctions()) {
							if(fun.getUri() != null) {
								userInfo.getFunctions().add(fun.getUri());
							}
						}
					}
				}
			}
			
			
		}
		
		return userInfo;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(request.getSession().getAttribute(Constants.KEY_LOGIN_USER) != null) {
			request.getSession().removeAttribute(Constants.KEY_LOGIN_USER);
		}
		mv.setViewName("redirect:/");
		return mv;
	}
	
}
