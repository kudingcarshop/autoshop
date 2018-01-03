package com.kuding.garage.action;

import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.VehicleInfoEntify;

public class VehicleService extends BasicService<VehicleInfoEntify> {

	
	/**
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public int queryGarageAllCars(Integer garageId) {
		StringBuilder sb = new StringBuilder();
		sb.append("from VehicleInfoEntity vehicle")
		   .append("where ");
		
		return 0;
	}
}
