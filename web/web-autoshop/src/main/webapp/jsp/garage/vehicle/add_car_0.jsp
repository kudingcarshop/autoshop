<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<style type="text/css">
*{padding:0;margin:0;}
p{
	height:1.8rem;
	line-height:1.8rem;
	text-align:center;
}
.kd-add-cars{
    position:absolute;
	width:24rem;
	height:37.8rem;
	top:50%;
	left:50%;
	margin-top:-18.9rem;
	margin-left:-12rem;
}
a{text-decoration:none;}
.circle-link{
	display:block;
	margin:0 auto;
	width:18rem;
	height:18rem;
	border:1px solid gray;
	border-radius:18rem;
	line-height:18rem;
	text-align:center;
}
</style>
<title>车辆注册</title>
</head>
<body>
	
	<div class="kd-add-cars">
		<p>您的车辆信息为空，请录入，谢谢</p>
		<a href="#" class="circle-link">拍摄行驶证，自动智能识别</a>
		<a href="${path}/garage/vehicle/manualAdd" class="circle-link" style="margin-top:0.5rem;">人工输入</a>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>