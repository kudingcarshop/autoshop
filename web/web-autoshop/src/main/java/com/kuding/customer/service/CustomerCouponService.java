package com.kuding.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.customer.model.CustomerCouponEntity;
import com.kuding.garage.model.CouponFactoryEntity;
import com.kuding.system.model.UserEntity;

@Service
public class CustomerCouponService extends BasicService<CustomerCouponEntity> {

	@Transactional(readOnly = true)
	public boolean isCustomerHasCoupon(int userId, CouponFactoryEntity couponFactoryEntity) {
		UserEntity user = (UserEntity) getSession().load(UserEntity.class, userId);
		String hql = "from CustomerCouponEntity coupon where userEntity = :userEntity "
				+ "and couponFactoryEntity = :couponFactoryEntity";
		List<CustomerCouponEntity> couponList = new ArrayList<>();
		Query query = getSession().createQuery(hql);
		query.setEntity("userEntity", user);
		query.setEntity("couponFactoryEntity", couponFactoryEntity);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				CustomerCouponEntity cvProduct = (CustomerCouponEntity) queryObject;
				couponList.add(cvProduct);
			}
		}
		return !couponList.isEmpty();
	}
	
	@Transactional(readOnly = true)
	public List<CustomerCouponEntity> getAllCustomerCoupon(int userId) {
		UserEntity user = (UserEntity) getSession().load(UserEntity.class, userId);
		String hql = "from CustomerCouponEntity coupon where userEntity = :userEntity";
		List<CustomerCouponEntity> couponList = new ArrayList<>();
		Query query = getSession().createQuery(hql);
		query.setEntity("userEntity", user);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				CustomerCouponEntity cvProduct = (CustomerCouponEntity) queryObject;
				couponList.add(cvProduct);
			}
		}
		return couponList;
	}
	
	@Transactional(readOnly = false)
	public boolean updateCustomerCoupon(CustomerCouponEntity customerCouponEntity) {
		getSession().update(customerCouponEntity);
		getSession().flush();
		return true;
	}
	
	
	@Transactional(readOnly = false)
	public boolean customerGetCoupon(CustomerCouponEntity customerCouponEntity, int userId) {
		int couponId = customerCouponEntity.getCouponId();
		CouponFactoryEntity couponFactoryEntity = (CouponFactoryEntity) getSession().load(CouponFactoryEntity.class, couponId);
		int couponAmount = couponFactoryEntity.getCouponAmount();
		if (couponAmount <= 0) {
			return false;
		} else {
			couponAmount --;
			couponFactoryEntity.setCouponAmount(couponAmount);
			getSession().update(couponFactoryEntity);
			getSession().flush();
			UserEntity userEntity = (UserEntity) getSession().load(UserEntity.class, userId);
			customerCouponEntity.setCouponFactoryEntity(couponFactoryEntity);
			customerCouponEntity.setUserEntity(userEntity);
			getSession().save(customerCouponEntity);
			getSession().flush();
			return true;
		}
	}

}
