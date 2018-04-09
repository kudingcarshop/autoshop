package com.kuding.customer.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.action.BasicAction;
import com.kuding.garage.service.CustomerService;
import com.kuding.system.model.UserEntity;

@Controller
@RequestMapping("/customer")
public class CustomerAction extends BasicAction {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("center")
	public ModelAndView center(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/center");
		return mv;
	}
	
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		UserEntity userEntity = service.findById(UserEntity.class, user.getUserId());
		ModelAndView mv = new ModelAndView();
		if(userEntity != null) {
			mv.getModelMap().put("user", userEntity);
		}
		mv.setViewName("customer/center_edit");
		return mv;
	}

}
