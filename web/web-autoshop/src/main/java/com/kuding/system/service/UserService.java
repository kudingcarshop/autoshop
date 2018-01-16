package com.kuding.system.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.system.model.FunctionEntity;
import com.kuding.system.model.UserEntity;

@Service
public class UserService extends BasicService<UserEntity> {
	
	@Transactional(readOnly = true)
	public UserEntity login(String loginName, String password) {
		UserEntity user = null;
		if(loginName != null && password != null) {
			String hql = "from UserEntity u left outer join fetch u.roles role left outer join fetch role.functions where u.loginName = ? and u.password = ? ";
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
	
}
