package com.kuding.customer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.login.UserInfo;
import com.kuding.garage.action.BasicAction;
import com.kuding.garage.service.CustomerService;
import com.kuding.system.model.UserEntity;

@Controller
@RequestMapping("/customer")
public class CustomerAction extends BasicAction {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("center")
	public ModelAndView center(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		
		UserEntity userInfo = service.findById(UserEntity.class, user.getUserId());
		if(userInfo != null) {
			mv.getModel().put("user", userInfo);
		}
		
		mv.setViewName("customer/center");
		return mv;
	}
	
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
	
	
	@RequestMapping("edit/save")
	public ModelAndView saveEdit(HttpServletRequest req,MultipartFile file,String userName,String phoneNumber) throws IOException{
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		//全部为空
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(phoneNumber)) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/customer/edit");
			return mv;
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getUserId());
		
		if(!StringUtils.isEmpty(userName)) {
			userEntity.setName(userName);
		}
		
		if(!StringUtils.isEmpty(phoneNumber)) {
			userEntity.setPhoneNumber(phoneNumber);
		}
		service.updateUserInfo(userEntity,file);
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("flag", "1");//
		mv.setViewName("redirect:/customer/center");
		return mv;
	}
	
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
	 * 当用户没有设置头像时提供默认头像图片
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
	
	
	@RequestMapping("cards")
	public ModelAndView cards(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_cards");
		return mv;
	}
	
	@RequestMapping("consumptions")
	public ModelAndView consumptions(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_consumption_records");
		return mv;
	}
	
	@RequestMapping("cars")
	public ModelAndView cars(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/customer_cars");
		return mv;
	}
	
	@RequestMapping("backlog")
	public ModelAndView backlog(HttpServletRequest req) {
		UserInfo user = getUserInfo(req.getSession());
		if(user == null || user.getUserId() == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/backlog/backlog");
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
