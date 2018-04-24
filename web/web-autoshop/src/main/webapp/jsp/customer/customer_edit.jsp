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
<form id="myform" action="${path}/customer/edit/save" method="post" enctype="multipart/form-data">
	<div class="kd-page">
		<!-- 背景 -->
		<div class="kd-header-bg"></div>
		<!-- 头像 -->
		<div class="kd-header-img">
			<!-- <div id="preview" style="background-image:  url('../../../img/default_head_thubnail.png');"></div> -->
			<canvas id="canvas" width="120" height="100"></canvas>
				<input name="file" type="file" class="kd-input-head" id="files" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" />
			<a>点击更换头像</a>
		</div>
		

		<div class="kd-edit-container">
		<!-- 用户名 -->
		<div class="kd-edit-itme">
		<label>用户名</label><input value="${user.name }" name="name"/>
		</div>
		<!-- 手机 -->
		<div class="kd-edit-itme">
		<label>手机</label><input value="${user.phoneNumber}" name="phoneNumber" />
		</div>
		</div>
		<a class="kd-btn-save">保存</a>
	</div>
	</form>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/kuding/common.js"></script>
	<script type="text/javascript">
		$(function() {
			var basicSize = basicRem();
			//加载头像图片
			cropImg('canvas', '${path}/customer/headImg',1.2*basicSize,basicSize);

			$('.kd-header-img>a').on('click', function() {
				console.log('a click');
				$('#files').click();
			});

			$('#files').on('change', function() {
				cropImg('canvas', getObjectURL(this.files[0]),1.2*basicSize,basicSize);
			});
			
			
			$('a.kd-btn-save').on('click',function(){
				$('#myform').submit();
			});
			
			//服务端异常信息显示
			var msg = '${msg}';
			showMessage('.kd-page', msg, '0');
		});
	</script>
</body>
</html>
