package com.kuding.customer.view;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class CarEditView {

	@NotNull(message="{customer.cars.edit.save.id_blank}")
	private Integer vehicleId;
	
	@NotNull(message="{customer.cars.edit.save.lastMileage_error}")
	private Integer lastMileage;
	
	@NotNull(message="{customer.cars.edit.save.lastMaintainDate_null}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastMaintainDate;
	
	@NotNull(message="{customer.cars.edit.save.lastBeautyDate_null}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastBeautyDate;
	
	@NotNull(message="{customer.cars.edit.save.tireYears_error}")
	private Double tireYears;
	
	@NotNull(message="{customer.cars.edit.save.batteryYears_error}")
	private Double batteryYears;
	
	private String name;
	
	private Date registerDate;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public Integer getLastMileage() {
		return lastMileage;
	}

	public Date getLastMaintainDate() {
		return lastMaintainDate;
	}

	public Date getLastBeautyDate() {
		return lastBeautyDate;
	}

	public Double getTireYears() {
		return tireYears;
	}

	public Double getBatteryYears() {
		return batteryYears;
	}

	public String getName() {
		return name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setLastMileage(Integer lastMileage) {
		this.lastMileage = lastMileage;
	}

	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}

	public void setLastBeautyDate(Date lastBeautyDate) {
		this.lastBeautyDate = lastBeautyDate;
	}

	public void setTireYears(Double tireYears) {
		this.tireYears = tireYears;
	}

	public void setBatteryYears(Double batteryYears) {
		this.batteryYears = batteryYears;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}
