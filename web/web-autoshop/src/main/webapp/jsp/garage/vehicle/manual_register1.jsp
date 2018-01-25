<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<title>车辆注册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
</head>
<body>

	<div>
		<span>使用人信息</span>
	</div>
	<form>
	<div>
		<div>
			<label>姓名*:</label> <span>男</span><span>女</span> 
			<input type="hidden" name="gender" />
		</div>
	</div>
	<div>
		<label>联系电话*:</label> <input type="text" name="phoneNumber"/>
	</div>
	
	<div>
		<span>车况信息</span>
	</div>
	<p>为了给您提供更精准的保养建议，请您完善以下信息：</p>
	<div>
		<label>当前公里数:</label> 
		<input type="text" name="mileage" />
		<span>公里</span>
	</div>
	
	<div>
		<label>上次保养公里数:</label> 
		<input type="text" name="mileage" />
		<span>公里</span>
	</div>
	
	<div>
		<label>上次保养日期：</label> 
		<input type="text" name="lastMaintainDate" />
		<span>公里</span>
	</div>
	
	<div>
		<label>电池已使用时长：</label> 
		<input type="text" name="batteryServiceYear" />
		<span>年</span>
	</div>
	
	<div>
		<label>轮胎已使用时长：</label> 
		<input type="text" name="batteryServiceYear" />
		<span>年</span>
	</div>
	
	<div>
		<button type="button">下一部</button>
	</div>
	</form>
</body>
</html>