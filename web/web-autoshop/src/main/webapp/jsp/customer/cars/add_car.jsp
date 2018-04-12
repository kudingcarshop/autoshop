<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/add_car.css"/>
</head>
<body>
	<div class="kd-page">
		<p class="kd-title">添加行驶证</p>
		<div class="kd-big-btn">
		<a>
			<i class="fa fa-car fa-5x"></i>
			<p>拍照识别</p>
		</a>
		</div>
		<div class="kd-big-btn">
			<a>
				<i class="fa fa-edit fa-5x" style="margin-left: 0.07rem;"></i>
				<p>手动输入</p>
			</a>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>