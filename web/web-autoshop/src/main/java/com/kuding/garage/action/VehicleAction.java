package com.kuding.garage.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.garage.model.VehicleEntity;
import com.kuding.garage.service.VehicleService;

@Controller
@RequestMapping("/garage")
public class VehicleAction extends BasicAction{
	
	@Autowired
	private VehicleService vehicleService;
	
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

}
