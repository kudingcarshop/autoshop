package com.kuding.customer.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.action.BasicAction;

@Controller
@RequestMapping("/customer")
public class CustomerCarAction extends BasicAction {
	
	@RequestMapping("cars/add")
	public ModelAndView add(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/add_car");
		return mv;
	}
	
	@RequestMapping("cars/addDetail")
	public ModelAndView addDetail(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/add_car_detail");
		return mv;
	}
	
	@RequestMapping("cars/detail")
	public ModelAndView detail(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/car_detail");
		return mv;
	}

}
