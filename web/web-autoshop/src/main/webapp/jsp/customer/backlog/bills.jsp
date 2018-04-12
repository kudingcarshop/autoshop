<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/backlog/bills.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-bills">
			<div class="kd-bills-hd">
				<input type="checkbox"> <span>粤L52017</span>
			</div>

			<div class="kd-bills-bd">
				<input type="checkbox" />
				<div>
					<p>保养</p>
					<p>机油，机油格，工时费</p>
				</div>
				<p>600.00</p>
			</div>

			<div class="kd-bills-bd">
				<input type="checkbox" />
				<div>
					<p>美容</p>
					<p>抛光，打蜡</p>
				</div>
				<p>600.00</p>
			</div>
		</div>
		
		<div class="kd-bills">
			<div class="kd-bills-hd">
				<input type="checkbox"> <span>粤L5917</span>
			</div>

			<div class="kd-bills-bd">
				<input type="checkbox" />
				<div>
					<p>洗车</p>
					<p>普通洗车</p>
				</div>
				<p>30.00</p>
			</div>
		</div>

		<div class="kd-bills-ft">
			<input type="checkbox" /> 
			<a>结算<label>(2)</label></a>
			<span>630.00</span>
			<span>合计：</span> 
			 <span>全选</span>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>