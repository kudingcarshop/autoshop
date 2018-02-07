package com.kuding.garage.service;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.kuding.commons.service.BasicService;
import com.kuding.customer.model.VehicleMaintainInfo;

@Service
public class BacklogService extends BasicService<Object> {
	
	/**
	 * 车行赊账统计
	 * @param garageId
	 * @return
	 */
	public Map<String,Object> queryUnpayVehicles(Integer garageId) {
		Map<String,Object> unpayMap = new HashMap<>();
		if(garageId != null) {
			//查询未付款车辆
			StringBuffer hql = new StringBuffer()
					.append("select count(maintain.vehicleId) from VehicleMaintainInfo maintain ")
					.append("where maintain.state in ( :states ) ")
					.append("and maintain.isPay = :isPay ");
			Query query = getSession().createQuery(hql.toString());
			query.setParameterList("states",
					new String[] {VehicleMaintainInfo.STATE_SERVED,VehicleMaintainInfo.STATE_HANDOVER});
			
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
			query.setMaxResults(1);
			Long unpayCars = (Long) query.uniqueResult();
			if(unpayCars != null) {
				unpayMap.put("cars", unpayCars.intValue());
			}
			
			//统计未付款总额
			hql.setLength(0);
			hql.append("");
		}
		
		return unpayMap;
	}
	
	/**
	 * 车行赊账总数统计
	 * @param garageId
	 * @return
	 */
	public Float queryUnpaySum(Integer garageId) {
		
		return null;
	}

}
