<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/center_edit.css"/>
</head>
<body>
	<div class="kd-page">
		<form action="${path}/customer/edit/submit">
			<!-- 背景 -->
			<div class="kd-header-bg"></div>
			<!-- 头像 -->
			<div class="kd-header-img">
				<div style="background-image:  url('${path}${user.headThubnail }');"></div>
				<a>点击编辑头像</a> 
				<input type="file" hidden="true" capture="camera" accept="image/*" id="filetest" name="headImage">
			</div>

			<div class="kd-edit-container">
				<!-- 用户名 -->
				<div class="kd-edit-itme">
					<label>用户名</label><input type="text" name="name" value="${user.name}" />
				</div>
				<!-- 手机 -->
				<div class="kd-edit-itme">
					<label>手机</label><input type="text" name="phoneNumber" value="${user.phoneNumber}" />
				</div>
			</div>
			<a class="kd-btn-save">保存</a>
		</form>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>