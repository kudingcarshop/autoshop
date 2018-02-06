package com.kuding.garage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.http.Result;
import com.kuding.garage.model.VehicleBookInfoEntity;
import com.kuding.garage.service.VehicleBookService;
import com.kuding.system.model.SystemDicEntity;

@Controller
@RequestMapping("/garage")
public class VehicleCheckInAction {
	
	@Autowired
	private VehicleBookService vehicleBookService;
	
	
	@RequestMapping("checkin")
	public ModelAndView garageCheckin(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/car_checkin");
		List<SystemDicEntity> bizList = vehicleBookService.getBizList();
		mv.addObject("biz_category", bizList);
//		for (VehicleBookInfoEntity entity : bookList) {
//			Logger.getLogger("BookAction").info(entity.getPlateNumber() + "!!!!!!!!!!!!!!!!!");
//		}
		return mv;
	}
	
    @RequestMapping("checkin/suggest_plate_number")
    @ResponseBody
    public List<VehicleBookInfoEntity> suggestPlateNumber(HttpSession session) {
    	List<VehicleBookInfoEntity> bookList = vehicleBookService.getSuggestBookList(1/*garageId*/);
    	List<VehicleBookInfoEntity> result = new ArrayList<>();
    	for (VehicleBookInfoEntity vEntity : bookList) {
    		VehicleBookInfoEntity entity = new VehicleBookInfoEntity();
    		entity.setId(vEntity.getId());
    		entity.setPlateNumber(vEntity.getPlateNumber());
    		result.add(entity);
    	}
    	return result;
    }

}
