<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/backlog/annual_vertification_insurance.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-annual">
			<div class="kd-hd">
				<input type="checkbox"> <span>粤L52017</span>
			</div>

			<div class="kd-bd">
				<input type="checkbox" class="kd-bd-vertification"/>
				<div>
					<p>年审</p>
					<p>车龄：8年</p>
					<p>登记日期：2010.01.01</p>
					<p>到期日期：2018.01.01</p>
				</div>
				<div class="kd-bd-fee">
					<span class="pull-right">50.00</span><label class="pull-right">待办手续费：</label>
				</div>
			</div>
		</div>

		<div class="kd-annual">
			<div class="kd-hd">
				<input type="checkbox"> <span>粤L52017</span>
			</div>

			<div class="kd-bd">
				<input type="checkbox" class="kd-bd-insurance"/>
				<div>
					<p>保险</p>
					<p>车龄：8年</p>
					<p>到期日期：2018.01.01</p>
				</div>
				<div class="kd-bd-fee">
					<span class="pull-right">2000.00</span><label class="pull-right">待办手续费：</label>
				</div>
			</div>
		</div>

		<div class="kd-ft">
			<input type="checkbox" /> 
			<a>结算<label>(2)</label></a>
			<span>2050.00</span>
			<span>合计：</span> 
			 <span>全选</span>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>