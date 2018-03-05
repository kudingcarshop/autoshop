﻿/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.0 		*/
/*  Created On : 09-二月-2018 15:40:44 				*/
/*  DBMS       : MySql 						*/
/* ---------------------------------------------------- */

USE `autoshop`
;

SET FOREIGN_KEY_CHECKS=0

/* Drop Tables */

DROP TABLE IF EXISTS `driving_license` CASCADE
;

DROP TABLE IF EXISTS `garage_biz` CASCADE
;

DROP TABLE IF EXISTS `garage_biz_book` CASCADE
;

DROP TABLE IF EXISTS `garage_card_info` CASCADE
;

DROP TABLE IF EXISTS `garage_discount_coupon` CASCADE
;

DROP TABLE IF EXISTS `garage_info` CASCADE
;

DROP TABLE IF EXISTS `sys_dict` CASCADE
;

DROP TABLE IF EXISTS `sys_function` CASCADE
;

DROP TABLE IF EXISTS `sys_role` CASCADE
;

DROP TABLE IF EXISTS `sys_role_fun` CASCADE
;

DROP TABLE IF EXISTS `sys_user` CASCADE
;

DROP TABLE IF EXISTS `sys_user_role` CASCADE
;

DROP TABLE IF EXISTS `sys_wechat_info` CASCADE
;

DROP TABLE IF EXISTS `traffic_violation` CASCADE
;

DROP TABLE IF EXISTS `vehicle_info` CASCADE
;

DROP TABLE IF EXISTS `vehicle_maintain_detail` CASCADE
;

DROP TABLE IF EXISTS `vehicle_maintain_info` CASCADE
;

/* Create Tables */

CREATE TABLE `driving_license`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(25),
	`gender` VARCHAR(10) COMMENT '性别',
	`nationality` VARCHAR(100) COMMENT '国籍',
	`address` VARCHAR(200),
	`birthday` DATE COMMENT '生日',
	`issue_date` DATE COMMENT '初次领证日期',
	`valid_from` DATE COMMENT '有效起始日期',
	`valid_for` INTEGER COMMENT '有效期限:年',
	`file_no` VARCHAR(100) COMMENT '驾驶证副页：档案编号',
	`record` VARCHAR(200) COMMENT '记录',
	`user_id` INTEGER COMMENT '客户ID',
	CONSTRAINT `PK_driving_license` PRIMARY KEY (`id`)
) COMMENT='驾驶证'
;

CREATE TABLE `garage_biz`
(
	`id` MEDIUMINT NOT NULL AUTO_INCREMENT ,
	`category` INTEGER COMMENT '类别,1:维修 2:保养 3:洗车 4:其它',
	`code` VARCHAR(50) COMMENT '业务编码',
	`name` VARCHAR(50) COMMENT '业务名称',
	`price` FLOAT(0) COMMENT '价格',
	`garage_id` INTEGER COMMENT '所属车行',
	`desc` VARCHAR(200) COMMENT '业务详细描述',
	CONSTRAINT `PK_garage_biz` PRIMARY KEY (`id`)
) COMMENT='业务列表'
;

CREATE TABLE `garage_biz_book`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`type` VARCHAR(50) COMMENT '预约业务类别',
	`type_name` VARCHAR(50) COMMENT '业务类别名称',
	`state` INTEGER COMMENT '预约状态：0:预约 2:已接待 3:取消',
	`user_id` INTEGER COMMENT '预约用户',
	`book_time` TIMESTAMP COMMENT '预约时间',
	`garage_id` INTEGER COMMENT '预约车行',
	`plate_number` VARCHAR(50) COMMENT '车辆ID',
	`create_time` TIMESTAMP COMMENT '记录生成时间',
	`memo` TEXT COMMENT '备注说明',
	CONSTRAINT `PK_garage_biz_book` PRIMARY KEY (`id`)
)
;

CREATE TABLE `garage_card_info`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`type` CHAR(2),
	`value` FLOAT(0),
	`create_time` DATETIME COMMENT '开卡日期',
	`expired_date` VARCHAR(50) COMMENT '到期日',
	`user_id` INTEGER COMMENT '所属客户',
	`garage_id` INTEGER COMMENT '开卡车行',
	`create_user` INTEGER COMMENT '开卡员工',
	`desc` VARCHAR(50),
	CONSTRAINT `PK_garage_card_info` PRIMARY KEY (`id`)
)
;

