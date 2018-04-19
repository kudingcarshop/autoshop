package com.kuding.garage.service;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Date;
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
import com.kuding.customer.model.TrafficViolationEntity;
import com.kuding.customer.model.VehicleMaintainInfo;
import com.kuding.garage.model.VehicleEntity;
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
					.append("and vehMain.isPay = :isPay ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
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
					.append("left join vehMain.user user ")
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
	 * 统计用户需要年审车辆记录
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,Integer> queryAnnualVertificationCount(Integer userId) {
		Map<String,Integer> result = new HashMap<>();
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("from VehicleEntity veh ")
					.append("left join fetch vehMain.user user ")
					.append("where user.id = :userId ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			List<VehicleEntity> vehList = query.list();
			if(vehList != null && vehList.size() > 0) {
				Integer annualCount = 0;
				Integer insuranceCount = 0;
				for(VehicleEntity veh : vehList) {
					if(isNeedAnnualVertification(veh.getRegisterDate())) {
						annualCount++;
					}
					
					if(isNeedInsurance(veh.getRegisterDate())) {
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
	 * 根据车辆注册日期推算年检日期
	 * @param date 车辆注册日期 
	 * @return
	 */
	private boolean isNeedAnnualVertification(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);
			
			//年检截止日期
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));
			
			//车辆购买时常
			long days = (deadline.getTime().getTime()-regCal.getTime().getTime())/1000/60/60/24;
			//六年内新车
			if(days/365<=6) {
				//新车每隔两年需要年检
				if(days/365%2==0) {
					Calendar curCal = Calendar.getInstance();
					//距离年检天数
					long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
					//距离年检前30天提醒
					if(daybeforEnd<=30 && daybeforEnd >=0 ) {
						return true;
					}
				}
			}else {
				Calendar curCal = Calendar.getInstance();
				//距离年检天数
				long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
				//距离年检前30天提醒
				if(daybeforEnd<=30 && daybeforEnd >=0 ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 根据车辆注册日期推算保险到期日
	 * @param date
	 * @return
	 */
	private boolean isNeedInsurance(Date date) {
		if(date != null) {
			Calendar regCal = Calendar.getInstance();
			regCal.setTime(date);

			//保险到期日
			Calendar deadline = Calendar.getInstance();
			deadline.set(Calendar.MONTH, regCal.get(Calendar.MONTH));
			deadline.set(Calendar.DAY_OF_MONTH,regCal.get(Calendar.DAY_OF_MONTH));

			Calendar curCal = Calendar.getInstance();
			//距离保险到期日天数
			long daybeforEnd = (deadline.getTime().getTime()-curCal.getTime().getTime())/1000/60/60/24 ;
			//前45天提醒
			if(daybeforEnd<=45 && daybeforEnd >=0 ) {
				return true;
			}
		}
		return false;
	}

}
