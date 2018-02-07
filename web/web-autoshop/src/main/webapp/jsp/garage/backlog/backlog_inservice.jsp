<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/backlog_inservice.css"/>
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
	<div class="kd-page">
		<ul class="kd-tab">
			<li class="kd-active"><a href="#">全部(10)</a></li>
			<li><a href="#">排队中(3)</a></li>
			<li><a href="#">服务中(5)</a></li>
			<li><a href="#">待付款(2)</a></li>
		</ul>
		<div class="kd-bd">
			<p class="kd-seq">1</p>
			<div class="kd-bd-text">
				<p>粤LQW306</p>
				<p>汽修</p>
				<p>2017-12-28 11:00:00</p>
			</div>
			<select name="status">
				<option value="0" selected="selected">排队中</option>
				<option value="1">服务中</option>
				<option value="2">待付款</option>
			</select>
		</div>
			<div class="kd-bd">
			<p class="kd-seq">2</p>
			<div class="kd-bd-text">
				<p>粤LQW306</p>
				<p>汽修</p>
				<p>2017-12-28 11:00:00</p>
			</div>
			<select name="status">
				<option value="0" >排队中</option>
				<option value="1" selected="selected">服务中</option>
				<option value="2">待付款</option>
			</select>
		</div>
				<div class="kd-bd">
			<p class="kd-seq">3</p>
			<div class="kd-bd-text">
				<p>粤LQW306</p>
				<p>汽修</p>
				<p>2017-12-28 11:00:00</p>
			</div>
			<select name="status">
				<option value="0">排队中</option>
				<option value="1">服务中</option>
				<option value="2" selected="selected">待付款</option>
			</select>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>