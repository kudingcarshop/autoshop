package com.kuding.garage.model;

import java.sql.Date;
import java.sql.Timestamp;

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

import com.kuding.system.model.UserEntity;

@Entity
@Table(name = "coupon_factory")
public class CouponFactoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", updatable = false, insertable = true)
	private UserEntity userEntity;

	@Column(name = "coupon_name")
	private String couponName;

	@Column(name = "coupon_type")
	private int couponType;

	@Column(name = "coupon_discount")
	private float couponDiscount;

	@Column(name = "coupon_price")
	private float couponPrice;

	@Column(name = "coupon_condition")
	private float couponCondition;

	@Column(name = "coupon_condition_count")
	private int couponConditionCount;

	@Column(name = "coupon_for_type")
	private String couponForType;

	@Column(name = "coupon_for_sku")
	private String couponForSku;

	@Column(name = "coupon_start_date")
	private Date couponStartDate;

	@Column(name = "coupon_end_date")
	private Date couponEndDate;

	@Column(name = "coupon_amount")
	private int couponAmount;

	@Column(name = "coupon_create_datetime")
	private Timestamp couponCreateDatetime;

	@Column(name = "coupon_factory_status")
	private int couponFactoryStatus;

	@Column(name = "coupon_unique_code")
	private String couponUniqueCode;
	
	@Column(name="coupon_desc")
	private String couponDesc;
	
	@Transient
	private int userGetStatus;
	
	@Transient
	private int couponRemainPercent;

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

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getCouponType() {
		return couponType;
	}

	public void setCouponType(int couponType) {
		this.couponType = couponType;
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

	public String getCouponForType() {
		return couponForType;
	}

	public void setCouponForType(String couponForType) {
		this.couponForType = couponForType;
	}

	public String getCouponForSku() {
		return couponForSku;
	}

	public void setCouponForSku(String couponForSku) {
		this.couponForSku = couponForSku;
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

	public int getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Timestamp getCouponCreateDatetime() {
		return couponCreateDatetime;
	}

	public void setCouponCreateDatetime(Timestamp couponCreateDatetime) {
		this.couponCreateDatetime = couponCreateDatetime;
	}

	public int getCouponFactoryStatus() {
		return couponFactoryStatus;
	}

	public void setCouponFactoryStatus(int couponFactoryStatus) {
		this.couponFactoryStatus = couponFactoryStatus;
	}

	public String getCouponUniqueCode() {
		return couponUniqueCode;
	}

	public void setCouponUniqueCode(String couponUniqueCode) {
		this.couponUniqueCode = couponUniqueCode;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public int getUserGetStatus() {
		return userGetStatus;
	}

	public void setUserGetStatus(int userGetStatus) {
		this.userGetStatus = userGetStatus;
	}

	public int getCouponRemainPercent() {
		return couponRemainPercent;
	}

	public void setCouponRemainPercent(int couponRemainPercent) {
		this.couponRemainPercent = couponRemainPercent;
	}

	@Override
	public String toString() {
		return "CouponFactoryEntity [id=" + id + ", userEntity=" + userEntity + ", couponName=" + couponName
				+ ", couponType=" + couponType + ", couponDiscount=" + couponDiscount + ", couponPrice=" + couponPrice
				+ ", couponCondition=" + couponCondition + ", couponConditionCount=" + couponConditionCount
				+ ", couponForType=" + couponForType + ", couponForSku=" + couponForSku + ", couponStartDate="
				+ couponStartDate + ", couponEndDate=" + couponEndDate + ", couponAmount=" + couponAmount
				+ ", couponCreateDatetime=" + couponCreateDatetime + ", couponFactoryStatus=" + couponFactoryStatus
				+ ", couponUniqueCode=" + couponUniqueCode + ", userGetStatus=" + userGetStatus
				+ ", couponRemainPercent=" + couponRemainPercent + "]";
	}



}
