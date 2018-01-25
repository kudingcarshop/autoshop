package com.kuding.garage.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kuding.system.model.UserEntity;

@Entity
@Table(name="garage_info")
public class GarageInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="establish_date")
	private Date establishDate;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone_no")
	private String phoneNo;
	
	@Column(name="carwash_rack")
	private Integer carwashRack;
	
	@Column(name="car_repairing_rack")
	private Integer carRepairingRack;
	
	@Column(name="painting_rack")
	private Integer paintingRack;
	
	@Column(name="lbs")
	private String lbs;
	
	@Column(name="desc")
	private String desc;
	
	@OneToMany(mappedBy = "garageInfoEntify")
	private Set<VehicleBookInfoEntity> books;
	
	@OneToMany(mappedBy="garage")
	private Set<UserEntity> users;

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

	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getCarwashRack() {
		return carwashRack;
	}

	public void setCarwashRack(Integer carwashRack) {
		this.carwashRack = carwashRack;
	}

	public Integer getCarRepairingRack() {
		return carRepairingRack;
	}

	public void setCarRepairingRack(Integer carRepairingRack) {
		this.carRepairingRack = carRepairingRack;
	}

	public Integer getPaintingRack() {
		return paintingRack;
	}

	public void setPaintingRack(Integer paintingRack) {
		this.paintingRack = paintingRack;
	}

	public String getLbs() {
		return lbs;
	}

	public void setLbs(String lbs) {
		this.lbs = lbs;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<VehicleBookInfoEntity> getBooks() {
		return books;
	}

	public void setBooks(Set<VehicleBookInfoEntity> books) {
		this.books = books;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
}
