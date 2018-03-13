package com.kuding.garage.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;

@Controller
@RequestMapping("/garage")
public class GarageAction extends BasicAction {
	
	@RequestMapping("myGarage")
	public ModelAndView showGarageDetail(HttpServletRequest req) {
		UserInfo  user = getUserInfo(req.getSession());
		if(user == null || user.getGarageId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/garage_detail");
		return mv;
	}

}
