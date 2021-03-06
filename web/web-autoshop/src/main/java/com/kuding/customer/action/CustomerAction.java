package com.kuding.customer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.http.Result;
import com.kuding.commons.login.UserInfo;
import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;
import com.kuding.customer.model.ConsumeRecordEntity;
import com.kuding.customer.view.CustomerEditView;
import com.kuding.garage.action.BasicAction;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.garage.service.CustomerService;
import com.kuding.garage.service.VehicleService;
import com.kuding.system.model.UserEntity;
import com.kuding.util.Utils;

@Controller
@RequestMapping("/customer")
public class CustomerAction extends BasicAction {
	
	@Autowired
	private CustomerService service;
	
	@Autowired
	private VehicleService vehService;
	
	/**
	 * 用户个人中心入口
	 * @param req
	 * @return
	 */
	@RequestMapping("center")
	public ModelAndView center(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		
		//用户基本信息
		UserEntity userInfo = service.findById(UserEntity.class, user.getUserId());
		if(userInfo != null) {
			mv.getModel().put("user", userInfo);
		}
		
		//用户车辆数目
		Integer vehNum = vehService.queryVehiclesByUserId(user.getUserId());
		if(vehNum != null) {
			mv.getModel().put("vehNum", vehNum);
		}
		
		//待办事项
		long backlogCount = 0;
		backlogCount += service.queryTrafficViolationCount(user.getUserId());
		backlogCount += service.queryUnPayCount(user.getUserId());
		Map<String,Integer> annualInsuranceMap = service.queryAnnualVertificationAndInsuranceCount(user.getUserId());
		if(annualInsuranceMap != null) {
			if(annualInsuranceMap.get("annual") != null) {
				backlogCount += annualInsuranceMap.get("annual");
			}
			if(annualInsuranceMap.get("insurance") != null) {
				backlogCount += annualInsuranceMap.get("insurance");
			}
		}
		mv.getModel().put("backlogCount", backlogCount);
		
		//进度查询
		long servingCount = service.queryUserServingCount(user.getUserId());
		mv.getModel().put("servingCount", servingCount);
		
		mv.setViewName("customer/center");
		return mv;
	}
	
