package com.kuding.customer.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kuding.system.model.UserEntity;

@Entity
@Table(name="customer_consume_record")
public class ConsumeRecordEntity {
	
	/**现金消费*/
	public static final Integer TYPE_CASH_CONSUME =0;
	
	/**现金充值*/
	public static final Integer TYPE_CASH_RECHARGE =1;
	
	/**洗车卡消费*/
	public static final Integer TYPE_WASH_CARD_CONSUME =2;

	/**洗车卡充值*/
	public static final Integer TYPE_WASH_CARD_RECHARGE =3;

	/**积分获取*/
	public static final Integer TYPE_INTEGRAL_GAIN =4;

	/**积分消费*/
	public static final Integer TYPE_INTEGRAL_CONSUME =5;

	/**优惠券获取*/
	public static final Integer TYPE_COUPON_GAIN =6;

	/**优惠券消费*/
	public static final Integer TYPE_COUPON_CONSUME =7;	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@Column(name="type")
	private Integer type;
	
	@Column(name="value")
	private Double value;
	
	@Column(name="record_time")
	private Timestamp recordTime;
	
	@Column(name="desc")
	private String desc;
	
	@OneToOne
	@JoinColumn(name="maintain_id")
	private VehicleMaintainInfo maintainInfo;
	
	//后续需要根据商场设计修改
	@Column(name="order_id")
	private Integer orderId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public VehicleMaintainInfo getMaintainInfo() {
		return maintainInfo;
	}

	public void setMaintainInfo(VehicleMaintainInfo maintainInfo) {
		this.maintainInfo = maintainInfo;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
