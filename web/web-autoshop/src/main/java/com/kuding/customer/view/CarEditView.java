package com.kuding.customer.view;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class CarEditView {

	@NotBlank(message="{customer.cars.edit.save.id_blank}")
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

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
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
