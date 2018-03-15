package com.kuding.garage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.garage.model.GarageStaffEntity;
import com.kuding.garage.service.GarageService;

@Controller
@RequestMapping("/garage")
public class GarageAction extends BasicAction {
	
	@Autowired
	private GarageService service;
	
	/**
	 * 查看管理车行信息(可编辑)
	 * @param req
	 * @return
	 */
	@RequestMapping("myGarage")
	public ModelAndView showGarageDetail(HttpServletRequest req) {
		UserInfo  user = getUserInfo(req.getSession());
		if(user == null || user.getGarageId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = detail(user.getGarageId());
		mv.getModel().put("editable", true);
		return mv;
	}

	/**
	 * 查询指定车行信息
	 * @param garageId
	 * @return
	 */
	@RequestMapping("detail/{garageId}")
	public ModelAndView detail(@PathVariable("garageId") Integer garageId) {
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		GarageInfoEntity garage = service.findById(GarageInfoEntity.class,garageId);
		if(garage != null) {
			mv.getModel().put("garage", garage);
		}
		List<GarageStaffEntity> staffs = service.queryGarageStaff(garageId);
		if(staffs != null && staffs.size() > 0) {
			mv.getModel().put("staffs", staffs);
		}
		mv.getModel().put("editable", false);
		mv.setViewName("garage/garage_detail");
		return mv;
	}
}
