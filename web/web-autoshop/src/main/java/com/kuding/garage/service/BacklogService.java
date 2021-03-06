package com.kuding.garage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuding.commons.BusinessException;
import com.kuding.commons.ErrorCode;
import com.kuding.commons.service.BasicService;
import com.kuding.customer.model.VehicleMaintainInfo;
import com.kuding.garage.model.VehicleBookInfoEntity;

@Service
public class BacklogService extends BasicService<Object> {
	
	/**
	 * 查询未付款车辆
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Integer queryUnpayVehicles(Integer garageId) {
		if(garageId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select count( distinct vehicle.id) from VehicleEntity vehicle ")
					.append("left join vehicle.maintains maintain ")
					.append("left join maintain.garage garage ")
					.append("where maintain.state in ( :states ) ")
					.append("and maintain.isPay = :isPay ")
					.append("and garage.id = :garageId ");
			Query query = getSession().createQuery(hql.toString());
			query.setParameterList("states",
					new String[] {VehicleMaintainInfo.STATE_SERVED,VehicleMaintainInfo.STATE_HANDOVER});
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
			query.setInteger("garageId", garageId);
			query.setMaxResults(1);
			Long unpayCars = (Long) query.uniqueResult();
			if(unpayCars != null) {
				return unpayCars.intValue();
			}
		}
		
		return null;
	}
	
	/**
	 * 车行赊账总数统计
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Double queryUnpaySum(Integer garageId) {
		if(garageId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select sum(maintain.payPrice) from VehicleMaintainInfo maintain ")
					.append("left join maintain.garage garage ")
					.append("where maintain.state in ( :states ) ")
					.append("and maintain.isPay = :isPay ")
					.append("and garage.id = :garageId ");
			Query query = getSession().createQuery(hql.toString());
			query.setParameterList("states",
					new String[] {VehicleMaintainInfo.STATE_SERVED,VehicleMaintainInfo.STATE_HANDOVER});
			
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
			query.setInteger("garageId", garageId);
			query.setMaxResults(1);
			return (Double) query.uniqueResult();
		}
		return null;
	}
	
	/**
	 * 车行预约车辆统计
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Map<String,Integer> queryBooking(Integer garageId) {
		if(garageId != null) {
			//统计预约总数
			Map<String,Integer> bookingMap = new HashMap<>();
			StringBuffer hql = new StringBuffer()
					.append("select count(distinct booking.id) from VehicleBookInfoEntity booking ")
					.append("left join booking.garageInfoEntity garage ")
					.append("where booking.state = :state ")
					.append("and garage.id = :garageId ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("state", VehicleBookInfoEntity.STATE_BOOKING);
			query.setInteger("garageId", garageId);
			query.setMaxResults(1);
			Long total = (Long) query.uniqueResult();
			if(total != null) {
				bookingMap.put("total", total.intValue());
			}
			
			String subHql = hql.toString() + "and booking.type = :type ";
			query = getSession().createQuery(subHql);
			query.setInteger("state", VehicleBookInfoEntity.STATE_BOOKING);
			query.setInteger("garageId", garageId);
			query.setMaxResults(1);
			
			//统计美容预约数
			query.setString("type", VehicleBookInfoEntity.TYPE_BEAUTY);
			Long beautySum = (Long) query.uniqueResult();
			if(beautySum != null) {
				bookingMap.put("beautySum", beautySum.intValue());
			}
			
			//统计保养预约数
			query.setString("type", VehicleBookInfoEntity.TYPE_MAINTAIN);
			Long maintainSum = (Long) query.uniqueResult();
			if(maintainSum != null) {
				bookingMap.put("maintainSum", maintainSum.intValue());
			}
			
			//统计维修预约数
			query.setString("type", VehicleBookInfoEntity.TYPE_REPAIR);
			Long repaireSum = (Long) query.uniqueResult();
			if(repaireSum != null) {
				bookingMap.put("repaireSum", repaireSum.intValue());
			}
			
			//统计其他预约数
			query.setString("type", VehicleBookInfoEntity.TYPE_OTHER);
			Long otherSum = (Long) query.uniqueResult();
			if(otherSum != null) {
				bookingMap.put("otherSum", otherSum.intValue());
			}
			
			return bookingMap;
		}
		return null;
	}

	/**
	 * 查询服务中车辆(不包括排队中)
	 * @param garageId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Long queryServing(Integer garageId) {
		if(garageId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select count(distinct veh.id) from VehicleMaintainInfo maintain ")
					.append("left join maintain.vehicle veh ")
					.append("left join maintain.garage garage ")
					.append("where maintain.state = :states ")
					.append("and garage.id = :garageId ");
			Query query = getSession().createQuery(hql.toString());
			query.setString("states", VehicleMaintainInfo.STATE_SERVING);
			query.setInteger("garageId", garageId);
			query.setMaxResults(1);
			return (Long) query.uniqueResult();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Map<String,Object>> queryUnpayDetails(Integer garageId) {
		if(garageId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select new map(maintain.id as id,maintain.category as category,maintain.categoryName as categoryName,maintain.payPrice as payPrice,veh.plateNumber as plateNumber,maintain.receiveTime as receiveTime) ")
					.append("from VehicleMaintainInfo maintain ")
					.append("left join maintain.vehicle veh ")
					.append("left join maintain.garage garage ")
					.append("where maintain.state in ( :states ) ")
					.append("and garage.id = :garageId ")
					.append("and maintain.isPay = :isPay ")
					.append("order by maintain.receiveTime desc ");
			Query query = getSession().createQuery(hql.toString());
			query.setParameterList("states", new String[] {VehicleMaintainInfo.STATE_SERVED,VehicleMaintainInfo.STATE_HANDOVER});
			query.setInteger("garageId", garageId);
			query.setString("isPay", VehicleMaintainInfo.PAY_NO);
			return query.list();
		}
		return null;
	}
	
	/**
	 * 查询预约待处理列表
	 * @param garageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Map<String,Object>> queryBookingList(Integer garageId) {
		if(garageId != null) {
			StringBuffer hql = new StringBuffer()
					.append("select new map(booking.id as id, booking.plateNumber as plateNumber, ")
					.append("booking.bookTime as bookTime,booking.type as type, booking.typeName as typeName, ")
					.append("user.id as userId, user.phoneNumber as phoneNumber ) ")
					.append("from VehicleBookInfoEntity booking ")
					.append("left join booking.userEntity user ")
					.append("left join booking.garageInfoEntity garage ")
					.append("where booking.state = :state ")
					.append("and garage.id = :garageId ")
					.append("order by booking.bookTime asc ");
			Query query = getSession().createQuery(hql.toString());
			query.setInteger("state", VehicleBookInfoEntity.STATE_BOOKING);
			query.setInteger("garageId", garageId);
			return query.list();
		}
		return null;
	}
	
	@Transactional(readOnly=true)
	public Integer queryVehicleSumWithState(Integer garageId,String[] states) {
		StringBuffer hql = new StringBuffer()
				.append("select count( distinct vehicle.id) from VehicleEntity vehicle ")
				.append("left join vehicle.maintains maintain ")
				.append("left join maintain.garage garage ")
				.append("where 1=1 ");
		if(garageId != null) {
			hql.append("and garage.id = :garageId ");
		}
		
		if(states != null && states.length > 0) {
			hql.append("and maintain.state in ( :states ) ");
		}

		Query query = getSession().createQuery(hql.toString());
		if(garageId != null) {
			query.setInteger("garageId", garageId);
		}
		if(states != null && states.length > 0) {
			query.setParameterList("states", states);
		}
		query.setMaxResults(1);
		Long unpayCars = (Long) query.uniqueResult();
		
		return unpayCars != null ? unpayCars.intValue() : 0;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Map<String,Object>> queryVehicleListWithState(Integer garageId,String[] states) {
		StringBuffer hql = new StringBuffer()
				.append("select new map( vehicle.id as id, vehicle.plateNumber as plateNumber, ")
				.append("maintain.id as maintainId,maintain.state as state, maintain.category as category, ")
				.append("maintain.categoryName as categoryName,maintain.receiveTime as receiveTime) ")
				.append("from VehicleEntity vehicle ")
				.append("left join vehicle.maintains maintain ")
				.append("left join maintain.garage garage ")
				.append("where 1=1 ");
		if(garageId != null) {
			hql.append("and garage.id = :garageId ");
		}
		
		if(states != null && states.length > 0) {
			hql.append("and maintain.state in ( :states ) ");
		}
		hql.append("group by vehicle.id ")
		   .append("order by maintain.receiveTime asc ");
		
		Query query = getSession().createQuery(hql.toString());
		if(garageId != null) {
			query.setInteger("garageId", garageId);
		}
		if(states != null && states.length > 0) {
			query.setParameterList("states", states);
		}
		return query.list();
	}
	
	/**
	 * 更新车辆服务状态
	 * @param maintainId
	 * @param state
	 */
	@Transactional(readOnly=false, rollbackFor = { Exception.class, RuntimeException.class })
	public void updateMaintainState(Integer maintainId,String state) {
		if(maintainId == null || state == null) {
			throw new BusinessException(ErrorCode.SYS_ERROR);
		}
		VehicleMaintainInfo maintain = (VehicleMaintainInfo) getSession().load(VehicleMaintainInfo.class, maintainId);
		maintain.setState(state);
		getSession().saveOrUpdate(maintain);
	}
}
