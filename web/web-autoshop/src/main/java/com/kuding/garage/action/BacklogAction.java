package com.kuding.garage.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.CalendarUtils;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.service.BacklogService;

@Controller
@RequestMapping("/garage")
public class BacklogAction extends BasicAction {
	
	@Autowired
	private BacklogService backlogService;
	
	@RequestMapping("backlog")
	public ModelAndView showBacklog(HttpServletRequest req) {
		Integer garageId = getGarageId(req);
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("unpVehs", backlogService.queryUnpayVehicles(garageId));
		mv.getModel().put("unpSum", backlogService.queryUnpaySum(garageId));
		mv.getModel().put("booking", backlogService.queryBooking(garageId));
		mv.getModel().put("serving", backlogService.queryServing(garageId));
		mv.setViewName("garage/backlog/backlog");
		return mv;
	}

	@RequestMapping("backlog/unpay")
	public ModelAndView showUnpay(HttpServletRequest req) {
		Integer garageId = getGarageId(req);
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> list = backlogService.queryUnpayDetails(garageId);
		mv.getModel().put("unpays", prepareUnpayData(list));
		mv.setViewName("garage/backlog/backlog_unpay");
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
	
	/**
	 * 拼装数据用于前台显示
	 * @param list 按时间降序排列数据
	 * @return
	 */
	private Map<String,List<?>> prepareUnpayData(List<Map<String,Object>> list){
		Map<String,List<?>> result = null;
		if(list != null && list.size() > 0) {
			result = new HashMap<>();
			//分类 0-未开始分类 1-今天 2-月内 3-一个月之前
			int state = 0;
			Calendar today = CalendarUtils.getToday();
			Calendar month = CalendarUtils.getThisMonth();
			Timestamp time = null;
			List<Map<String,Object>> todayList = null;
			List<Map<String,Object>> monthList = null;
			List<Map<String,Object>> overMonthList = null;
			for(Map<String,Object> map : list) {
				time = (Timestamp) map.get("receiveTime");
				getLogger().info("time = " + time);
				if(isToday(today, time) && state != 1) {
					state = 1;
					todayList = new ArrayList<>();
					result.put("todayList", todayList);
				}
				
				if(isThisMonth(today, month, time) && state != 2) {
					state = 2;
					monthList = new ArrayList<>();
					result.put("monthList", monthList);
				}
				
				if(isBeforThisMonth(month, time) && state != 3) {
					state = 3;
					overMonthList =  new ArrayList<>();
					result.put("overMonthList", overMonthList);
				}
				
				switch (state) {
				case 1:
					todayList.add(map);
					break;
				case 2:
					monthList.add(map);
					break;
				case 3:
					overMonthList.add(map);
					break;

				default:
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * @param today
	 * @param time
	 * @return
	 */
	private boolean isToday(Calendar today,Timestamp time) {
		boolean isToday = false;
		if(today!= null && time != null) {
			isToday = time.compareTo(today.getTime()) >= 0;
		}
		return isToday;
	}
	
	/**
	 * 判断是否当前月份之内日期
	 * @param today
	 * @param month 当月首日0时日期
	 * @param time
	 * @return
	 */
	private boolean isThisMonth(Calendar today, Calendar month,Timestamp time) {
		boolean isMonth = false;
		if(today!= null &&month!= null && time != null) {
			isMonth = (time.compareTo(month.getTime()) >= 0) 
							&& (time.compareTo(today.getTime())< 0);
		}
		return isMonth;
	}
	
	/**
	 * 判断是否当前月份之前日期
	 * @param month 当月首日0时日期
	 * @param time
	 * @return
	 */
	private boolean isBeforThisMonth(Calendar month,Timestamp time) {
		boolean isBeforeMonth = false;
		if(month!= null && time != null) {
			isBeforeMonth = time.before(month.getTime());
		}
		return isBeforeMonth;
	}
	
	@RequestMapping("backlog/booking")
	public ModelAndView showBooking(HttpServletRequest req) {
		Integer garageId = getGarageId(req);
		if(garageId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("booking", categoryDataforBooking(backlogService.queryBookingList(garageId)));
		mv.setViewName("garage/backlog/backlog_booking");
		return mv;
	}
	
	/**
	 * 预约列表展示重新处理
	 * @param list
	 * @return
	 */
	private List<Map<String,Object>> categoryDataforBooking(List<Map<String,Object>> list){
		if(list != null && list.size() > 0) {
			List<Map<String,Object>> res = new ArrayList<>();
			Timestamp preTime = null;
			int seq = 0;
			for(Map<String,Object> map : list) {
				Timestamp time = (Timestamp) map.get("bookTime");
				if(time == null) {
					getLogger().info("error: booking list got an invalid record that bookTime is null: id = " + map.get("id"));
					continue;
				}
				if(preTime == null || (preTime != null && !CalendarUtils.isSameDate(preTime, time))) {
					Map<String,Object> titleMap = new HashMap<>();
					titleMap.put("titleTime", time);
					seq = 0;
					res.add(titleMap);
				}
				preTime = time;
				seq++;
				map.put("seq", seq);
				res.add(map);
			}
			return res;
		}
		return null;
	}

}
