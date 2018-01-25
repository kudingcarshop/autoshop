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
		<span>行驶证信息</span>
	</div>
	<form>
		<input type="hidden" name="vehicleId" value="${vehicle.id}"/>
	<div>
		<button type="button">确定注册</button>
	</div>
	</form>
</body>
</html>