CREATE TABLE `garage_discount_coupon`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`uniqu_code` VARCHAR(200) NOT NULL COMMENT '唯一编码，可识别此券',
	`type` CHAR(10) COMMENT '0:折扣 1:抵金券',
	`value` FLOAT(0) COMMENT '折扣、抵扣值',
	`is_active` CHAR(0) COMMENT '状态：0:未激活 1:已激活',
	`activte_date` DATE,
	`duration` VARCHAR(50),
	`expired_date` DATE COMMENT '到期日，若有活动期限，则与活动的结束日期一致；若按激活后按可使用时长算则active_date+ duration计算；若两者都有，则取两种计算结果较短者;若业务类型定义了不限定使用期限，则此列不生效',
	`valid_date_begin` DATE COMMENT '优惠券可使用的开始日期',
	`valid_date_end` DATE COMMENT '优惠券可使用的结束日期',
	`user_id` INTEGER COMMENT '关联的用户',
	`garage_id` INTEGER COMMENT '所属车行',
	`desc` VARCHAR(200) COMMENT '描述',
	CONSTRAINT `PK_garage_card_coupon` PRIMARY KEY (`id`)
)
;

CREATE TABLE `garage_info`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(100) COMMENT '车行名称',
	`establish_date` DATE COMMENT '成立日期',
	`address` VARCHAR(200) COMMENT '车行地址',
	`phone_no` VARCHAR(50) COMMENT '联系电话',
	`carwash_rack` INTEGER COMMENT '洗车位',
	`car_repairing_rack` INTEGER COMMENT '修车位个数',
	`painting_rack` INTEGER COMMENT '烤漆位个数',
	`lbs` TEXT COMMENT '定位地址',
	`desc` TEXT COMMENT '描述',
	CONSTRAINT `PK_garage_inf` PRIMARY KEY (`id`)
)
;

CREATE TABLE `sys_dict`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`category_code` VARCHAR(50) COMMENT '数据字典类别代码',
	`code` VARCHAR(50) COMMENT '数据字典编码',
	`name` VARCHAR(50) COMMENT '名称',
	`desc` TEXT COMMENT '备注',
	CONSTRAINT `PK_sys_dict` PRIMARY KEY (`id`)
) COMMENT='数据字典'
;

CREATE TABLE `sys_function`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(100),
	`req_uri` VARCHAR(250),
	`desc` TEXT,
	`parent_id` INTEGER,
	CONSTRAINT `PK_sys_function` PRIMARY KEY (`id`)
)
;

CREATE TABLE `sys_role`
(
	`role_code` VARCHAR(25) NOT NULL COMMENT '唯一角色代码',
	`name` VARCHAR(250),
	`role_desc` TEXT,
	CONSTRAINT `PK_sys_role` PRIMARY KEY (`role_code`)
)
;

CREATE TABLE `sys_role_fun`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`role_code` VARCHAR(25),
	`fun_id` INTEGER
)
;

CREATE TABLE `sys_user`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`login_name` VARCHAR(50),
	`password` VARCHAR(250),
	`type` INTEGER COMMENT '用户类型:0-平台用户 1-车行用户 2-车主',
	`state` INTEGER COMMENT '用户状态 0-正常 1-已删除',
	`garage_id` INTEGER COMMENT '所属车行，系统管理员则为空',
	`create_time` TIMESTAMP,
	`last_modify_time` TIMESTAMP,
	`last_modify_user` INTEGER,
	`name` VARCHAR(50),
	`gender` VARCHAR(1) COMMENT '性别 0-女 1-男',
	`phone_number` VARCHAR(50),
	`birthday` DATE,
	`head_thubnail` VARCHAR(100) COMMENT '用户头像地址',
	CONSTRAINT `PK_sys_user` PRIMARY KEY (`id`)
)
;

