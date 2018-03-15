package com.kuding.garage.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.kuding.system.model.UserEntity;

@Entity
@Table(name="garage_staff")
public class GarageStaffEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="induction_date")
	private Date inductionDate;
	
	@Column(name="job_number")
	private String jobNumber;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@OrderBy("positionCode asc")
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="garage_staff_position", joinColumns= {@JoinColumn(name = "staff_id") }, inverseJoinColumns = {
			@JoinColumn(name = "position_code") })
	private Set<GaragePositionEntity> positions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getInductionDate() {
		return inductionDate;
	}

	public void setInductionDate(Date inductionDate) {
		this.inductionDate = inductionDate;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Set<GaragePositionEntity> getPositions() {
		return positions;
	}

	public void setPositions(Set<GaragePositionEntity> positions) {
		this.positions = positions;
	}
	
}
