package com.kuding.garage.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.http.Result;
import com.kuding.commons.login.UserInfo;
import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;
import com.kuding.garage.service.CustomerService;

@Controller
@RequestMapping("/garage")
public class PersonalCenterAction extends BasicAction {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("center")
	public ModelAndView showPersonalCenter(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getGarageId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		Integer userSum = service.queryUserSum(user.getGarageId());
		mv.getModel().put("userSum", userSum);
		mv.setViewName("garage/center/personal_center");
		return mv;
	}
	
	@RequestMapping("center/customers")
	public ModelAndView customers(HttpServletRequest req, String search, Integer page) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getGarageId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		PaginationQuery pageQuery = new PaginationQuery();
		if(page != null) {
			pageQuery.page = page.intValue();
		}
		pageQuery.mapParams.put("search", search);
		pageQuery.mapParams.put("garageId", user.getGarageId());
		//查询车主,2-个人车主 3-企业车主
		Integer[] userTypes = new Integer[2];
		userTypes[0] = 2;
		userTypes[1] = 3;
		pageQuery.mapParams.put("type", userTypes);
		PaginationResult<Map<String,Object>> result = service.queryUsersBySqlPagination(pageQuery);
		mv.getModel().put("result", result);
		mv.setViewName("garage/center/customers");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("center/customers/more")
	@ResponseBody
	public Result moreCustomers(HttpServletRequest req, String search, Integer page) {
		Result res = new Result();
		ModelAndView mv = customers(req, search, page);
		if(mv != null && mv.getModel() != null 
				&& mv.getModel().get("result") != null) {
			PaginationResult<Map<String,Object>> pq = (PaginationResult<Map<String, Object>>) mv.getModel().get("result");
			res.getExtraData().put("total", pq.getTotal());
			res.setRows(pq.getRows());
			res.setFlag(Result.FLAG_SUCCESS);
		}
		return res;
	}

}
