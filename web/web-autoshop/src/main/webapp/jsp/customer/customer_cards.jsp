<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/customer_cards.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-item">
			<div class="kd-item-hd">
				<i class="fa fa-credit-card fa-2x"></i><span>卡</span>
				<i class="fa fa-angle-right fa-2x"></i>
				<span>查看全部(1)</span>
			</div>

			<div class="kd-item-bd">
				<div class="kd-item-bd-left">
					<span>洗车卡</span> <span style="margin-top: 0.34rem;"> 当前剩余8次 </span>
				</div>
				<span class="kd-desc">办卡日期:2018-01-01</span>
				
			</div>
		</div>
		
		<div class="kd-item">
			<div class="kd-item-hd">
				<i class="fa fa-money fa-2x"></i><span>优惠券</span>
				<i class="fa fa-angle-right fa-2x"></i>
				<span>查看全部(2)</span>
			</div>

			<div class="kd-item-bd">
				<div>
					<span>通用优惠券</span> <span> 满1000减50</span>
				</div>
				<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
			</div>

			<div class="kd-item-bd">
				<div>
					<span>钣金优惠券</span> <span> 满1000减50</span>
				</div>
				<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
			</div>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>