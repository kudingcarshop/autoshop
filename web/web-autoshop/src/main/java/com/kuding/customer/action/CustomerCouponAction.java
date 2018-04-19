package com.kuding.customer.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.kuding.commons.http.Result;
import com.kuding.commons.login.UserInfo;
import com.kuding.customer.model.CustomerCouponEntity;
import com.kuding.customer.service.CustomerCouponService;
import com.kuding.garage.action.BasicAction;
import com.kuding.garage.model.Constant;
import com.kuding.garage.model.CouponFactoryEntity;
import com.kuding.garage.service.CouponFactoryService;

@Controller
@RequestMapping("/coupon")
public class CustomerCouponAction extends BasicAction {
	@Autowired
	private CustomerCouponService mCouponService;

	@Autowired
	private CouponFactoryService mCouponFactoryService;

	@RequestMapping("get_coupon_center")
	public ModelAndView getCouponCenterList(HttpSession session) {
		UserInfo userInfo = getUserInfo(session);
		ModelAndView mv = new ModelAndView();
		List<CouponFactoryEntity> couponFactoryList = mCouponFactoryService.getAllCouponFactory();
		List<CouponFactoryEntity> couponResult = new ArrayList<>();
		for (CouponFactoryEntity couponFactoryEntity : couponFactoryList) {
			if (couponFactoryEntity.getCouponFactoryStatus() != Constant.CouponFactoryConstant.COUPON_STATUS_ACTIVE) {
				continue;
			}
			CouponFactoryEntity add = new CouponFactoryEntity();
			add.setId(couponFactoryEntity.getId());
			add.setCouponName(couponFactoryEntity.getCouponName());
			float discount = couponFactoryEntity.getCouponDiscount();
			discount = discount * 100;
			if (discount % 10 == 0) {
				discount = discount / 10;
			}
			add.setCouponDiscount(discount);
			add.setCouponPrice(couponFactoryEntity.getCouponPrice());
			add.setCouponCondition(couponFactoryEntity.getCouponCondition());
			add.setCouponConditionCount(couponFactoryEntity.getCouponConditionCount());
			add.setCouponType(couponFactoryEntity.getCouponType());
			add.setCouponStartDate(couponFactoryEntity.getCouponStartDate());
			Date endDate = couponFactoryEntity.getCouponEndDate();
			Date current = new Date(System.currentTimeMillis());
			if (current.after(endDate)) {
				continue;
			}
			add.setCouponEndDate(couponFactoryEntity.getCouponEndDate());
			add.setCouponAmount(couponFactoryEntity.getCouponAmount());
			boolean hasCoupon = mCouponService.isCustomerHasCoupon(userInfo.getUserId(), couponFactoryEntity);
			if (hasCoupon) {
				add.setUserGetStatus(Constant.CouponFactoryConstant.COUPON_GET_STATUS_GET);
			} else {
				add.setUserGetStatus(Constant.CouponFactoryConstant.COUPON_GET_STATUS_NONE);
			}
			couponResult.add(add);
		}

		mv.addObject("couponCenterList", JSON.toJSONString(couponResult));
		mv.setViewName("garage/coupon/get_coupon_center");
		return mv;
	}

	@RequestMapping("customer_coupn")
	public ModelAndView getCustomerCouponList(HttpSession session) {
		UserInfo userInfo = getUserInfo(session);
		ModelAndView mv = new ModelAndView();
		List<CustomerCouponEntity> allCouponList = mCouponService.getAllCustomerCoupon(userInfo.getUserId());
		List<CustomerCouponEntity> unusedList = new ArrayList<>();
		List<CustomerCouponEntity> usedList = new ArrayList<>();
		List<CustomerCouponEntity> overdueList = new ArrayList<>();
		for (CustomerCouponEntity customerCouponEntity : allCouponList) {
			int status = customerCouponEntity.getCouponStatus();
			CustomerCouponEntity add = new CustomerCouponEntity();
			CouponFactoryEntity factoryEntity = customerCouponEntity.getCouponFactoryEntity();
			add.setCouponName(factoryEntity.getCouponName());
			add.setCouponType(factoryEntity.getCouponType());
			add.setCouponPrice(factoryEntity.getCouponPrice());
			float discount = factoryEntity.getCouponDiscount();
			discount = discount * 100;
			if (discount % 10 == 0) {
				discount = discount / 10;
			}
			add.setCouponDiscount(discount);
			add.setCouponCondition(factoryEntity.getCouponCondition());
			add.setCouponConditionCount(factoryEntity.getCouponConditionCount());
			add.setCouponStartDate(factoryEntity.getCouponStartDate());
			add.setCouponEndDate(factoryEntity.getCouponEndDate());
			Date endDate = add.getCouponEndDate();
			Date current = new Date(System.currentTimeMillis());
			if (current.after(endDate)
					&& status != Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_OVERDUE) {
				customerCouponEntity.setCouponStatus(Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_OVERDUE);
				mCouponService.updateCustomerCoupon(customerCouponEntity);
				status = Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_OVERDUE;
			}
			add.setCouponStatus(status);
			if (status == Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_ACTIVE) {
				unusedList.add(add);
			} else if (status == Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_USED) {
				usedList.add(add);
			} else if (status == Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_OVERDUE) {
				overdueList.add(add);
			}
		}

		mv.addObject("unusedList", JSON.toJSONString(unusedList));
		mv.addObject("usedList", JSON.toJSONString(usedList));
		mv.addObject("overdueList", JSON.toJSONString(overdueList));
		mv.setViewName("order/user_coupon_list");
		return mv;
	}

	@RequestMapping("/coupon_center/getAction")
	@ResponseBody
	public Result getCouponAction(HttpSession session, CustomerCouponEntity customerCouponEntity) {
		UserInfo userInfo = getUserInfo(session);
		Result result = new Result();
		customerCouponEntity.setCouponGetTime(new Date(System.currentTimeMillis()));
		customerCouponEntity.setCouponStatus(Constant.CustomerCouponConstant.CUSTOMER_COUPON_STATUS_ACTIVE);
		boolean success = mCouponService.customerGetCoupon(customerCouponEntity, userInfo.getUserId());
		if (success) {
			result.setFlag(Result.FLAG_SUCCESS);
		} else {
			result.setFlag(Result.FLAG_FAIL);
		}
		return result;
	}
}
