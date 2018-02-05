<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

.kd-add-cars {
	width: 2.4rem;
	margin: 0.1rem auto;
}

p {
	height: 0.18rem;
	line-height: 0.18rem;
	font-size: 0.14rem;
	text-align: center;
}

a {
	text-decoration: none;
	color: #101010;
}

.circle-link {
	display: block;
	margin: 0 auto;
	width: 1.8rem;
	height: 1.8rem;
	border: 1px solid gray;
	border-radius: 1.8rem;
	line-height: 1.8rem;
	font-size: 0.14rem;
	text-align: center;
	margin-top:0.05rem;
}
</style>
<title>车辆注册</title>
</head>
<body>
	
	<div class="kd-add-cars">
		<p>您的车辆信息为空，请录入，谢谢</p>
		<a href="#" class="circle-link">拍摄行驶证，自动智能识别</a>
		<a href="${path}/garage/vehicle/manualAdd" class="circle-link">人工输入</a>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>