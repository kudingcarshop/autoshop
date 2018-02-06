package com.kuding.garage.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/garage")
public class SosAction extends BasicAction{

	@RequestMapping("vehicle/showSos")
	public ModelAndView showSos(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/sos");
		return mv;
	}
	
	@RequestMapping("vehicle/showAddressPikcker")
	public ModelAndView showAddressPicker(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/address_picker");
		return mv;
	}
}
