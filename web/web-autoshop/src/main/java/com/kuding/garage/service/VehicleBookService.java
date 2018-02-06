package com.kuding.garage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.garage.model.VehicleBookInfoEntity;
import com.kuding.garage.model.VehicleEntity;
import com.kuding.system.model.SystemDicEntity;
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
	
	@Transactional(readOnly = false)
	public void saveBook(VehicleBookInfoEntity bookInfoEntity, int userId, int garageId) {
		UserEntity user = (UserEntity) getSession().load(UserEntity.class, userId);
		Logger.getLogger(VehicleBookService.class).info(""+ user == null ? "user is null" : user.getId());
		GarageInfoEntity garageInfoEntity = (GarageInfoEntity) getSession().load(GarageInfoEntity.class, garageId);
		Logger.getLogger(VehicleBookService.class).info(""+ garageInfoEntity == null ? "garageInfoEntity is null" : garageInfoEntity.getId());
		bookInfoEntity.setGarageInfoEntity(garageInfoEntity);
		bookInfoEntity.setUserEntity(user);
		String tCategoryCode = bookInfoEntity.getType().split("_")[0];
		String tCode = bookInfoEntity.getType().split("_")[1];
		SystemDicEntity dicEntity = getBizItem(tCategoryCode, tCode);
		if (dicEntity != null) {
			bookInfoEntity.setTypeName(dicEntity.getName());
		}
		getSession().save(bookInfoEntity);
		getSession().flush();
	}
	
	@Transactional(readOnly = false)
	public void updateBookState(int id, int state) {
		VehicleBookInfoEntity oldEntity = (VehicleBookInfoEntity) getSession()
				.load(VehicleBookInfoEntity.class, id);
		oldEntity.setState(state);
		getSession().update(oldEntity);
		getSession().flush();
	}
	

	
	@Transactional(readOnly = true)
	public VehicleBookInfoEntity queryBookVehicleNotCanceld(String plateNumber, int garageId) {
		VehicleBookInfoEntity vehicleBookInfoEntity = null;
		GarageInfoEntity garageInfoEntity = (GarageInfoEntity) getSession()
				.load(GarageInfoEntity.class, garageId);
		String hql = "from VehicleBookInfoEntity v where v.plateNumber = :plateNumber "
				+ "and v.garageInfoEntity = :garageInfoEntity";
		Query query = getSession().createQuery(hql);
		query.setString("plateNumber", plateNumber);
		query.setEntity("garageInfoEntity", garageInfoEntity);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				vehicleBookInfoEntity = (VehicleBookInfoEntity) queryObject;
				if (vehicleBookInfoEntity.getState() != 3) {
					return vehicleBookInfoEntity;
				}
			}
			
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<SystemDicEntity> getBizList() {
		List<SystemDicEntity> bizList = new ArrayList<>();
		String hql = "from SystemDicEntity s where s.categoryCode = :categoryCode ";
		Query query = getSession().createQuery(hql);
		query.setString("categoryCode", "biz");
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				SystemDicEntity dicEntity = (SystemDicEntity) queryObject;
				bizList.add(dicEntity);
			}
		}
		return bizList;
	}
	
	@Transactional(readOnly = true)
	public SystemDicEntity getBizItem(String categoryCode, String code) {
		SystemDicEntity entity = null;
		String hql = "from SystemDicEntity s where s.categoryCode = :categoryCode and s.code = :code";
		Query query = getSession().createQuery(hql);
		query.setString("categoryCode", categoryCode);
		query.setString("code", code);
		query.setMaxResults(1);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			entity = (SystemDicEntity) list.get(0);
		}
		return entity;
	}
	
	@Transactional(readOnly = true)
	public List<VehicleBookInfoEntity> getBookList(int garageId) {
		List<VehicleBookInfoEntity> bookList = new ArrayList<>();
		GarageInfoEntity garageInfoEntity = (GarageInfoEntity) getSession().load(GarageInfoEntity.class, garageId);
		String hql = "from VehicleBookInfoEntity v where v.garageInfoEntity = :garageInfoEntity";
		Query query = getSession().createQuery(hql);
		query.setEntity("garageInfoEntity", garageInfoEntity);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				VehicleBookInfoEntity vEntity = (VehicleBookInfoEntity) queryObject;
				bookList.add(vEntity);
			}
		}
		return bookList;
	}
	
	@Transactional(readOnly = true)
	public List<VehicleBookInfoEntity> getSuggestBookList(int garageId) {
		List<VehicleBookInfoEntity> bookList = new ArrayList<>();
		GarageInfoEntity garageInfoEntity = (GarageInfoEntity) getSession().load(GarageInfoEntity.class, garageId);
		String hql = "from VehicleBookInfoEntity v where v.garageInfoEntity = :garageInfoEntity";
		Query query = getSession().createQuery(hql);
		query.setEntity("garageInfoEntity", garageInfoEntity);
		List<?> list = query.list();
		for (Object queryObject : list) {
			VehicleBookInfoEntity vEntity = (VehicleBookInfoEntity) queryObject;
			boolean dumplicated = false;
			for (VehicleBookInfoEntity added : bookList) {
				if (added.getPlateNumber().equals(vEntity.getPlateNumber())) {
					dumplicated = true;
					break;
				}
			}
			if (!dumplicated) {
				bookList.add(vEntity);
			}
		}
		return bookList;
	}
	
	@Transactional(readOnly = true)
	public List<VehicleBookInfoEntity> getBookList(int userId, int garageId) {
		List<VehicleBookInfoEntity> bookList = new ArrayList<>();
		UserEntity user = (UserEntity) getSession().load(UserEntity.class, userId);
		GarageInfoEntity garageInfoEntity = (GarageInfoEntity) getSession().load(GarageInfoEntity.class, garageId);
		String hql = "from VehicleBookInfoEntity v where userEntity = :userEntity and garageInfoEntity = :garageInfoEntity";
		Query query = getSession().createQuery(hql);
		query.setEntity("userEntity", user);
		query.setEntity("garageInfoEntity", garageInfoEntity);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				VehicleBookInfoEntity dicEntity = (VehicleBookInfoEntity) queryObject;
				bookList.add(dicEntity);
			}
		}
		return bookList;
	}

}
