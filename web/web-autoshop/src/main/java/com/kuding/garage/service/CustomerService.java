package com.kuding.garage.service;

import java.math.BigInteger;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;
import com.kuding.commons.service.BasicService;
import com.kuding.system.model.UserEntity;

@Service
public class CustomerService extends BasicService<UserEntity> {
	
	
	/**
	 * 统计车行车主总数
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public int queryUserSum(Integer garageId){
		if(garageId == null ) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		StringBuffer hql = new StringBuffer()
				.append("select count(distinct user.id) from UserEntity user ")
				.append("left join user.garage garage ")
				.append("where garage.id = :garageId ")
				.append("and user.type in (:type) ");
		Query query = getSession().createQuery(hql.toString());
		query.setInteger("garageId", garageId);
		query.setParameterList("type", new Integer[] {2,3});

		return ((Long)query.uniqueResult()).intValue();
	}
	
	/**
	 * 查询用户信息列表
	 * @param pageQuery 只支持参数通过mapParams传递
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public PaginationResult<Map<String,Object>> queryUsersBySqlPagination(PaginationQuery pageQuery){
		PaginationResult<Map<String,Object>> result = new PaginationResult<>();
		
		StringBuffer sql = new StringBuffer()
				.append("select ")
				.append("user.*, ")
				.append("(select count(veh.id) as veh_num from vehicle_info veh where veh.user_id = user.id) as veh_num, ")
				.append("(select max(veh.last_garage_date) from vehicle_info veh where veh.user_id= user.id  group by veh.user_id) as last_garage_date ")
				.append("from sys_user user ")
				.append("where 1= 1 ");
		formQuerySql(sql,pageQuery.mapParams);
		
		
		String countSql = "select count(*) from ( " + sql +" )";
		SQLQuery countQuery = getSession().createSQLQuery(countSql);
		formQuery(countQuery,pageQuery.mapParams);
		result.setTotal(((BigInteger)countQuery.uniqueResult()).intValue());
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		formQuery(query,pageQuery.mapParams);
		query.setFirstResult((pageQuery.page-1) * pageQuery.rows);
		query.setMaxResults(pageQuery.rows);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		result.setRows(query.list());
		return result;
	}
	
	private void formQuerySql(StringBuffer sql,Map<String,Object> params) {
		if(sql != null && params!= null && params.size() >0) {
			if(params.get("garageId") != null) {
				sql.append("and user.garage_id=:garageId ");
			}
			
			//多字段模糊查询
			if(params.get("search") != null) {
				sql.append("and user.name like :search ");
			}
			
			//查询客户类型
			if(params.get("type") != null) {
				sql.append("and user.type in (:type) ");
			}
		}
	}
	
	private void formQuery(Query query,Map<String,Object> params) {
		if(query != null && params!= null && params.size() >0) {
			if(params.get("garageId") != null) {
				query.setParameter("garageId", params.get("garageId"));
			}
			
			//模糊查询
			if(params.get("search") != null) {
				query.setParameter("search", "%"+params.get("search")+"%");
			}
			
			//查询客户类型
			if(params.get("type") != null) {
				query.setParameterList("type", (Integer[])params.get("type"));
			}
		}
	}

}
