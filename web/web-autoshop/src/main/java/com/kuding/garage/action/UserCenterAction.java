package com.kuding.garage.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.service.UserCenterService;

@Controller
@RequestMapping("/garage")
public class UserCenterAction extends BasicAction {
	
	
	@Autowired
	private UserCenterService service;
	@RequestMapping("user/userCenter")
	public ModelAndView showPersonalCenter(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getGarageId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		Integer vehicleSum = service.queryVehicleSum(user.getGarageId(), user.getUserId());
		mv.getModel().put("vehicleSum", vehicleSum);
		mv.setViewName("sys/user/user_center");
		return mv;
	}
}
