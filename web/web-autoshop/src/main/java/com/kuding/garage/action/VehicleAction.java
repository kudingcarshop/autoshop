package com.kuding.garage.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/garage")
public class VehicleAction {
	
	@RequestMapping("showGarageCars")
	public ModelAndView showGarageCars() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/vehicle/showGarageCars");
		return mv;
	}

}
