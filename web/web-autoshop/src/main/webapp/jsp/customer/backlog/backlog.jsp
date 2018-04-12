<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/backlog/backlog.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 需付款 -->
		<a href="${path}/customer/backlog/bills" class="backlog">
			<i class="fa fa-credit-card fa-2x pull-left"></i>
			<div class="pull-left">
				<span>需付款</span>
				<span class="badge">3</span>
			</div>
			<i class="fa fa-angle-right fa-2x pull-right"></i>
		</a>
		<a href="${path}/customer/backlog/violation" class="backlog">
			<i class="fa fa-exclamation-triangle fa-2x pull-left"></i>
			<div class="pull-left">
				<span>待办违章</span>
				<span class="badge">2</span>
			</div>
			<i class="fa fa-angle-right fa-2x pull-right"></i>
		</a>
		<a href="${path}/customer/backlog/vertification" class="backlog">
			<i class="fa fa-calendar-minus-o fa-2x pull-left"></i>
			<div class="pull-left">
				<span>年审与保险</span>
			</div>
			<i class="fa fa-angle-right fa-2x pull-right"></i>
		</a>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>