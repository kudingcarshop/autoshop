<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<link rel="stylesheet" href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css">
<title>车辆注册</title>
</head>
<body ontouchstart>
<div class="kd-page">
<form id="myForm" action="${path}/garage/vehicle/saveCar" method="post">
<input type="hidden" name="mileage" value="${vehicleView.mileage}"/>
<input type="hidden" name="lastMileage" value="${vehicleView.lastMileage}"/>
<input type="hidden" name="lastMaintainDate" value="${vehicleView.lastMaintainDate }"/>
<input type="hidden" name="tireServiceYear" value="${vehicleView.tireServiceYear }"/>
<input type="hidden" name="batteryServiceYear" value="${vehicleView.batteryServiceYear }"/>
<input type="hidden" name="userId" value="${vehicleView.userId }"/>
	<div class="weui-cells__title">行驶证信息</div>
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">车主：</label>
				</div>
				<div class="weui-cell_bd">
					<input class="weui-input" name="owner" type="text" placeholder="请输入车主姓名">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">车牌号码：</label>
				</div>
				<div class="weui-cell_bd">
					<input class="weui-input" name="plateNumber" type="text" placeholder="请输入车号码">
				</div>
			</div>
	
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">车辆识别码：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" name="vin" type="text" placeholder="请输入车辆识别码">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">发动机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" name="engineNumber" type="text" placeholder="请输入发动机码">
				</div>
			</div>
		</div>
</form>
			<div style="margin:0 auto; width:250px">
					<a href="${path}/garage/vehicle/goBack" class="weui-btn weui-btn_mini weui-btn_primary" style="display:inline-block;width:100px;">上一步</a>
					<a href="javascript:goSave();" class="weui-btn weui-btn_mini weui-btn_primary" style="display:inline-block;width:100px;">确认注册</a>
			</div>
</div>
<script src="${path}/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
	function goSave(){
		$('#myForm').submit();
	}
	
</script>
</body>
</html>