CREATE TABLE `sys_user_role`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`user_id` INTEGER,
	`role_code` VARCHAR(25),
	CONSTRAINT `PK_sys_user_role` PRIMARY KEY (`id`)
)
;

CREATE TABLE `sys_wechat_info`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`user_id` INTEGER,
	`wechat_account` VARCHAR(100),
	`nick_name` VARCHAR(50),
	`head_portrait` BLOB,
	`open_id` VARCHAR(50),
	`unique_id` VARCHAR(50),
	CONSTRAINT `PK_sys_wechat_info` PRIMARY KEY (`id`)
)
;

CREATE TABLE `traffic_violation`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`state` VARCHAR(10) COMMENT '状态:0-待处理 1-已处理',
	`violation_desc` TEXT COMMENT '违章信息详情',
	`create_time` TIME COMMENT '记录生成时间',
	`user_id` INTEGER,
	`vehicle_id` INTEGER,
	CONSTRAINT `PK_traffic_violation` PRIMARY KEY (`id`)
) COMMENT='违章列表'
;

CREATE TABLE `vehicle_maintain_detail`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`maintain_id` INTEGER COMMENT '维护ID',
	`biz_code` VARCHAR(50) COMMENT '业务类型编码,取自车行业务表',
	`biz_name` VARCHAR(50) COMMENT '业务名称',
	`price` FLOAT(0) COMMENT '业务原价',
	`pay_price` FLOAT(0) COMMENT '客户应付款',
	`state` VARCHAR(2) COMMENT '服务状态,0:排队中 1:等待中 2:服务中 3:服务完成 ',
	`is_pay` CHAR(1) COMMENT '付款状态, 0:未付款 1:已付款',
	`start_time` TIMESTAMP COMMENT '业务开始时间',
	`end_time` TIMESTAMP COMMENT '业务完成时间',
	`desc` VARCHAR(200) COMMENT '明细说明',
	CONSTRAINT `PK_vehicle_maintain_detail` PRIMARY KEY (`id`)
)
;

CREATE TABLE `vehicle_maintain_info`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`state` VARCHAR(2) COMMENT '服务状态,0:排队中 1:服务中 2:服务完成 3:已交车',
	`category` VARCHAR(50) COMMENT '服务类别,10001:维修 10002:保养 10003:洗车 10004:其它',
	`category_name` VARCHAR(100) COMMENT '业务类别名称',
	`vehicle_id` INTEGER COMMENT '车辆信息ID',
	`mileage` INTEGER COMMENT '此次维修保养里程',
	`battery_service_year` INTEGER COMMENT '截止当前电池使用年限',
	`tire_service_year` INTEGER COMMENT '轮胎已使用年限',
	`user_id` INTEGER COMMENT '用户信息ID，交车保养维修用户，不一定是车主',
	`garage_id` INTEGER COMMENT '业务办理车行ID',
	`price` FLOAT(0) COMMENT '实际价格',
	`pay_price` FLOAT(0) COMMENT '客户应付款',
	`is_pay` CHAR(1) COMMENT '是否付款, 0:未付款 1:已付款',
	`memo` TEXT COMMENT '备注',
	`receive_time` TIMESTAMP COMMENT '车辆到店时间',
	`start_time` TIMESTAMP COMMENT '业务开始时间',
	`end_time` TIMESTAMP COMMENT '业务完成时间',
	CONSTRAINT `PK_vehicle_maintain_info` PRIMARY KEY (`id`)
)
;

CREATE TABLE `traffic_violation`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`state` VARCHAR(10) COMMENT '状态:0-待处理 1-已处理',
	`violation_desc` TEXT COMMENT '违章信息详情',
	`create_time` TIME COMMENT '记录生成时间',
	`user_id` INTEGER,
	`vehicle_id` INTEGER,
	CONSTRAINT `PK_traffic_violation` PRIMARY KEY (`id`)
) COMMENT='违章列表'
;

