<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/serving_state.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 车辆服务详情 -->
		<div class="kd-state">
			<div class="kd-state-hd">
				<span>粤LSD712</span> <span class="kd-working">进行中</span>
			</div>
			<div class="kd-state-bd">
				<div class="kd-state-bd-title">
					<span>维修</span> <span class="kd-finish">已完成</span>
				</div>
				<div class="kd-state-bd-detail">
					<p>详情</p>
					<div>
						<span>钣金</span>
						<span>水箱漏水</span>
					</div>
				</div>
			</div>
			<div class="kd-state-bd">
				<div class="kd-state-bd-title">
					<span>保养</span> <span class="kd-working">进行中</span>
				</div>
				<div class="kd-state-bd-detail">
					<p>详情</p>
					<div>
						<span>换机油</span>
					</div>
				</div>
			</div>
			<div class="kd-state-ft">
				<a>催一下</a>
			</div>
		</div>
		
		<div class="kd-state">
			<div class="kd-state-hd">
				<span>粤L88888</span> <span class="kd-finish">已完成</span>
			</div>
			<div class="kd-state-bd">
				<div class="kd-state-bd-title">
					<span>维修</span> <span class="kd-finish">已完成</span>
				</div>
				<div class="kd-state-bd-detail">
					<p>详情</p>
					<div>
						<span>钣金</span>
						<span>水箱漏水</span>
					</div>
				</div>
			</div>
			<div class="kd-state-bd">
				<div class="kd-state-bd-title">
					<span>保养</span> <span class="kd-finish">已完成</span>
				</div>
				<div class="kd-state-bd-detail">
					<p>详情</p>
					<div>
						<span>换机油</span>
					</div>
				</div>
			</div>
			<div class="kd-state-ft">
				<a>结算</a>
			</div>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>