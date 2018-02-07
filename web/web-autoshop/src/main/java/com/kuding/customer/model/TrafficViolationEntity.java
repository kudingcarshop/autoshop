package com.kuding.customer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kuding.garage.model.VehicleEntity;
import com.kuding.system.model.UserEntity;

@Entity
@Table(name="traffic_violation")
public class TrafficViolationEntity {
	
	/**违章处理状态:未处理-0*/
	public static final String STATE_UNHANDLE = "0";
	
	/**违章处理状态:已处理-1*/
	public static final String STATE_HANDLED = "1";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="state")
	private String state;
	
	@Column(name="violation_desc")
	private String violationDesc;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="vehicle_id")
	private VehicleEntity vehicle;

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

	public String getViolationDesc() {
		return violationDesc;
	}

	public void setViolationDesc(String violationDesc) {
		this.violationDesc = violationDesc;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

}
