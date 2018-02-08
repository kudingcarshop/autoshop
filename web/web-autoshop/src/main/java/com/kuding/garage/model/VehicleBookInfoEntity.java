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
	
	/**预约状态：预约*/
	public static final int STATE_BOOKING = 0;
	/**预约状态：已接待*/
	public static final int STATE_SERVED = 1;
	/**预约状态：取消*/
	public static final int STATE_CANCLE = 2;
	
	/**预约业务类型:美容*/
	public static final String TYPE_BEAUTY = "biz_10001";
	/**预约业务类型:保养*/
	public static final String TYPE_MAINTAIN = "biz_10002";
	/**预约业务类型:维修*/
	public static final String TYPE_REPAIR = "biz_10003";
	/**预约业务类型:其它*/
	public static final String TYPE_OTHER = "biz_10004";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "plate_number")
	private String plateNumber;

	@Column(name = "type")
	private String type;
	
	@Column(name = "type_name")
	private String typeName;

	@Column(name = "state")
	private Integer state;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", updatable = false, insertable = true)
	private UserEntity userEntity;

	@Column(name = "book_time")
	private Timestamp bookTime;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "garage_id")
	private GarageInfoEntity garageInfoEntity;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public GarageInfoEntity getGarageInfoEntity() {
		return garageInfoEntity;
	}

	public void setGarageInfoEntity(GarageInfoEntity garageInfoEntity) {
		this.garageInfoEntity = garageInfoEntity;
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