CREATE TABLE `vehicle_info`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`last_garage_date` DATE COMMENT '上次到店日期',
	`violation_count` INTEGER COMMENT '未处理违章数目',
	`total_consumption` FLOAT(0) COMMENT '消费总额',
	`total_debt` FLOAT(0) COMMENT '总欠账 :元',
	`last_mileage` INTEGER COMMENT '上次登记里程',
	`insurance_date` DATE COMMENT '保险到期日',
	`last_maintain_date` DATE COMMENT '上次保养日期',
	`last_maintain_mileage` INTEGER COMMENT '上次保养登记里程',
	`tire_service_year` FLOAT(0) COMMENT '轮胎使用时长',
	`battery_service_year` FLOAT(0) COMMENT '电池使用时长',
	`last_beauty_date` DATE COMMENT '上次美容日期',
	`user_id` INTEGER COMMENT '车主用户ID',
	`plate_number` VARCHAR(50) COMMENT '车牌号码',
	`vehicle_type` VARCHAR(50) COMMENT '车辆类型',
	`owner` VARCHAR(50) COMMENT '车辆所有人',
	`address` VARCHAR(200) COMMENT '住址',
	`use_character` VARCHAR(50) COMMENT '使用性质：家庭自用汽、非营业客车、营业客车、非营业货车、营业货车、特种车、摩托车、拖拉机、挂车',
	`model` VARCHAR(50) COMMENT '品牌型号',
	`vin` VARCHAR(50) COMMENT '车辆识别代号',
	`engine_no` VARCHAR(50) COMMENT '发动机号码',
	`register_date` DATE COMMENT '注册日期',
	`issue_date` DATE COMMENT '发证日期',
	`file_no` VARCHAR(50) COMMENT '行驶证背面：档案编号',
	`approved_passengers_apacity` INTEGER COMMENT '核载人数',
	`gross_mass` INTEGER COMMENT '总质量:KG,',
	`unloaden_mass` INTEGER COMMENT '整备质量:KG',
	`approved_load` INTEGER COMMENT '核定载质量:KG',
	`overall_dimension` VARCHAR(50) COMMENT '外廓尺寸:4521x1788x1492',
	`traction_mass` INTEGER COMMENT '准牵引质量:kg',
	`comment` VARCHAR(200) COMMENT '备注',
	`inspection_record` VARCHAR(200),
	CONSTRAINT `PK_vehicle_info` PRIMARY KEY (`id`)
)
;

CREATE TABLE `vehicle_maintain_detail`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`maintain_id` INTEGER COMMENT '维护ID',
	`biz_code` VARCHAR(50) COMMENT '业务类型编码,取自车行业务表',
	`biz_name` VARCHAR(50) COMMENT '业务名称',
	`price` FLOAT(0) COMMENT '业务原价',
	`pay_price` FLOAT(0) COMMENT '客户应付款',
	`state` VARCHAR(2) COMMENT '服务状态,0:排队中 1:等待中 2:服务中 3:服务完成 ',
	`is_pay` CHAR(1) COMMENT '付款状态, 0:未付款 1:已付款',
	`start_time` TIMESTAMP COMMENT '业务开始时间',
	`end_time` TIMESTAMP COMMENT '业务完成时间',
	`desc` VARCHAR(200) COMMENT '明细说明',
	CONSTRAINT `PK_vehicle_maintain_detail` PRIMARY KEY (`id`)
)
;

CREATE TABLE `vehicle_maintain_info`
(
	`id` INTEGER NOT NULL AUTO_INCREMENT ,
	`state` VARCHAR(2) COMMENT '服务状态,0:排队中 1:服务中 2:服务完成 3:已交车',
	`category` VARCHAR(2) COMMENT '服务类别,1:维修 2:保养 3:洗车 4:其它',
	`vehicle_id` INTEGER COMMENT '车辆信息ID',
	`mileage` INTEGER COMMENT '此次维修保养里程',
	`battery_service_year` INTEGER COMMENT '截止当前电池使用年限',
	`tire_service_year` INTEGER COMMENT '轮胎已使用年限',
	`user_id` INTEGER COMMENT '用户信息ID，交车保养维修用户，不一定是车主',
	`garage_id` INTEGER COMMENT '业务办理车行ID',
	`price` FLOAT(0) COMMENT '实际价格',
	`pay_price` FLOAT(0) COMMENT '客户应付款',
	`is_pay` CHAR(1) COMMENT '是否付款, 0:未付款 1:已付款',
	`memo` TEXT COMMENT '备注',
	`receive_time` TIMESTAMP COMMENT '车辆到店时间',
	`start_time` TIMESTAMP COMMENT '业务开始时间',
	`end_time` TIMESTAMP COMMENT '业务完成时间',
	CONSTRAINT `PK_vehicle_maintain_info` PRIMARY KEY (`id`)
)
;

