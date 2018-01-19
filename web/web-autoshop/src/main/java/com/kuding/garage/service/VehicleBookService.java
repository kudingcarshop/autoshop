package com.kuding.garage.service;

import org.springframework.stereotype.Service;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.garage.model.VehicleBookInfoEntity;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.system.model.UserEntity;

@Service
public class VehicleBookService extends BasicService<VehicleBookInfoEntity> {
	
	public UserEntity getUserEntity(int id) {
		return (UserEntity) getSession().load(UserEntity.class, id);
	}
	
	public GarageInfoEntity getGarageInfoEntity(int id) {
		return (GarageInfoEntity) getSession().load(GarageInfoEntity.class, id);
	}
	
	public VehicleEntity getVehicleEntity(int id) {
		return (VehicleEntity) getSession().load(VehicleEntity.class, id);
	}

}
