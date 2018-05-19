package com.kuding.customer.view;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class CarSaveView {
	
	private String name;
	
	@NotNull(message="{customer.cars.add.save.plateNumber_error}")
	@Length(min=7,max=7,message="{customer.cars.add.save.plateNumber_error}")
	private String plateNumber;
	
	@NotNull(message="{customer.cars.add.save.registerDate_null}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date registerDate;
	
	@NotNull(message="{customer.cars.add.save.vin_error}")
	@Length(min=17,max=17,message="{customer.cars.add.save.vin_error}")
	private String vin;
	
	@NotNull(message="{customer.cars.add.save.engineNumber.null}")
	private String engineNumber;
	
	@NotNull(message="{customer.cars.add.save.lastMileage_error}")
	private Integer lastMileage;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastMaintainDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastBeautyDate;
	
	@NotNull(message="{customer.cars.add.save.tireYears_error}")
	private Double tireYears;
	
	@NotNull(message="{customer.cars.add.save.batteryYears_error}")
	private Double batteryYears;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Integer getLastMileage() {
		return lastMileage;
	}

	public void setLastMileage(Integer lastMileage) {
		this.lastMileage = lastMileage;
	}

	public Date getLastMaintainDate() {
		return lastMaintainDate;
	}

	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}

	public Date getLastBeautyDate() {
		return lastBeautyDate;
	}

	public void setLastBeautyDate(Date lastBeautyDate) {
		this.lastBeautyDate = lastBeautyDate;
	}

	public Double getTireYears() {
		return tireYears;
	}

	public void setTireYears(Double tireYears) {
		this.tireYears = tireYears;
	}

	public Double getBatteryYears() {
		return batteryYears;
	}

	public void setBatteryYears(Double batteryYears) {
		this.batteryYears = batteryYears;
	}
}
