package com.kuding.garage.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.service.BacklogService;

@Controller
@RequestMapping("/garage")
public class BacklogAction extends BasicAction {
	
	@Autowired
	private BacklogService backlogService;
	
	@RequestMapping("backlog")
	public ModelAndView showBacklog(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getGarageId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		Integer garageId = user.getGarageId();
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("unpVehs", backlogService.queryUnpayVehicles(garageId));
		mv.getModel().put("unpSum", backlogService.queryUnpaySum(garageId));
		mv.getModel().put("booking", backlogService.queryBooking(garageId));
		mv.getModel().put("serving", backlogService.queryServing(garageId));
		mv.setViewName("garage/backlog/backlog");
		return mv;
	}

	@RequestMapping("backlog/unpay")
	public ModelAndView showUnpay() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/backlog/backlog_unpay");
		return mv;
	}
	
	@RequestMapping("backlog/booking")
	public ModelAndView showBooking() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/backlog/backlog_booking");
		return mv;
	}
	
	@RequestMapping("backlog/serving")
	public ModelAndView showServing() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/backlog/backlog_inservice");
		return mv;
	}

}
