package com.kuding.garage.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.customer.model.VehicleMaintainInfo;
import com.kuding.garage.service.BacklogService;
@Controller
@RequestMapping("garage")
public class BacklogInServeAction extends BasicAction {
	
	@Autowired
	private BacklogService backlogService;
	
	@RequestMapping("backlog/serving")
	public ModelAndView showServing(HttpServletRequest req,String msg,String msgType) {
		Integer garageId = getGarageId(req);
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("act", 0);
		mv.getModel().put("msg", msg);
		mv.getModel().put("msgType", msgType);
		queryCount(mv, garageId);
		
		//全部未完成
		List<Map<String, Object>> list = backlogService.queryVehicleListWithState(garageId, 
				new String[] {VehicleMaintainInfo.STATE_QUEUE,VehicleMaintainInfo.STATE_SERVING});
		mv.getModel().put("list", list);
		
		mv.setViewName("garage/backlog/backlog_inservice");
		return mv;
	}
	
	
	private Integer getGarageId(HttpServletRequest req) {
		if(req != null) {
			UserInfo user = getUserInfo(req.getSession());
			if(user != null && user.getGarageId() != null) {
				return user.getGarageId();
			}
		}
		return null;
	}
	
	private void queryCount(ModelAndView mv,Integer garageId){
		//全部
		Integer all = backlogService.queryVehicleSumWithState(garageId, 
				new String[] {VehicleMaintainInfo.STATE_QUEUE,VehicleMaintainInfo.STATE_SERVING});
		mv.getModel().put("all", all);
		
		//排队中
		Integer queueNum = backlogService.queryVehicleSumWithState(garageId, new String[] {VehicleMaintainInfo.STATE_QUEUE});
		mv.getModel().put("queueNum", queueNum);
		
		//服务中
		Integer servingNum = backlogService.queryVehicleSumWithState(garageId, new String[] {VehicleMaintainInfo.STATE_SERVING});
		mv.getModel().put("servingNum", servingNum);
	}
	
	@RequestMapping("backlog/inqueue")
	public ModelAndView showInqueue(HttpServletRequest req,String msg,String msgType) {
		Integer garageId = getGarageId(req);
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("act", 1);
		mv.getModel().put("msg", msg);
		mv.getModel().put("msgType", msgType);
		queryCount(mv,garageId);
		
		//排队中列表
		List<Map<String, Object>> list = backlogService.queryVehicleListWithState(garageId, 
				new String[] {VehicleMaintainInfo.STATE_QUEUE});
		mv.getModel().put("list", list);
		
		mv.setViewName("garage/backlog/backlog_inservice");
		return mv;
	}
	
	@RequestMapping("backlog/inserving")
	public ModelAndView showInserving(HttpServletRequest req,String msg,String msgType) {
		Integer garageId = getGarageId(req);
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("act", 2);
		mv.getModel().put("msg", msg);
		mv.getModel().put("msgType", msgType);
		queryCount(mv,garageId);
		
		//服务中中列表
		List<Map<String, Object>> list = backlogService.queryVehicleListWithState(garageId, 
				new String[] {VehicleMaintainInfo.STATE_SERVING});
		mv.getModel().put("list", list);
		
		mv.setViewName("garage/backlog/backlog_inservice");
		return mv;
	}
	
	@RequestMapping("backlog/changeState")
	public ModelAndView changeState(HttpServletRequest req,Integer maintainId, String state, Integer act) {
		if(maintainId == null || state == null) {
			throw new BusinessException("maintainId or state should not be null", ErrorCode.SYS_ERROR);
		}
		backlogService.updateMaintainState(maintainId, state);
		ModelAndView mv = null;
		String msg = "success";
		String msgType = "0";
		switch (act) {
		case 0:
			mv = showServing(req, msg, msgType);
			break;
		case 1:
			mv = showInqueue(req, msg, msgType);
			break;
		case 2:
			mv = showInserving(req, msg, msgType);
			break;

		default:
			break;
		}
		return mv;
	}

}
