package com.kuding.customer.action;


import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping("cars/detail/{vehicleId}")
	public ModelAndView detail(HttpServletRequest req,@PathVariable Integer vehicleId) {
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
				//车龄
				Long years = (Calendar.getInstance().getTimeInMillis()-veh.getRegisterDate().getTime())/1000/60/60/24/365;
				mv.getModel().put("years", years);
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
				//保养提醒标志
				mv.getModel().put("maintainInform", Utils.isNeedMaintaine(veh.getLastMaintainDate()));
			}
			
			mv.getModel().put("veh", veh);
		}
		
		mv.setViewName("customer/cars/car_detail");
		return mv;
	}
	
	/**
	 * 车辆信息编辑入口
	 * @param req
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping("cars/edit/{vehicleId}")
	public ModelAndView carEdit(HttpServletRequest req,@PathVariable Integer vehicleId) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null || vehicleId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		
		VehicleEntity veh = vehService.findById(VehicleEntity.class, vehicleId);
		if(veh != null) { 
			mv.getModel().put("veh", veh);
			
			//计算轮胎使用年限
			if(veh.getTireServiceYear() != null) {
				mv.getModel().put("tireYears", (Calendar.getInstance().getTimeInMillis()-veh.getTireServiceYear().getTime())/1000/60/60/24/365.00);
			}
			
			//计算电池使用年限
			if(veh.getBatteryServiceYear() != null) {
				mv.getModel().put("batteryYears", (Calendar.getInstance().getTimeInMillis()-veh.getBatteryServiceYear().getTime())/1000/60/60/24/365.00);
			}
		}
		
		mv.setViewName("customer/cars/car_detail_edit");
		return mv;
	}
	
	/**
	 * 保养详情
	 * @param req
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping("cars/maintainDetail/{vehicleId}")
	public ModelAndView maintainDetail(HttpServletRequest req,@PathVariable Integer vehicleId) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null || vehicleId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		VehicleEntity veh = vehService.findById(VehicleEntity.class, vehicleId);
		if(veh != null) {
			mv.getModel().put("veh", veh);
			if(veh.getLastMaintainDate() != null) {
				//上次保养距今时长(月)
				Long maintainMonths = (Calendar.getInstance().getTimeInMillis() - veh.getLastMaintainDate().getTime())/1000/60/60/24/30;
				mv.getModel().put("maintainMonths", maintainMonths);
				
				//是否需要提醒保养
				mv.getModel().put("maintainInform", Utils.isNeedMaintaine(veh.getLastMaintainDate()));
			}
			
			//计算轮胎使用年限
			if(veh.getTireServiceYear() != null) {
				Double tireYears = (Calendar.getInstance().getTimeInMillis() - veh.getTireServiceYear().getTime())/1000/60/60/24/365.00;//保留小数点
				mv.getModel().put("tireYears", tireYears);
			}
			
			//计算电池使用年限
			if(veh.getBatteryServiceYear() != null) {
				Double batteryYears = (Calendar.getInstance().getTimeInMillis() - veh.getBatteryServiceYear().getTime())/1000/60/60/24/365.00;//保留小数点
				mv.getModel().put("batteryYears", batteryYears);
			}
			
		}
		
		mv.setViewName("customer/cars/maintain_detail");
		return mv;
	}

}
