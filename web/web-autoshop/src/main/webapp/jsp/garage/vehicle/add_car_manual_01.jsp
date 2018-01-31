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
<form id="myForm" action="${path}/garage/vehicle/goNext" method="post">
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">使用人</label>
				</div>
				<div class="weui-cell_bd">
					<div class="weui-cell_bd weui-cell_select">
						<select id="myUser" class="weui-select" name="userId">
							<c:forEach items="${users}" var="user">
								<option value="${user.id }" >${user.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="weui-cells__title">
			<h2>车况信息</h2>
			<p>为了给您提供更精准的保养建议，请您完善一下信息</p>
		</div>
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">当前公里数:</label>
				</div>
				<div class="weui-cell_bd">
					<input class="weui-input" name="mileage" type="number" placeholder="20000" value="${vehicleView.mileage}"/>
				</div>
				<div class="weui-cell_ft">
					<span>公里</span>
				</div>
			</div>
	
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">保养公里数:</label>
				</div>
				<div class="weui-cell_bd">
					<input class="weui-input" name="lastMileage" type="number" placeholder="20000" value="${vehicleView.lastMileage}"/>
				</div>
				<div class="weui-cell_ft">
					<span>公里</span>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">上次保养日期:</label>
				</div>
				<div class="weui-cell_bd">
					<input id="maintainDate" class="weui-input" name="lastMaintainDate" data-toggle='date' value="${vehicleView.lastMaintainDate}"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">电池已用时长:</label>
				</div>
				<div class="weui-cell_bd">
					<input class="weui-input" name="batteryServiceYear" type="number" pattern="[0-9]*" placeholder="2" value="${vehicleView.batteryServiceYear}"/>
				</div>
				<div class="weui-cell_ft">
					<span>年</span>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell_hd">
					<label class="weui-label">轮胎已用时长:</label>
				</div>
				<div class="weui-cell_bd">
					<input class="weui-input" name="tireServiceYear" type="number" pattern="[0-9]*" placeholder="2" value="${vehicleView.tireServiceYear}"/>
				</div>
				<div class="weui-cell_ft">
					<span>年</span>
				</div>
			</div>
			
			<div style="margin:0 auto; width:250px">
					<a href="#" class="weui-btn weui-btn_mini weui-btn_primary" style="display:inline-block;width:100px;">取消</a>
					<a href="javascript:goNext();" class="weui-btn weui-btn_mini weui-btn_primary" style="display:inline-block;width:100px;">下一步</a>
			</div>
		</div>
	</form>
</div>

<script src="${path}/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
	
	function goNext(){
		$('#myForm').submit();
	}
	
	var defUser = ${vehicleView.userId}+"";
	$(function(){
		console.log('defUser=' + defUser);
		if(defUser && "" != defUser){
			$('#myUser').children().each(function(){
				var $opt =$(this);
				if($opt.val() == defUser){
					$opt.attr('selected','selected');
				}
			});
		}
	});
</script>
</body>
</html>