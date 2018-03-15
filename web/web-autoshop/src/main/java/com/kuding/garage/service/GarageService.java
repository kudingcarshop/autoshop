package com.kuding.garage.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.garage.model.GarageStaffEntity;

@Service
public class GarageService extends BasicService<GarageInfoEntity> {
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<GarageStaffEntity> queryGarageStaff(Integer garageId){
		if(garageId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select staff from GarageStaffEntity staff ")
					.append("left join staff.user user ")
					.append("left join user.garage garage ")
					.append("where garage.id = :garageId ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("garageId", garageId);
			return query.list();
		}
		return null;
	}
}