INSERT INTO `sys_function` VALUES ('1', '用户管理',null,'system init',null);
INSERT INTO `sys_function` VALUES ('2', '新增用户','/sys/user/add','system init','1');
INSERT INTO `sys_function` VALUES ('3', '删除用户','/sys/user/del','system init','1');
INSERT INTO `sys_function` VALUES ('4', '更新用户','/sys/user/upt','system init','1');
INSERT INTO `sys_function` VALUES ('5', '更新用户','/sys/user/qry','system init','1');

INSERT INTO `GARAGE_INFO` VALUES ('1','酷鼎车行','2010-1-1','广东省惠州市惠城区小金口','0752-36368766','3','1','1',null,'系统测试');

INSERT INTO `sys_role` VALUES ('SYS_ADM','系统管理员','平台系统管理员');
INSERT INTO `sys_role` VALUES ('GAR_ADM','车行管理员','车行系统管理员');
INSERT INTO `sys_role` VALUES ('CUST','客户','车主');
INSERT INTO `sys_role` VALUES ('GAR_RCP','车行接待','车行接待员');

INSERT INTO `sys_role_fun` VALUES ('1','GAR_ADM','1');
INSERT INTO `sys_role_fun` VALUES ('2','GAR_ADM','2');
INSERT INTO `sys_role_fun` VALUES ('3','GAR_ADM','3');
INSERT INTO `sys_role_fun` VALUES ('4','GAR_ADM','4');
INSERT INTO `sys_role_fun` VALUES ('5','GAR_ADM','5');

INSERT INTO `SYS_USER` VALUES ('1','admin','E10ADC3949BA59ABBE56E057F20F883E','0','1',null,now(),now(),null,'邱管理','1','13588888888','1990-1-1',null);
INSERT INTO `SYS_USER` VALUES ('2','test','E10ADC3949BA59ABBE56E057F20F883E','1','1','1',now(),now(),null,'钟测试','1','13588888888','1990-1-1',null);
INSERT INTO `SYS_USER` VALUES ('3','customer1','E10ADC3949BA59ABBE56E057F20F883E','2','1','1',now(),now(),null,'李土豪','1','130666666','1970-1-1',null);
INSERT INTO `SYS_USER` VALUES ('4','customer2','E10ADC3949BA59ABBE56E057F20F883E','2','1','1',now(),now(),null,'王大炮','1','1391234555','1986-1-1',null);

INSERT INTO `sys_user_role` VALUES ('1','1','SYS_ADM');
INSERT INTO `sys_user_role` VALUES ('2','2','GAR_ADM');
INSERT INTO `sys_user_role` VALUES ('3','3','CUST');
INSERT INTO `sys_user_role` VALUES ('4','4','CUST');

INSERT INTO `VEHICLE_INFO` VALUES ('1','2017-12-25','0','25000','0','25000','2017-11-18','2017-12-25','12000','2.5','2.5','2018-1-10','3','粤L88888','轿车','李土豪','广东省惠州市回城区金山湖','家庭自用汽车','2016款 AMG S 63 L 4MATIC+','VIN0234233333','EN238740830344','2017-1-1','2017-1-1',null,'5',null,null,null,null,null,null,null);
INSERT INTO `VEHICLE_INFO` VALUES ('2','2017-12-25','0','25000','0','25000','2017-11-18','2017-12-25','12000','2.5','2.5','2018-1-10','4','粤L66666','轿车','王大炮','广东省惠州市回城区长湖苑','家庭自用汽车','2016款 AMG S 63 L 4MATIC+','VIN0234255555','EN238740830123','2017-1-1','2017-1-1',null,'5',null,null,null,null,null,null,null);
INSERT INTO `VEHICLE_INFO` VALUES ('3','2018-03-01','0','25000','0','25000','2017-11-18','2018-03-01','12000','2.5','2.5','2018-1-10','3','粤L55555','轿车','李土豪','广东省惠州市回城区金山湖','家庭自用汽车','2016款 AMG S 63 L 4MATIC+','VIN0234255544','EN238740830155','2017-1-1','2017-1-1',null,'5',null,null,null,null,null,null,null);

