package com.kuding.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.system.model.FunctionEntity;
import com.kuding.system.model.UserEntity;

@Service
public class UserService extends BasicService<UserEntity> {
	
	@Transactional(readOnly = true)
	public UserEntity login(String loginName, String password) {
		UserEntity user = null;
		if(loginName != null && password != null) {
			String hql = "from UserEntity u "
					+ "left outer join fetch u.roles role "
					+ "left outer join fetch role.functions "
					+ "where u.loginName = ? and u.password = ? ";
			Query query = getSession().createQuery(hql);
			query.setString(0, loginName);
			query.setString(1, password);
			query.setMaxResults(1);
			List<?> list = query.list();
			if(list != null && list.size() > 0) {
				user = (UserEntity) list.get(0);
			}
		}
		return user;
	}
	
	@Transactional(readOnly = true)
	public GarageInfoEntity queryGarageInfo(Integer userId) {
		GarageInfoEntity garage = null;
		if(userId != null) {
			StringBuffer hql = new StringBuffer("from GarageInfoEntity garage ")
					.append(" left join fetch garage.users user ")//fetch返回数据以对象形式封装 否则以对象数组的形式
					.append("where user.id = ? ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger(0, userId);
			query.setFetchSize(1);
			List<?> list = query.list();
			if(list != null && list.size() > 0) {
				garage = (GarageInfoEntity) list.get(0);
			}
		}
		
		return garage;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<FunctionEntity> queryUserFuns(String loginName){
		StringBuffer hql = new StringBuffer("from FunctionEntity fun left outer join fetch fun.roles role  ")
				.append("left outer join fetch role.users user ")
				.append("where user.loginName = ?");
		Query query = getSession().createQuery(hql.toString());
		query.setString(0, loginName);
		return (List<FunctionEntity>) query.list();
	}
	
	/***
	 * 查询车行用户列表 用于选择用户操作
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String,String>> queryGarageUsers(Integer garageId){
		if(garageId != null && garageId > 0) {
			StringBuffer hql = new StringBuffer("select distinct user.id, user.name from UserEntity user  ")
					.append("left outer join user.garage garage ")
					.append("where garage.id = :id ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("id", garageId);
			List<?> res = query.list();
			if(res != null && res.size() > 0) {
				List<Map<String,String>> users = new ArrayList<Map<String,String>>();
				for(Object obj : res) {
					Object[] objs = (Object[]) obj;
					Map<String,String> user = new HashMap<String,String>();
					user.put("id", objs[0]+"");
					user.put("name", objs[1]+"");
					users.add(user);
				}
				return users;
			}
		}
		return null;
	}
	
}
