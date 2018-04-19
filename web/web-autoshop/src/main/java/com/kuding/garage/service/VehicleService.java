package com.kuding.garage.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.service.BasicService;
import com.kuding.customer.model.TrafficViolationEntity;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.system.model.UserEntity;

@Service
public class VehicleService extends BasicService<VehicleEntity> {

	
	/**
	 * @param garageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<VehicleEntity> queryGarageAllCars(Integer garageId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from VehicleEntity vehicle ")
			.append("left join fetch vehicle.user user ")
			.append("left join fetch user.garage garage ");
		if(garageId != null && garageId > 0) {
			hql.append("where garage.id = ? ");
		}
		Query query = getSession().createQuery(hql.toString());
		if(garageId != null && garageId > 0) {
			query.setInteger(0, garageId);
		}
		return query.list();
	}

	/**
	 * 
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer queryWorkingCount(Integer garageId) {
		StringBuffer hql = new StringBuffer()
				.append("select distinct maintain.id from VehicleMaintainInfo maintain ")
				.append("left outer join maintain.garage garage ")
				.append("where (maintain.state = 0 or maintain.state = 1) ");
		if(garageId != null && garageId > 0) {
			hql.append("and garage.id = ?");
		}
		Query query = getSession().createQuery(hql.toString());
		if(garageId != null && garageId > 0) {
			query.setInteger(0, garageId);
		}
		return (Integer)query.list().size();
	}
	
	
	@Transactional(readOnly = true)
	public Integer queryUnPayCount(Integer garageId) {
		StringBuffer hql = new StringBuffer()
				.append(" select distinct maintain.id from VehicleMaintainInfo maintain ")
				.append("left outer join maintain.garage garage ")
				.append("where (maintain.state = 2 or  maintain.state = 3) ")
				.append("and maintain.isPay = 0 ");
		if(garageId != null && garageId > 0) {
			hql.append("and garage.id= ? ");
		}
		Query query = getSession().createQuery(hql.toString());
		if(garageId != null && garageId > 0) {
			query.setInteger(0, garageId);
		}
		return (Integer)query.list().size();
	}
	
	@Transactional(readOnly=false, rollbackFor = { Exception.class, RuntimeException.class })
	public void saveOrUpdateVehicle(VehicleEntity vehicle) {
		Logger logger = Logger.getLogger(getClass());
		if(vehicle != null) {
			UserEntity user = null;
			logger.info("vehicle.getUser() " + (vehicle.getUser() != null ));
			if(vehicle.getUser() != null 
					&& vehicle.getUser().getId() != null) {
				logger.info("load user entity");
				user = (UserEntity) getSession().load(UserEntity.class, vehicle.getUser().getId());
				vehicle.setUser(user);
			}
			getSession().save(vehicle);
		}
	}
	
	/**
	 * 判断车牌号码是否存在
	 * @param plateNumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isPlateNumberExist(String plateNumber) {
		if(StringUtils.isAllEmpty("plateNumber")) {
			throw new BusinessException("plate number is null ", ErrorCode.BIZ_VEH_PLATENO_NULL);
		}
		StringBuffer hql = new StringBuffer("from VehicleEntity vehicle where vehicle.plateNumber=:plateNumber ");
		Query query = getSession().createQuery(hql.toString());
		query.setString("plateNumber", plateNumber);
		List<?> list = query.list();
		return list != null && list.size() > 0 ;
	}
	
	/**
	 * 判断车辆是否存在
	 * @param vin
	 * @param engineNumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean isVehicleExist(String vin, String engineNumber) {
		if(StringUtils.isAllEmpty("vin") || StringUtils.isAllEmpty(engineNumber)) {
			throw new BusinessException("vin or engine number is null ", ErrorCode.BIZ_VEH_VIN_ENGNO_NULL);
		}
		StringBuffer hql = new StringBuffer("from VehicleEntity vehicle ")
				.append("where vehicle.vin=:vin ")
				.append("and vehicle.engineNumber=:engineNumber");
		Query query = getSession().createQuery(hql.toString());
		query.setString("vin", vin);
		query.setString("engineNumber", engineNumber);
		List<?> list = query.list();
		return list != null && list.size() > 0 ;
	}
	
	/**
	 * 获取车辆详情信息
	 * @param vehicleId
	 * @return
	 */
	@Transactional(readOnly=true)
	public VehicleEntity queryVehicleDetail(Integer vehicleId) {
		if(vehicleId != null ) {
			Query query = getSession().createQuery("from VehicleEntity veh left join fetch veh.user where veh.id = :id ");
			query.setInteger("id", vehicleId);
			query.setMaxResults(1);
			return (VehicleEntity) query.uniqueResult();
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	public Integer queryTrafficViolationCount(Integer vehicleId) {
		if(vehicleId != null ) {
			StringBuffer hql = new StringBuffer()
					.append("select count(vio.id) from TrafficViolationEntity vio ")
					.append("left join vio.vehicle veh ")
					.append("where veh.id=:id ")
					.append("and vio.state = :state ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("id", vehicleId);
			query.setString("state", TrafficViolationEntity.STATE_UNHANDLE);
			query.setMaxResults(1);
			return ((Long) query.uniqueResult()).intValue();
		}
		return null;
	}
	
	/**
	 * 统计用户名下车辆数目
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Integer queryVehiclesByUserId(Integer userId) {
		if(userId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select count (distinct veh.id) from VehicleEntity veh ")
					.append("left join veh.user user ")
					.append("where user.id = :userId ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("userId", userId);
			return ((Long)query.uniqueResult()).intValue();
		}
		
		return 0;
	}
	
}
