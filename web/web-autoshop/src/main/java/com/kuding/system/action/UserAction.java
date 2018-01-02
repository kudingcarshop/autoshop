package com.kuding.system.action;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuding.commons.http.Result;
import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;
import com.kuding.system.model.UserEntity;
import com.kuding.system.service.UserService;

@Controller
@RequestMapping("/sys")
public class UserAction {
	
	@Autowired
	private UserService userService;

    @RequestMapping("user/query")
    @ResponseBody
    public PaginationResult<UserEntity> queryUsers(){
    	PaginationQuery pageQuery = new PaginationQuery();
    	StringBuffer hql = new StringBuffer();
    	hql.append("from UserEntity u");
    	PaginationResult<UserEntity> result = userService.queryByPagination(pageQuery, hql.toString());
        return result;
    }
    
    @RequestMapping("user/show")
    public String showIndex() {
    	return "sys/user/index.jsp";
    }
    
    @RequestMapping("user/register")
    @ResponseBody
    public Result addUser(UserEntity user) {
    	Result result = new Result();
    	Logger.getLogger("UserAction").info(user.getLoginName());
    	user.setCreateTime(new Timestamp(System.currentTimeMillis()));
    	user.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
    	userService.save(user);
    	return result;
    }
    
}
