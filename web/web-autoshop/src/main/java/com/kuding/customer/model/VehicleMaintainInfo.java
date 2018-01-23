package com.kuding.customer.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kuding.garage.model.GarageInfoEntity;

@Entity
@Table(name="vehicle_maintain_info")
public class VehicleMaintainInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="state")
	private String state;
	
	@Column(name="category")
	private String category;
	
	@Column(name="mileage")
	private int mileage;
	
	@Column(name="battery_service_year")
	private int batteryServiceYear;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CustomerInfoEntity customer;
	
	@ManyToOne
	@JoinColumn(name="garage_id")
	private GarageInfoEntity garage;
	
	@Column(name="price")
	private float price;
	
	@Column(name="pay_price")
	private float payPrice;
	
	@Column(name="is_pay")
	private String isPay;
	
	@Column(name="memo")
	private String memo;
	
	@Column(name="receive_time")
	private Timestamp receiveTime;
	
	@Column(name="start_time")
	private Timestamp startTime;
	
	@Column(name="end_time")
	private Timestamp endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getBatteryServiceYear() {
		return batteryServiceYear;
	}

	public void setBatteryServiceYear(int batteryServiceYear) {
		this.batteryServiceYear = batteryServiceYear;
	}

	public CustomerInfoEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfoEntity customer) {
		this.customer = customer;
	}

	public GarageInfoEntity getGarage() {
		return garage;
	}

	public void setGarage(GarageInfoEntity garage) {
		this.garage = garage;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(float payPrice) {
		this.payPrice = payPrice;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
}