INSERT INTO `sys_dict` VALUES ('1', 'biz', '10001', '美容', '汽车美容');
INSERT INTO `sys_dict` VALUES ('2', 'biz', '10002', '保养', '汽车保养');
INSERT INTO `sys_dict` VALUES ('3', 'biz', '10003', '维修', '汽车维修');
INSERT INTO `sys_dict` VALUES ('4', 'biz', '10004', '其他', '汽车服务');

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE `driving_license` 
 ADD INDEX `IXFK_driving_license_sys_user` (`user_id` ASC)
;

ALTER TABLE `garage_biz` 
 ADD INDEX `IXFK_garage_biz_garage_info` (`garage_id` ASC)
;

ALTER TABLE `garage_biz_book` 
 ADD INDEX `IXFK_garage_biz_book_garage_info` (`garage_id` ASC)
;

ALTER TABLE `garage_biz_book` 
 ADD INDEX `IXFK_garage_biz_book_sys_user` (`user_id` ASC)
;

ALTER TABLE `garage_biz_book` 
 ADD INDEX `IXFK_garage_biz_book_vehicle_info` (`plate_number` ASC)
;

ALTER TABLE `garage_card_info` 
 ADD INDEX `IXFK_garage_card_info_garage_info` (`garage_id` ASC)
;

ALTER TABLE `garage_card_info` 
 ADD INDEX `IXFK_garage_card_info_sys_user` (`user_id` ASC)
;

ALTER TABLE `garage_discount_coupon` 
 ADD INDEX `IXFK_garage_card_coupon_garage_staff_info` (`garage_id` ASC)
;

ALTER TABLE `garage_discount_coupon` 
 ADD INDEX `IXFK_garage_card_coupon_sys_user` (`user_id` ASC)
;

ALTER TABLE `sys_role_fun` 
 ADD INDEX `IXFK_sys_role_fun_sys_function` (`fun_id` ASC)
;

ALTER TABLE `sys_user` 
 ADD INDEX `IXFK_sys_user_garage_info` (`garage_id` ASC)
;

ALTER TABLE `sys_user_role` 
 ADD INDEX `IXFK_sys_user_role_sys_role` (`role_code` ASC)
;

ALTER TABLE `sys_user_role` 
 ADD INDEX `IXFK_sys_user_role_sys_role_02` (`role_code` ASC)
;

ALTER TABLE `sys_user_role` 
 ADD INDEX `IXFK_sys_user_role_sys_user` (`user_id` ASC)
;

ALTER TABLE `sys_wechat_info` 
 ADD INDEX `IXFK_sys_wechat_info_sys_user` (`user_id` ASC)
;

ALTER TABLE `traffic_violation` 
 ADD INDEX `IXFK_traffic_violation_sys_user` (`user_id` ASC)
;

ALTER TABLE `traffic_violation` 
 ADD INDEX `IXFK_traffic_violation_vehicle_info` (`vehicle_id` ASC)
;

ALTER TABLE `vehicle_info` 
 ADD INDEX `IXFK_vehicle_info_sys_user` (`user_id` ASC)
;

ALTER TABLE `vehicle_maintain_detail` 
 ADD INDEX `IXFK_vehicle_maintain_detail_vehicle_maintain_info` (`maintain_id` ASC)
;

ALTER TABLE `vehicle_maintain_info` 
 ADD INDEX `IXFK_vehicle_maintain_info_garage_info` (`garage_id` ASC)
;

ALTER TABLE `vehicle_maintain_info` 
 ADD INDEX `IXFK_vehicle_maintain_info_sys_user` (`user_id` ASC)
;

ALTER TABLE `vehicle_maintain_info` 
 ADD INDEX `IXFK_vehicle_maintain_info_vehicle_info` (`vehicle_id` ASC)
;
