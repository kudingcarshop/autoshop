package com.kuding.customer.action;


import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.binder.SqlDateFormat;
import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.customer.view.CarEditView;
import com.kuding.customer.view.CarSaveView;
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
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	//添加java.sql.Date类型转换
	@InitBinder
	public void initBinder(WebDataBinder binder) {  
		binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(new SqlDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 手动添加车辆入口
	 * @param req
	 * @return
	 */
	@RequestMapping("cars/add")
	public ModelAndView addDetail(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		CarSaveView carView = new CarSaveView();
		carView.setName(user.getUserName());
		mv.getModel().put("carView",carView);
		mv.setViewName("customer/cars/add_car_detail");
		return mv;
	}
	
	/***
	 * 手动添加车辆
	 * @param req
	 * @param saveView
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("cars/add/save")
	public ModelAndView saveCar(HttpServletRequest req,@ModelAttribute(name="carView") @Validated CarSaveView saveView,BindingResult bindingResult) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/add_car_detail");
		StringBuffer errors = new StringBuffer();
		
		//常规数据校验
		String basicError = combineErrors(bindingResult);
		if(basicError != null) {
			errors.append(basicError);
		}
		
		//车牌号码相同
		if(vehService.isPlateNumberExist(saveView.getPlateNumber())) {
			if(errors.length() > 0) {
				errors.append("<br>");
			}
			errors.append(getMessage(req,"customer.cars.add.save.palteNumber_exist"));
		}
		//车架号
		if(vehService.isVINExist(saveView.getVin())) {
			if(errors.length() > 0) {
				errors.append("<br>");
			}
			errors.append(getMessage(req,"customer.cars.add.save.vin_exist"));
		}
		//发动机号
		if(vehService.isEngineNumberExist(saveView.getEngineNumber())) {
			if(errors.length() > 0) {
				errors.append("<br>");
			}
			errors.append(getMessage(req,"customer.cars.add.save.engineNumber_exist"));
		}
		if(errors.length() > 0) {
			mv.getModel().put("msg", errors.toString());
			return mv;
		}
		
		VehicleEntity veh = new VehicleEntity();
		veh.setPlateNumber(saveView.getPlateNumber());
		veh.setRegisterDate(saveView.getRegisterDate());
		veh.setVin(saveView.getVin());
		veh.setEngineNumber(saveView.getEngineNumber());
		veh.setLastMileage(saveView.getLastMileage());
		veh.setLastMaintainDate(saveView.getLastMaintainDate());
		veh.setLastBeautyDate(saveView.getLastBeautyDate());	
		veh.setTireServiceYear(getPassDate(saveView.getTireYears()));
		veh.setBatteryServiceYear(getPassDate(saveView.getBatteryYears()));
		mv.setViewName("redirect:/customer/cars/");
		vehService.saveVehicleInfo(veh, user.getUserId());
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
	public ModelAndView carEdit(HttpServletRequest req,@PathVariable Integer vehicleId,@ModelAttribute(name="veh") CarEditView carEdit) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null || vehicleId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		
		VehicleEntity veh = vehService.findById(VehicleEntity.class, vehicleId);
		if(veh != null) { 
			carEdit.setVehicleId(veh.getId());
			carEdit.setName(veh.getUser() != null ? veh.getUser().getName() : "");
			carEdit.setRegisterDate(veh.getRegisterDate());
			//计算电池使用年限
			if(veh.getBatteryServiceYear() != null) {
				carEdit.setBatteryYears((Calendar.getInstance().getTimeInMillis()-veh.getBatteryServiceYear().getTime())/1000/60/60/24/365.00);
			}
			//计算轮胎使用年限
			if(veh.getTireServiceYear() != null) {
				carEdit.setTireYears( (Calendar.getInstance().getTimeInMillis()-veh.getTireServiceYear().getTime())/1000/60/60/24/365.00);
			}
			carEdit.setLastMileage(veh.getLastMileage());
			carEdit.setLastMaintainDate(veh.getLastMaintainDate());
			carEdit.setLastBeautyDate(veh.getLastBeautyDate());
		}
		
		mv.setViewName("customer/cars/car_detail_edit");
		return mv;
	}
	
	@RequestMapping("cars/edit/save")
	public ModelAndView updateCar(HttpServletRequest req,@ModelAttribute(name="veh") @Validated CarEditView carEdit,BindingResult bindingResult) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null || carEdit.getVehicleId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/cars/car_detail_edit");
		//常规数据校验
		String errors = combineErrors(bindingResult);
		if(errors != null) {
			mv.getModel().put("msg", errors);
			return mv;
		}
		//业务逻辑校验
		VehicleEntity veh = vehService.findById(VehicleEntity.class, carEdit.getVehicleId());
		if(veh == null ) {
			mv.getModel().put("msg", messageSource.getMessage("customer.cars.edit.save.vehicle_notexist", null, null));
			return mv;
		}
		
		//客户不能够修改他人的车辆信息
		if(veh.getUser() != null && !veh.getUser().getId().equals(user.getUserId())) {
			mv.getModel().put("msg", messageSource.getMessage("customer.cars.edit.save.not_owner", null, null));
			return mv;
		}
		//新日期要大于旧日期:上次保养日期
		if(veh.getLastMaintainDate() != null 
				&& veh.getLastMaintainDate().after(carEdit.getLastMaintainDate())) {
			mv.getModel().put("msg", messageSource.getMessage("customer.cars.edit.save.lastMaintainDate_before", null, null));
			return mv;
		}
		//新日期要大于旧日期 :上次美容日期
		if(veh.getLastBeautyDate() != null 
				&& veh.getLastBeautyDate().after(carEdit.getLastBeautyDate())) {
			mv.getModel().put("msg", messageSource.getMessage("customer.cars.edit.save.lastBeautyDate_before", null, null));
			return mv;
		}

		VehicleEntity vehicle = new VehicleEntity();
		vehicle.setId(carEdit.getVehicleId());
		vehicle.setLastMaintainDate(carEdit.getLastMaintainDate());
		vehicle.setLastBeautyDate(carEdit.getLastBeautyDate());
		vehicle.setBatteryServiceYear(getPassDate(carEdit.getBatteryYears()));
		vehicle.setTireServiceYear(getPassDate(carEdit.getTireYears()));
		vehicle.setLastMileage(carEdit.getLastMileage());
		vehService.updateVehicleInfo(vehicle);
		
		mv.setViewName("redirect:/customer/cars/edit/"+carEdit.getVehicleId());
		
		return mv;
	}
	
	public static Date getPassDate(Double year) {
		if(year != null && year >= 0) {
			Calendar cur = Calendar.getInstance();
			Double milseconds = year*365*24*60*60*1000;
			return new Date((long)(cur.getTimeInMillis()-milseconds));
		}
		return null;
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
