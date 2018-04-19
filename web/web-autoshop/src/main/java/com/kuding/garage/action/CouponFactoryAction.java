package com.kuding.garage.action;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.http.Result;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.model.Constant;
import com.kuding.garage.model.CouponFactoryEntity;
import com.kuding.garage.service.CouponFactoryService;

@Controller
@RequestMapping("/garage")
public class CouponFactoryAction extends BasicAction {

	@Autowired
	private CouponFactoryService mService;

	@RequestMapping("showCouponFactory")
	public ModelAndView showCouponFactory(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("garage/coupon/coupon_creator");
		return mv;
	}

	@RequestMapping("couponFactory/creator")
	@ResponseBody
	public Result createCoupon(HttpSession session, CouponFactoryEntity couponFactoryEntity) {
		UserInfo userInfo = getUserInfo(session);
		Result result = new Result();
		couponFactoryEntity.setCouponCreateDatetime(new Date(System.currentTimeMillis()));
		couponFactoryEntity.setCouponFactoryStatus(Constant.CouponFactoryConstant.COUPON_STATUS_ACTIVE);
		couponFactoryEntity.setCouponUniqueCode(""+System.currentTimeMillis());
		mService.createCoupon(couponFactoryEntity, userInfo.getUserId());

		result.setFlag(Result.FLAG_SUCCESS);
		result.setMessage("Coupon added");
		return result;
	}
}
