<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="jsp/path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css"/>  
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>用户登录</title>
</head>
<body>
	<c:if test="${not empty errors }">
		<div id="error" class="alert alert-danger">${errors}</div>
	</c:if>
	<div style="margin:0 auto;padding:0.12rem;width:3.2rem;height:3rem;">
		<form id="loginForm" method="post" action="${path}/pub/login">
		<input type="hidden" name="reqUri" value="${req_uri}">
			<div class="form-group">
				<label for="loginName">用户名</label> 
				<input class="form-control" type="text" name="loginName" />
			</div>
			<div class="form-group">
				<label for="password">密码</label> 
				<input class="form-control" type="password" name="password" />
			</div>
			<input type="submit" class="btn btn-primary" style="width:100%" value="登录"/>
		</form>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>