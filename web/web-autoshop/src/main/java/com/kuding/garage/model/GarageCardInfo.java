package com.kuding.garage.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kuding.system.model.UserEntity;

@Entity
@Table(name="garage_card_info")
public class GarageCardInfo {
	
	/**卡类别：洗车卡*/
	public static final String CARD_TYPE_WASH = "01";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="type_name")
	private String typeName;
	
	@Column(name="value")
	private Float value;
	
	@Column(name="balance")
	private Float balance;
	
	@Column(name="create_time")
	private Timestamp createTime;
	
	@Column(name="expired_date")
	private Date expiredDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="garage_id")
	private GarageInfoEntity garage;
	
	@ManyToOne
	@JoinColumn(name="create_user")
	private UserEntity createUser;
	
	@Column(name="desc")
	private String desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public GarageInfoEntity getGarage() {
		return garage;
	}

	public void setGarage(GarageInfoEntity garage) {
		this.garage = garage;
	}

	public UserEntity getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserEntity createUser) {
		this.createUser = createUser;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getCardTypeWash() {
		return CARD_TYPE_WASH;
	}


}
