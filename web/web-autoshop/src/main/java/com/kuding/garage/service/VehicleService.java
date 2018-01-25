package com.kuding.garage.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.VehicleEntity;

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
	
	
}
