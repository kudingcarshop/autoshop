package com.kuding.garage.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/*
 * CREATE TABLE `garage_info`
(
	`id` INTEGER NOT NULL,
	`name` VARCHAR(100) COMMENT '车行名称',
	`address` VARCHAR(200) COMMENT '车行地址',
	`lbs` TEXT COMMENT '定位地址',
	`desc` TEXT COMMENT '描述',
	CONSTRAINT `PK_garage_inf` PRIMARY KEY (`id`)
)
;
 */
@Entity
@Table(name="garage_info")
public class GarageInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="lbs")
	private String lbs;
	
	@Column(name="desc")
	private String desc;
	
	@OneToMany(mappedBy = "garageInfoEntify")
	private Set<VehicleBookInfoEntity> books;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	
}
