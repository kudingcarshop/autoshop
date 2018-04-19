package com.kuding.customer.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kuding.garage.model.CouponFactoryEntity;
import com.kuding.system.model.UserEntity;

@Entity
@Table(name = "customer_coupon")
public class CustomerCouponEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", updatable = false, insertable = true)
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "coupon_id", updatable = false, insertable = true)
	private CouponFactoryEntity couponFactoryEntity;

	@Column(name = "coupon_status")
	private int couponStatus;

	@Column(name = "coupon_get_time")
	private Date couponGetTime;

	@Transient
	private int couponId;
	
	@Transient
	private int couponType;
	
	@Transient
	private String couponName;
	
	@Transient
	private float couponDiscount;
	
	@Transient
	private float couponPrice;
	
	@Transient
	private float couponCondition;
	
	@Transient
	private int couponConditionCount;
	
	@Transient
	private Date couponStartDate;
	
	@Transient
	private Date couponEndDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public CouponFactoryEntity getCouponFactoryEntity() {
		return couponFactoryEntity;
	}

	public void setCouponFactoryEntity(CouponFactoryEntity couponFactoryEntity) {
		this.couponFactoryEntity = couponFactoryEntity;
	}

	public int getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(int couponStatus) {
		this.couponStatus = couponStatus;
	}

	public Date getCouponGetTime() {
		return couponGetTime;
	}

	public void setCouponGetTime(Date couponGetTime) {
		this.couponGetTime = couponGetTime;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public int getCouponType() {
		return couponType;
	}

	public void setCouponType(int couponType) {
		this.couponType = couponType;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public float getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(float couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public float getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(float couponPrice) {
		this.couponPrice = couponPrice;
	}

	public float getCouponCondition() {
		return couponCondition;
	}

	public void setCouponCondition(float couponCondition) {
		this.couponCondition = couponCondition;
	}

	public int getCouponConditionCount() {
		return couponConditionCount;
	}

	public void setCouponConditionCount(int couponConditionCount) {
		this.couponConditionCount = couponConditionCount;
	}

	public Date getCouponStartDate() {
		return couponStartDate;
	}

	public void setCouponStartDate(Date couponStartDate) {
		this.couponStartDate = couponStartDate;
	}

	public Date getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(Date couponEndDate) {
		this.couponEndDate = couponEndDate;
	}
	
	

}
