<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/backlog.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 待付款车辆 -->
		<a href="${path}/garage/backlog/unpay" class="backlog">
			<i class="fa fa-cc-diners-club fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>待付款车辆（${unpVehs}）</p>
				<p>共赊账&yen;<fmt:formatNumber value="${unpSum}" pattern="###,###.###"/>（元）</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>
		</a>
		<!-- 已预约车辆 -->
		<a href="${path}/garage/backlog/booking" class="backlog">
			<i class="fa fa-calendar fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>已预约车辆（${booking.total}）</p>
				<p>保养${booking.maintainSum}部、汽修${booking.repaireSum}部、洗车${booking.beautySum}部</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>
		</a>
		<!-- 服务中车辆 -->
		<a href="${path}/garage/backlog/serving" class="backlog">
			<i class="fa fa-truck fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>服务中车辆（${serving}）</p>
				<p>请及时更新状态</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>		
		</a>
		<!-- 待发货商品 -->
		<a id="goods" href="#" class="backlog">
			<i class="fa fa-shopping-cart fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>待发货商品（20）</p>
				<p>请及时处理</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>	
		</a>

	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>