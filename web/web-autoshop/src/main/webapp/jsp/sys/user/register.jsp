<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path}/js/bootstrap/3.3.7/css/bootstrap.min.css"/>  
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>用户注册</title>
</head>
<body>
</body>
<form action="${path}/sys/user/register" method="post" class="form-horizontal">
	<div class="form-group">
		<label class="col-sm-2 col-sm-offset-3 control-label">用户名</label> 
		<input class="col-sm-4" type="text" name="loginName" />
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 col-sm-offset-3 control-label">密码</label> 
		<input class="col-sm-4" type="password" name="password" /> 
	</div>

	<div class="form-group">
		<label class="col-sm-2 col-sm-offset-3 control-label">确认密码</label> 
		<input class="col-sm-4" type="password" name="password_confirm" /> 
	</div>
	<input type="submit" value="提交"/>
</form>
</html>