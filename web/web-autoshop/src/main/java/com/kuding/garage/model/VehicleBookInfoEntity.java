package com.kuding.garage.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kuding.system.model.UserEntity;

/*
CREATE TABLE `garage_biz_book`
(
	`id` INTEGER NOT NULL,
	`plate_number` VARCHAR(15) COMMENT '车牌号',
	`type` VARCHAR(2) COMMENT '预约业务类别:1:',
	`state` INTEGER COMMENT '预约状态：0:预约 2:已接待 3:取消',
	`user_id` INTEGER COMMENT '预约用户',
	`book_time` TIMESTAMP COMMENT '预约时间',
	`garage_id` INTEGER COMMENT '预约车行',
	`create_time` TIMESTAMP COMMENT '记录生成时间',
	`memo` VARCHAR(50) COMMENT '备注说明',
	CONSTRAINT `PK_garage_biz_book` PRIMARY KEY (`id`)
)
;
 * 
 */
@Entity
@Table(name = "garage_biz_book")
public class VehicleBookInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "plate_number")
	private String plateNumber;

	@Column(name = "type")
	private String type;

	@Column(name = "state")
	private Integer state;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
	private UserEntity userEntity;

	@Column(name = "book_time")
	private Timestamp bookTime;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "garage_id", updatable = false, insertable = false)
	private GarageInfoEntity garageInfoEntify;

	@Column(name = "create_time")
	private Timestamp createTime;

	@Column(name = "memo")
	private String memo;

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

	public Integer getState() {
		return state;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getBookTime() {
		return bookTime;
	}

	public void setBookTime(Timestamp bookTime) {
		this.bookTime = bookTime;
	}

	public GarageInfoEntity getGarageInfoEntify() {
		return garageInfoEntify;
	}

	public void setGarageInfoEntify(GarageInfoEntity garageInfoEntify) {
		this.garageInfoEntify = garageInfoEntify;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

}
