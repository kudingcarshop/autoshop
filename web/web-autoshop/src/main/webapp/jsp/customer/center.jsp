<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/center.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- head -->
		<div class="kd-cust-head">
			<img src="${path}/customer/headImg"></img>
			<div>
				<span>${user.name }</span> <span>${user.phoneNumber}</span> 
			</div>
			<i class="fa fa-angle-right fa-2x"></i>
		</div>
		<!-- consumption -->
		<div class="kd-section">
			<div class="kd-items">
				<i class="fa fa-vimeo fa-2x"></i>  <span>会员积分</span> <span><fmt:formatNumber value="${user.integral}" pattern="###,###,###"></fmt:formatNumber>积分</span>
			</div>
			<div class="kd-items" onclick="javascript:window.location.href='${path}/customer/cards';">
				<i class="fa fa-credit-card fa-2x"></i> <i class="fa fa-angle-right fa-2x"></i> <span>我的卡包</span>
			</div>
			<div class="kd-items" onclick="javascript:window.location.href='${path}/customer/coupons';">
				<i class="fa fa-money fa-2x"></i> <i class="fa fa-angle-right fa-2x"></i> <span>我的优惠券</span>
			</div>
			<div class="kd-items" onclick="javascript:window.location.href='${path}/customer/consumptions';">
				<i class="fa fa-bar-chart fa-2x"></i> <i class="fa fa-angle-right fa-2x"></i> <span>消费记录</span>
			</div>
		</div>
		<!-- cars -->
		<div class="kd-section">
			<div class="kd-items" onclick="javascript:window.location.href='${path}/customer/cars';">
				<i class="fa fa-car fa-2x"></i> <i class="fa fa-angle-right fa-2x"></i>
				<span>我的车辆</span> <span>${vehNum}辆车</span>
			</div>
			<div class="kd-items" onclick="javascript:window.location.href='${path}/customer/backlog';">
				<i class="fa fa-briefcase fa-2x"></i> <i class="fa fa-angle-right fa-2x"></i>
				<span>代办事项</span> <span><fmt:formatNumber value="${backlogCount}" pattern="###,###,###"></fmt:formatNumber>项</span>
			</div>
			<div class="kd-items" onclick="javascript:window.location.href='${path}/customer/serving/state';">
				<i class="fa fa-step-forward fa-2x"></i> <i class="fa fa-angle-right fa-2x"></i>
				<span>进度查询</span> <span>${servingCount}项</span>
			</div>
		</div>
		
		
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script type="text/javascript">
	$(function(){
		$('.kd-cust-head').on('click',function(){
			$(window).attr('location','${path}/customer/edit');
		});
	});
	</script>
</body>
</html>
