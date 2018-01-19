package com.kuding.garage.action;

import java.sql.Timestamp;

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

@Controller
@RequestMapping("/garage")
public class VehicleBookAction {
	
	@Autowired
	private VehicleBookService vehicleBookService;
	
	@RequestMapping("show")
	public ModelAndView show(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/car_book");
		return mv;
	}
	
    @RequestMapping("book/add")
    @ResponseBody
    public Result addBook(HttpSession session, VehicleBookInfoEntity bookInfoEntify) {
    	Result result = new Result();
//    	Logger.getLogger("BookAction").info(bookInfoEntify.getId());
    	bookInfoEntify.setUserEntity(vehicleBookService.getUserEntity(1));
    	bookInfoEntify.setGarageInfoEntify(vehicleBookService.getGarageInfoEntity(1));
    	bookInfoEntify.setCreateTime(new Timestamp(System.currentTimeMillis()));
    	vehicleBookService.save(bookInfoEntify);
    	return result;
    }
    
    

}
