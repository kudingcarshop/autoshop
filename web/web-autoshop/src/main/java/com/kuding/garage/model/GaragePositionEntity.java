package com.kuding.garage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="garage_position")
public class GaragePositionEntity {
	
    @Id	
	@Column(name="position_code")
	private String positionCode;
	
	@Column(name="position_name")
	private String positionName;
	
	@Column(name="desc")
	private String desc;

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
