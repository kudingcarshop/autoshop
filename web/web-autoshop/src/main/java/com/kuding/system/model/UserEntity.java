package com.kuding.system.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kuding.customer.model.ConsumeRecordEntity;
import com.kuding.garage.model.GarageInfoEntity;
import com.kuding.garage.model.VehicleBookInfoEntity;
import com.kuding.garage.model.VehicleEntity;

@Entity
@Table(name="sys_user")
public class UserEntity {
	
	/**用户类型: 平台用户*/
	public static final int USER_TYPE_SYS= 0;
	
	/**用户类型: 车行用户*/
	public static final int USER_TYYPE_GAR = 1;
	
	/**用户类型: 车主*/
	public static final int USER_YTPE_CUST= 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password")
	private String password;
	
	@Column(name="type")
	private Integer type;

	@Column(name = "state")
	private Integer state;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "last_modify_time")
	private Timestamp lastModifiedTime;

	@Column(name = "last_modify_user")
	private String lastModifiedUser;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="birthday")
	private String birthday;

	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_code") })
	private Set<RoleEntity> roles;

	@OneToMany(mappedBy = "userEntity")
	private Set<VehicleBookInfoEntity> books;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="garage_id")
	private GarageInfoEntity garage;
	
	@OneToMany(mappedBy="user")
	private Set<VehicleEntity> vehicles;
	
	@Column(name="head_thubnail")
	private Blob headThubnail;
	
	@Column(name="customer_integral")
	private Integer integral;
	
	@OneToMany(mappedBy="user")
	private Set<ConsumeRecordEntity> consumeRecords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public Set<VehicleBookInfoEntity> getBooks() {
		return books;
	}

	public void setBooks(Set<VehicleBookInfoEntity> books) {
		this.books = books;
	}

	public GarageInfoEntity getGarage() {
		return garage;
	}

	public void setGarage(GarageInfoEntity garage) {
		this.garage = garage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Set<VehicleEntity> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<VehicleEntity> vehicles) {
		this.vehicles = vehicles;
	}

	public Blob getHeadThubnail() {
		return headThubnail;
	}

	public void setHeadThubnail(Blob headThubnail) {
		this.headThubnail = headThubnail;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Set<ConsumeRecordEntity> getConsumeRecords() {
		return consumeRecords;
	}

	public void setConsumeRecords(Set<ConsumeRecordEntity> consumeRecords) {
		this.consumeRecords = consumeRecords;
	}

}
