package com.kuding.customer.action;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.action.BasicAction;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.garage.service.CustomerService;
import com.kuding.garage.service.VehicleService;
import com.kuding.util.Utils;

@Controller
@RequestMapping("/customer")
public class CustomerCarAction extends BasicAction {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private VehicleService vehService;
	
	@RequestMapping("cars/add")
	public ModelAndView add(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/add_car");
		return mv;
	}
	
	@RequestMapping("cars/addDetail")
	public ModelAndView addDetail(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/add_car_detail");
		return mv;
	}
	
	/**
	 * 车辆详情
	 * @param req
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping("cars/detail")
	public ModelAndView detail(HttpServletRequest req,Integer vehicleId) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null || vehicleId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		//违章
		long vioCount = custService.queryTrafficViolationCount(user.getUserId());
		mv.getModel().put("vioCount", vioCount);
		
		//进度
		long servCount = custService.queryUserServingCount(user.getUserId());
		mv.getModel().put("servCount", servCount);
		
		VehicleEntity veh = vehService.findById(VehicleEntity.class, vehicleId);
		if(veh != null) { 
			if(veh.getRegisterDate() != null) {
				//保险到期日
				Date insuranceDate = Utils.assumeInsuranceDate(veh.getRegisterDate());
				if(insuranceDate != null) {
					mv.getModel().put("insuranceDate", insuranceDate);
				}
				//保险到期提醒标志
				mv.getModel().put("insuranceInform", Utils.isNeedInsurance(veh.getRegisterDate()));
				
				//年审
				Date vertificationDate = Utils.assumeVertificationDate(veh.getRegisterDate());
				if(vertificationDate != null) {
					mv.getModel().put("vertificationDate", vertificationDate);
				}
				//年审提醒标志
				mv.getModel().put("vertificationInform", Utils.isNeedAnnualVertification(veh.getRegisterDate()));
			}
			
			//保养
			if(veh.getLastMaintainDate() != null) {
				//保养到期日
				Date maintainDate = Utils.assumeMaintaineDate(veh.getLastMaintainDate());
				if(maintainDate != null) {
					mv.getModel().put("maintainDate", maintainDate);
				}
				//年审提醒标志
				mv.getModel().put("maintainInform", Utils.isNeedMaintaine(veh.getLastMaintainDate()));
			}
			
		}
		
		mv.setViewName("customer/cars/car_detail");
		return mv;
	}

}
