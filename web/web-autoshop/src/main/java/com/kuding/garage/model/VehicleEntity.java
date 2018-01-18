package com.kuding.garage.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kuding.customer.model.CustomerInfoEntity;

@Entity
@Table(name="vehicle_info")
public class VehicleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="plate_number")
	private String plateNumber;

	@Column(name="engine_number")
	private String engineNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", insertable = false, updatable = false)
	private CustomerInfoEntity customerInfo;
	
	@Column(name = "license_date")
	private Date liscenDate;
	
	@Column(name = "car_brand")
	private String carBrand;
	
	@Column(name = "mileage")
	private Integer mileage;
	
	@Column(name = "insurance_date")
	private Date insuranceDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public CustomerInfoEntity getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfoEntity customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Date getLiscenDate() {
		return liscenDate;
	}

	public void setLiscenDate(Date liscenDate) {
		this.liscenDate = liscenDate;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
}
