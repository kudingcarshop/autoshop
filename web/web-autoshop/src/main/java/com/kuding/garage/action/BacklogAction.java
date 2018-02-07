package com.kuding.garage.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/garage")
public class BacklogAction extends BasicAction {
	
	@RequestMapping("backlog")
	public ModelAndView showBacklog() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/backlog/backlog");
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

	@RequestMapping("backlog/unpay")
	public ModelAndView showUnpay() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/backlog/backlog_unpay");
		return mv;
	}


}