	/**
	 * 用户基本信息编辑入口
	 * @param req
	 * @return
	 */
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		UserEntity userEntity = service.findById(UserEntity.class, user.getUserId());
		ModelAndView mv = new ModelAndView();
		if(userEntity != null) {
			mv.getModelMap().put("user", userEntity);
		}
		mv.setViewName("customer/customer_edit");
		return mv;
	}
	
	/**
	 * 更新用户基本信息
	 * @param req
	 * @param file
	 * @param userName
	 * @param phoneNumber
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("edit/save")
	public ModelAndView saveEdit(HttpServletRequest req,@ModelAttribute("user") @Validated CustomerEditView customer, BindingResult bindingResult) throws IOException{
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		
		if(bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			StringBuffer sb = new StringBuffer();
			for(ObjectError err : errors) {
				sb.append(err.getDefaultMessage());
			}
			ModelAndView mv = new ModelAndView();
			mv.setViewName("customer/customer_edit");
			mv.getModel().put("msg", sb.toString());
			return mv;
		}
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getUserId());
		userEntity.setName(customer.getName());
		userEntity.setPhoneNumber(customer.getPhoneNumber());
		
		service.updateUserInfo(userEntity,customer.getFile());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/customer/center");
		return mv;
	}
	
	/**
	 * 用户头像
	 * @param req
	 * @param resp
	 */
	@RequestMapping("headImg")
	public void customerHeadImg(HttpServletRequest req,HttpServletResponse resp) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		UserEntity userEntity = service.findById(UserEntity.class, user.getUserId());
		if(userEntity != null && userEntity.getHeadThubnail() != null) {
			resp.setContentType("image/jpg");
			try {
				resp.getOutputStream().write(userEntity.getHeadThubnail().getBytes(0, (int)userEntity.getHeadThubnail().length()));
				resp.getOutputStream().flush();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					resp.getOutputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			flushDefaultImg(req,resp);
		}
	}
	
	/**
	 * 默认头像
	 * @param req
	 * @param resp
	 */
	private void flushDefaultImg(HttpServletRequest req,HttpServletResponse resp) {
		File file = new File(req.getSession().getServletContext().getRealPath("/")+"/img/default_head_thubnail.png");
		OutputStream  os = null;
		InputStream  is = null;
		byte[] buffer = new byte[1024];
		resp.setContentType("image/png");
		try {
			os = resp.getOutputStream();
			is = new FileInputStream(file);
			while(is.read(buffer)!=-1) {
				os.write(buffer);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 我的卡包入口
	 * @param req
	 * @return
	 */
	@RequestMapping("cards")
	public ModelAndView cards(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_cards");
		mv.getModel().put("cards", service.queryUserCards(user.getUserId()));
		return mv;
	}
	
	/**
	 * 我的优惠券入口
	 * @param req
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("coupons")
	public ModelAndView coupons(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_coupons");
		
		PaginationQuery query = new PaginationQuery();
		
		mv.getModel().put("page", query.page);
		mv.getModel().put("rows", query.rows);
		
		//查询参数
		query.mapParams.put("userId", user.getUserId());
		
		StringBuffer hql = new StringBuffer()
				.append("select distinct new map(coupons.id as id, coupons.couponStatus as couponStatus,coupons.couponGetTime as couponGetTime, ")
				.append("fact.couponName as couponName,fact.couponDesc as couponDesc,")
				.append("fact.couponStartDate as couponStartDate,fact.couponEndDate as couponEndDate ) ")
				.append("from CustomerCouponEntity coupons ")
				.append("inner join coupons.userEntity user ")
				.append("inner join coupons.couponFactoryEntity fact ")
				.append("where user.id = :userId ")
				.append("order by coupons.couponGetTime desc ");
		mv.getModel().put("result", service.queryByPagination(query, hql.toString()));
		return mv;
	}
	
	/**
	 * 
	 * @param req
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("coupons/list")
	@ResponseBody
	public Result couponsQuery(HttpServletRequest req, Integer page, Integer rows) {
		Result result = new Result();
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			return result;
		}
		
		PaginationQuery query = new PaginationQuery();
		if(page != null && page > 0) {
			query.page =page;
		}
		
		if(rows != null && rows > 0) {
			query.rows = rows;
		}
		result.getExtraData().put("page", query.page);
		result.getExtraData().put("rows", query.rows);
		
		//查询参数
		query.mapParams.put("userId", user.getUserId());
		
		StringBuffer hql = new StringBuffer()
				.append("select distinct new map(coupons.id as id, coupons.couponStatus as couponStatus,coupons.couponGetTime as couponGetTime, ")
				.append("fact.couponName as couponName,fact.couponDesc as couponDesc,")
				.append("fact.couponStartDate as couponStartDate,fact.couponEndDate as couponEndDate ) ")
				.append("from CustomerCouponEntity coupons ")
				.append("inner join coupons.userEntity user ")
				.append("inner join coupons.couponFactoryEntity fact ")
				.append("where user.id = :userId ")
				.append("order by coupons.couponGetTime desc ");
		PaginationResult<?> pgResult = service.queryByPagination(query, hql.toString());
		if(pgResult != null) {
			result.setRows(pgResult.getRows());
			result.getExtraData().put("total", pgResult.getTotal());
		}
		result.setFlag(Result.FLAG_SUCCESS);
		return result;
	}
	
	/**
	 * 消费记录查询
	 * @param req
	 * @return
	 */
	@RequestMapping("consumptions")
	public ModelAndView consumptions(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		List<ConsumeRecordEntity> records = service.queryUserMonthlyConsumptions(user.getUserId());
		if(records != null && records.size() > 0) {
			mv.getModel().put("records", records);
			for(ConsumeRecordEntity record : records) {
				getLogger().info("maintain = "+ record.getMaintainInfo() );
			}
		}
		mv.setViewName("customer/customer_consumption_records");
		return mv;
	}
	
	/**
	 * 用户车辆查询
	 * @param req
	 * @return
	 */
	@RequestMapping("cars")
	public ModelAndView cars(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		List<Map<String,Object>> vehicles = vehService.queryUserVehicles(user.getUserId());
		if(vehicles != null && vehicles.size() > 0) {
			for(Map<String,Object> vehMap :vehicles) {
				if(vehMap.containsKey("veh")) {
					VehicleEntity vehicle = (VehicleEntity) vehMap.get("veh");
					Integer sugCount = 0;
					if(Utils.isNeedAnnualVertification(vehicle.getRegisterDate())) {
						sugCount++;
					}
					
					if(Utils.isNeedInsurance(vehicle.getRegisterDate())) {
						sugCount++;
					}
					
					if(Utils.isNeedMaintaine(vehicle.getLastMaintainDate())) {
						sugCount++;
					}
					vehMap.put("sugCount", sugCount);
				}
			}
			mv.getModel().put("vehicles", vehicles);
			mv.setViewName("customer/customer_cars");
		}else {
			mv.setViewName("redirect:/customer/cars/add");
		}
		return mv;
	}
	
	/**
	 * 待办事项入口
	 * @param req
	 * @return
	 */
	@RequestMapping("backlog")
	public ModelAndView backlog(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/backlog/backlog");
		//待付款
		mv.getModel().put("unpayCount", service.queryUnPayCount(user.getUserId()));
		//违章
		mv.getModel().put("vioCount", service.queryTrafficViolationCount(user.getUserId()));
		//年审及保险
		Map<String,Integer> annualInsuranceMap = service.queryAnnualVertificationAndInsuranceCount(user.getUserId());
		if(annualInsuranceMap != null) {
			Integer annualAndInsurance = 0;
			if(annualInsuranceMap.get("annual") != null) {
				annualAndInsurance += annualInsuranceMap.get("annual");
			}
			if(annualInsuranceMap.get("insurance") != null) {
				annualAndInsurance += annualInsuranceMap.get("insurance");
			}
			mv.getModel().put("annualAndInsurance", annualAndInsurance);
		}
		return mv;
	}
	

	@RequestMapping("serving/state")
	public ModelAndView servingState(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/serving_state");
		return mv;
	}
}
