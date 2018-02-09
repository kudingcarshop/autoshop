package com.kuding.garage.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.garage.service.VehicleService;
import com.kuding.garage.view.VehicleView;
import com.kuding.system.model.UserEntity;
import com.kuding.system.service.UserService;

@Controller
@RequestMapping("/garage")
public class VehicleAction extends BasicAction{
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("vehicle/showCars")
	public ModelAndView showGarageCars(HttpSession session) {
		
		Integer garageId = getUserInfo(session)!= null? getUserInfo(session).getGarageId() : null;
		ModelAndView mv = new ModelAndView();
		
		Integer working_count = vehicleService.queryWorkingCount(garageId);
		getLogger().info("working_count="+working_count);
		mv.getModel().put("working_count", working_count);
		
		Integer unPayCount = vehicleService.queryUnPayCount(garageId);
		getLogger().info("unPayCount="+unPayCount);
		mv.getModel().put("un_pay_count", unPayCount);
		
		List<VehicleEntity> vehicles = vehicleService.queryGarageAllCars(garageId);
		mv.getModel().put("vehicles", vehicles);
		
		mv.setViewName("garage/vehicle/showCars");
		return mv;
	}
	
	@RequestMapping("vehicle/addCarOptions")
	public ModelAndView addCarOptions() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/vehicle/add_car_0");
		return mv;
	}
	
	@RequestMapping("vehicle/manualAdd")
	public ModelAndView manualAdd(HttpSession session) {
		Integer garageId = getUserInfo(session)!= null? getUserInfo(session).getGarageId() : null;
		if(garageId == null ) {
			throw new BusinessException("Garage id is null",ErrorCode.BIZ_GAR_ID_NULL);
		}
		ModelAndView mv = new ModelAndView();
		
		List<Map<String,String>> users = userService.queryGarageUsers(garageId);
		mv.getModel().put("users", users);
		
		mv.setViewName("garage/vehicle/add_car_manual_01");
		
		return mv;
	}
	
	@RequestMapping("vehicle/goNext")
	public ModelAndView addCarNext(VehicleView view) {
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("vehicleView", view);
		mv.setViewName("garage/vehicle/add_car_manual_02");
		return mv;
	}
	
	@RequestMapping("vehicle/goBack")
	public ModelAndView goBack(VehicleView view) {
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("vehicleView", view);
		mv.setViewName("garage/vehicle/add_car_manual_01");
		return mv;
	}
	
	@RequestMapping("vehicle/saveCar")
	public ModelAndView saveCar(VehicleView view) {
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isAllEmpty(view.getPlateNumber()) 
				|| StringUtils.isAllEmpty(view.getVin())
				|| StringUtils.isAllEmpty(view.getEngineNumber())
				|| view.getUserId() == null) {
			throw new BusinessException("The input data is not valid.");
		}
		//车辆不能重复录入
		if(vehicleService.isPlateNumberExist(view.getPlateNumber())
				|| vehicleService.isVehicleExist(view.getVin(), view.getEngineNumber())) {
			throw new BusinessException("The vehicle is already exist",ErrorCode.BIZ_VEH_EXIST);
		}
		vehicleService.saveOrUpdateVehicle(prepareVehicleEntity(view));
		mv.setViewName("redirect:/garage/vehicle/showCars");
		return mv;
	}
	
	/**
	 * 拷贝数据
	 * @param view
	 * @return
	 */
	private VehicleEntity prepareVehicleEntity(VehicleView view) {
		
		VehicleEntity vehicle = null;
		if(view != null) {
			vehicle = new VehicleEntity();
			try {
				BeanUtils.copyProperties(vehicle, view);
			} catch (IllegalAccessException | InvocationTargetException e) {
				getLogger().info("prepare data fail :" + e.getMessage());
			}
			UserEntity user = new UserEntity();
			getLogger().info("userId=" + view.getUserId());
			user.setId(view.getUserId());
			vehicle.setUser(user);
		}
		return vehicle;
		
	}
	
	@RequestMapping("vehicle/showVehicleDetail")
	public ModelAndView showVehicleDetail(Integer vehicleId) {
		if(vehicleId == null || vehicleId <= 0) {
			throw new BusinessException(ErrorCode.BIZ_VEH_INVALID_ID);
		}
		VehicleEntity veh = vehicleService.queryVehicleDetail(vehicleId);
		ModelAndView mv = new ModelAndView();
		if(veh == null) {
			throw new BusinessException(ErrorCode.BIZ_VEH_NOT_EXIST);
		}
		mv.getModel().put("veh", veh);
		
		Integer vioNum = vehicleService.queryTrafficViolationCount(vehicleId);
		if(vioNum != null ) {
			mv.getModel().put("vioNum", vioNum);
		}else {
			mv.getModel().put("vioNum", 0);
		}
		mv.setViewName("garage/vehicle/vehicle_detail");
		return mv;
	}
	
	@RequestMapping("vehicle/serviceCenter")
	public ModelAndView showServiceCenter() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/service_center");
		return mv;
	}

}
