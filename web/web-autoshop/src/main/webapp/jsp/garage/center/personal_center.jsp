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
		<!-- 我的客户 -->
		<a href="${path}/garage/center/customers" class="backlog">
			<i class="fa fa-users fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>我的客户</p>
				<p>共有<fmt:formatNumber value="${userSum}" pattern="###,###.###"/>个客户</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>
		</a>
		<!-- 我的商品 -->
		<a href="#" class="backlog">
			<i class="fa fa-shopping-cart fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>我的商品 </p>
				<p>250种商品在售</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>
		</a>
		<!-- 车行信息 -->
		<a href="#" class="backlog">
			<i class="fa fa-calendar fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>车行信息</p>
				<p>请及时更新信息</p>
			</div>
			<div class="icon-right kd-right">
				<li class="fa fa-angle-right fa-2x"></li>
			</div>		
		</a>
		<!-- 收入报表 -->
		<a id="goods" href="#" class="backlog">
			<i class="fa fa-bar-chart fa-2x icon kd-left"></i>
			<div class="content kd-left">
				<p>收入报表</p>
				<p>日、月、年度报表</p>
			</div>
			<li class="fa fa-angle-right fa-2x icon-right kd-right"></li>
		</a>

	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>