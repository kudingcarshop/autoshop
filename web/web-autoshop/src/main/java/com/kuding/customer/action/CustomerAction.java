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
		
		UserEntity userInfo = service.findById(UserEntity.class, user.getUserId());
		if(userInfo != null) {
			mv.getModel().put("user", userInfo);
		}
		
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
		mv.setViewName("customer/customer_edit");
		return mv;
	}
	
	@RequestMapping("cards")
	public ModelAndView cards(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_cards");
		return mv;
	}
	
	@RequestMapping("consumptions")
	public ModelAndView consumptions(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_consumption_records");
		return mv;
	}
	
	@RequestMapping("cars")
	public ModelAndView cars(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_cars");
		return mv;
	}
	
	@RequestMapping("backlog")
	public ModelAndView backlog(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/backlog/backlog");
		return mv;
	}
	

	@RequestMapping("serving/state")
	public ModelAndView servingState(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/serving_state");
		return mv;
	}
}
