package com.kuding.garage.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.service.BasicService;
import com.kuding.garage.model.CouponFactoryEntity;
import com.kuding.system.model.UserEntity;

@Service
public class CouponFactoryService extends BasicService<CouponFactoryEntity> {

	@Transactional(readOnly = false)
	public void createCoupon(CouponFactoryEntity couponFactoryEntity, int userId) {
		UserEntity userEntity = (UserEntity) getSession().load(UserEntity.class, userId);
		couponFactoryEntity.setUserEntity(userEntity);
		getSession().save(couponFactoryEntity);
		getSession().flush();
	}

	@Transactional(readOnly = true)
	public List<CouponFactoryEntity> getAllCouponFactory() {
		String hql = "from CouponFactoryEntity coupon";
		List<CouponFactoryEntity> couponList = new ArrayList<>();
		Query query = getSession().createQuery(hql);
		List<?> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object queryObject : list) {
				CouponFactoryEntity cvProduct = (CouponFactoryEntity) queryObject;
				couponList.add(cvProduct);
			}
		}
		return couponList;
	}

}
