package com.kuding.garage.service;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;
import com.kuding.commons.service.BasicService;
import com.kuding.customer.model.ConsumeRecordEntity;
import com.kuding.customer.model.TrafficViolationEntity;
import com.kuding.customer.model.VehicleMaintainInfo;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.system.model.UserEntity;
import com.kuding.util.Utils;

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
	
	/**
	 * 更新用户基本信息
	 * @param user
	 * @throws IOException 
	 */
	@Transactional(readOnly=false, rollbackFor = { Exception.class, RuntimeException.class })
	public void updateUserInfo(UserEntity user,MultipartFile file) throws IOException {
		if(user != null && user.getId() != null) {
			UserEntity userEntity = findById(UserEntity.class, user.getId());
			if(userEntity != null) {
				if(!isUserNameDuplicated(user.getName(),user.getId())) {
					userEntity.setName(user.getName());
					userEntity.setPhoneNumber(user.getPhoneNumber());
					if(file != null && file.getSize() > 0) {
						Blob img = Hibernate.getLobCreator(getSession()).createBlob(file.getBytes());
						if(img != null) {
							userEntity.setHeadThubnail(img);
						}
					}
					save(userEntity);
				}
			}
		}
	}
	
	/**
	 * 判断用户名称是否重复
	 * @param userName
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUserNameDuplicated(String userName,Integer userId) {
		if(userName != null 
				&& userName.trim().length()>0 
				&& userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("from UserEntity user ")
					.append("where user.name = :userName ")
					.append("and user.id != :userId ");
			Query query = getSession().createQuery(hql.toString());
			query.setString("userName", userName);
			query.setInteger("userId", userId);
			return query.list().size() > 0;
		}
		return true;
	}
	
	/**
	 * 查询用户未付款记录数
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public long queryUnPayCount(Integer userId) {
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select count(distinct vehMain.id) from VehicleMaintainInfo vehMain ")
					.append("left join vehMain.user user ")
					.append("where user.id = :userId ")
					.append("and vehMain.isPay = :isPay ")
					.append("and vehMain.state in (:state) ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
			query.setParameterList("state", new String[] {VehicleMaintainInfo.STATE_SERVED,VehicleMaintainInfo.STATE_HANDOVER});
			return (long) query.uniqueResult();
		}
		return 0;
	}
	
	/**
	 * 统计用户未处理违章记录
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public long queryTrafficViolationCount(Integer userId) {
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select count(distinct vio.id) from TrafficViolationEntity vio ")
					.append("left join vio.user user ")
					.append("where user.id = :userId ")
					.append("and vio.state = :state ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			query.setString("state", TrafficViolationEntity.STATE_UNHANDLE);
			return (long) query.uniqueResult();
		}
		return 0;
	}
	
	/**
	 * 统计用户需要年审及保险数
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,Integer> queryAnnualVertificationAndInsuranceCount(Integer userId) {
		Map<String,Integer> result = new HashMap<>();
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("from VehicleEntity veh ")
					.append("left join fetch veh.user user ")
					.append("where user.id = :userId ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			List<VehicleEntity> vehList = query.list();
			if(vehList != null && vehList.size() > 0) {
				Integer annualCount = 0;
				Integer insuranceCount = 0;
				for(VehicleEntity veh : vehList) {
					if(Utils.isNeedAnnualVertification(veh.getRegisterDate())) {
						annualCount++;
					}
					
					if(Utils.isNeedInsurance(veh.getRegisterDate())) {
						insuranceCount++;
					}
				}
				result.put("annual", annualCount);
				result.put("insurance", insuranceCount);
			}
		}
		return result;
	}
	
	/**
	 * 用户正在服务车辆统计
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public long queryUserServingCount(Integer userId) {
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select count(distinct vehMain.id) from VehicleMaintainInfo vehMain ")
					.append("left join vehMain.user user ")
					.append("where user.id = :userId ")
					.append("and vehMain.state in (:state) ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			query.setParameterList("state", new String[] {VehicleMaintainInfo.STATE_QUEUE,VehicleMaintainInfo.STATE_SERVING});
			return (long) query.uniqueResult();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConsumeRecordEntity> queryUserMonthlyConsumptions(Integer userId){
		List<ConsumeRecordEntity> res = null;
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select records from ConsumeRecordEntity records ")
					.append("left join records.user user ")
					.append("left join records.maintainInfo maintain ")
					.append("where user.id = :userId ")
					.append("and records.recordTime >= :firstDay ")
					.append("order by records.recordTime desc ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			query.setTimestamp("firstDay", firtDayOfMonth());
			res = query.list();
		}
		return res;
	}
	
	/**
	 * 获取当前月份的第一天
	 * @return
	 */
	private Timestamp firtDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * 查询用户未待付款列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=false, rollbackFor = { Exception.class, RuntimeException.class })
	public List<VehicleMaintainInfo> queryUnPayList(Integer userId) {
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select distinct vehMain from VehicleMaintainInfo vehMain ")
					.append("left join fetch vehMain.user user ")
					.append("left join fetch vehMain.vehicle veh ")
					.append("where user.id = :userId ")
					.append("and vehMain.isPay = :isPay ")
					.append("and vehMain.state in (:state) ")
					.append("order by veh.plateNumber asc ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
			query.setParameterList("state", new String[] {VehicleMaintainInfo.STATE_SERVED,VehicleMaintainInfo.STATE_HANDOVER});
			return query.list();
		}
		return null;
	}
}
