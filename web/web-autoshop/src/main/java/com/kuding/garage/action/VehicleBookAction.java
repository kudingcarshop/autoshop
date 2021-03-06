package com.kuding.garage.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.kuding.commons.http.Result;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.model.VehicleBookInfoEntity;
import com.kuding.garage.service.VehicleBookService;
import com.kuding.system.model.SystemDicEntity;

@Controller
@RequestMapping("/garage")
public class VehicleBookAction extends BasicAction {
	private static final int BOOK_STATE_BOOKED = 0;
	private static final int BOOK_STATE_CHECKIN = 2;
	private static final int BOOK_STATE_CANCELED = 3;

	@Autowired
	private VehicleBookService vehicleBookService;

	@RequestMapping("car_book")
	public ModelAndView show(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/car_book");
		List<SystemDicEntity> bizList = vehicleBookService.getBizList();
		mv.addObject("biz_category", bizList);
		return mv;
	}

	@RequestMapping("car_book/list")
	public ModelAndView showList(HttpServletRequest req) {
		UserInfo userInfo = getUserInfo(req.getSession());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/car_book_list");
		List<VehicleBookInfoEntity> bookList = vehicleBookService.getBookList(userInfo.getGarageId());
		List<VehicleBookInfoEntity> resultList = new ArrayList<>();
		for (VehicleBookInfoEntity book : bookList) {
			VehicleBookInfoEntity add = new VehicleBookInfoEntity();
			add.setId(book.getId());
			add.setPlateNumber(book.getPlateNumber());
			add.setTypeName(book.getTypeName());
			add.setState(book.getState());
			add.setBookTime(book.getBookTime());
			resultList.add(add);
		}
		mv.addObject("book_list", JSON.toJSONString(resultList));
		mv.addObject("isUser", false);
		return mv;
	}

	@RequestMapping("car_book/user/list")
	public ModelAndView showUserList(HttpServletRequest req) {
		UserInfo userInfo = getUserInfo(req.getSession());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sys/garage/car_book_list");
		List<VehicleBookInfoEntity> bookList = vehicleBookService.getBookList(userInfo.getUserId(), userInfo.getGarageId());
		List<VehicleBookInfoEntity> resultList = new ArrayList<>();
		for (VehicleBookInfoEntity book : bookList) {
			VehicleBookInfoEntity add = new VehicleBookInfoEntity();
			add.setId(book.getId());
			add.setPlateNumber(book.getPlateNumber());
			add.setTypeName(book.getTypeName());
			add.setState(book.getState());
			add.setBookTime(book.getBookTime());
			resultList.add(add);
		}
		mv.addObject("book_list", JSON.toJSONString(resultList));
		mv.addObject("isUser", true);
		return mv;
	}

	@RequestMapping("car_book/add")
	@ResponseBody
	public Result addBook(HttpSession session, VehicleBookInfoEntity bookInfoEntity) {
		UserInfo userInfo = getUserInfo(session);
		Result result = new Result();
		// Logger.getLogger("BookAction").info(bookInfoEntity.getId());
		VehicleBookInfoEntity booked = vehicleBookService.queryBookVehicleNotCanceld(bookInfoEntity.getPlateNumber(),
				userInfo.getGarageId());
		if (booked == null || booked.getState() == BOOK_STATE_CANCELED) {
			if (bookInfoEntity.getBookTime() == null) {
				result.setFlag(Result.FLAG_FAIL);
				result.setMessage("Please add book time.");
				return result;
			}
			bookInfoEntity.setState(BOOK_STATE_BOOKED);
			bookInfoEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			vehicleBookService.saveBook(bookInfoEntity, userInfo.getUserId(), userInfo.getGarageId());
			result.setFlag(Result.FLAG_SUCCESS);
		} else {
			result.setFlag(Result.FLAG_FAIL);
			result.setMessage("This car has been booked");
		}

		return result;
	}

	@RequestMapping("car_book/cancel")
	@ResponseBody
	public Result cancelBook(HttpSession session, VehicleBookInfoEntity bookInfoEntity) {
		Result result = new Result();
		vehicleBookService.updateBookState(bookInfoEntity.getId(), BOOK_STATE_CANCELED);
		return result;
	}

	@RequestMapping("car_book/checkin")
	@ResponseBody
	public Result checkinBook(HttpSession session, VehicleBookInfoEntity bookInfoEntity) {
		Result result = new Result();
		vehicleBookService.updateBookState(bookInfoEntity.getId(), BOOK_STATE_CHECKIN);
		return result;
	}

	@RequestMapping("car_book/query_time")
	@ResponseBody
	public Result queryBookTime(HttpSession session, VehicleBookInfoEntity bookInfoEntity) {
		UserInfo userInfo = getUserInfo(session);
		Result result = new Result();
		VehicleBookInfoEntity vehicleBookInfoEntity = vehicleBookService
				.queryBookVehicleNotCanceld(bookInfoEntity.getPlateNumber(), userInfo.getGarageId());
		if (vehicleBookInfoEntity != null && vehicleBookInfoEntity.getState() == BOOK_STATE_BOOKED) {
			result.setFlag(1);
			result.setMessage(vehicleBookInfoEntity.getBookTime().toString());
		} else {
			result.setFlag(0);
			result.setMessage("NULL");
		}
		return result;
	}

}
