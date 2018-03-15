package com.kuding.garage.service;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.service.BasicService;
import com.kuding.system.model.UserEntity;

@Service
public class UserCenterService extends BasicService<UserEntity> {
	
	/**
	 * 统计用户车辆总数
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public int queryVehicleSum(Integer garageId, Integer userId){
		if(garageId == null || userId == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		StringBuffer hql = new StringBuffer()
				.append("select count(distinct vehicle.id) from VehicleEntity vehicle ")
				.append("left join vehicle.user user ")
				.append("left join user.garage garage ")
				.append("where user.id = :userId ")
				.append("and garage.id = :garageId ");
		Query query = getSession().createQuery(hql.toString());
		query.setInteger("userId", userId);
		query.setInteger("garageId", garageId);

		return ((Long)query.uniqueResult()).intValue();
	}

}
