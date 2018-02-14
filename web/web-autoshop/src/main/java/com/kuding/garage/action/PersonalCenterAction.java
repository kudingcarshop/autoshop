package com.kuding.garage.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/garage")
public class PersonalCenterAction extends BasicAction {
	
	@RequestMapping("center")
	public ModelAndView showPersonalCenter() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/center/personal_center");
		return mv;
	}

}
