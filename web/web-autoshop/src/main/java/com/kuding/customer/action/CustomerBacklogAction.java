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

@Controller
@RequestMapping("/customer")
public class CustomerBacklogAction extends BasicAction {
	
	@Autowired
	private CustomerService custService;
	
	/**
	 * 待付款列表
	 * @param req
	 * @return
	 */
	@RequestMapping("backlog/bills")
	public ModelAndView servingState(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/backlog/bills");
		mv.getModel().put("unpay", custService.queryUnPayList(user.getUserId()));
		return mv;
	}
	
	@RequestMapping("backlog/violation")
	public ModelAndView violation(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/backlog/tracffic_violation");
		return mv;
	}
	

	@RequestMapping("backlog/vertification")
	public ModelAndView vertification(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/backlog/annual_vertification_insurance");
		return mv;
	}

}
