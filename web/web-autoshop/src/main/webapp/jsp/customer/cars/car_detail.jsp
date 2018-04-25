<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/car_detail.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-hd">
			<div>
			<canvas id="canvas" width="100px" height="110px"></canvas>
			</div>
			<div class="kd-hd-owner">
				<div>
					<div>
						<p>车主：${veh.user.name}</p>
						<p>车龄：<fmt:formatNumber value="${years }" pattern="###,###.0"></fmt:formatNumber>年</p>
					</div>
					<a href="${path}/customer/cars/edit/${veh.id}">编辑</a>
				</div>
				<div class="kd-hd-more">
					<p>${vioCount}</p>
					<p>${servCount}</p>
					<p>违章</p>
					<p>进度</p>
				</div>
			</div>
		</div>
		<c:if test="${vertificationDate != null }">
			<div class="kd-item">
				<p>年审</p>
				<div>
					<div class="kd-date">
						<p>到期日期：<fmt:formatDate value="${vertificationDate}" pattern="yyyy-MM-dd"/></p>
					</div>
					<c:if test="${vertificationInform}">
						<div class="kd-suggest">
							<p>建议：年审即将到期，建议尽快办理</p>
							<a>代办服务</a>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>
		
		<c:if test="${insuranceDate !=null }">
			<div class="kd-item">
				<p>保险</p>
				<div>
					<div class="kd-date">
						<p>到期日期：<fmt:formatDate value="${insuranceDate}" pattern="yyyy-MM-dd"/></p>
					</div>
					<c:if test="${insuranceInform }">
						<div class="kd-suggest">
							<p>建议：保险即将到期，建议尽快办理</p>
							<a>代办服务</a>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>
		
		<c:if test="${veh.lastMaintainDate != null}">
			<div class="kd-item">
				<p>保养</p>
				<div>
					<div class="kd-desc">
						<div>
							<p>上次保养日期：<fmt:formatDate value="${veh.lastMaintainDate}" pattern="yyyy-MM-dd"/></p>
							<p>上次保养公里数：<fmt:formatNumber value="${veh.lastMaintainMileage}" pattern="###,###,###"></fmt:formatNumber></p>
						</div>
						<a href="${path}/customer/cars/maintainDetail/${veh.id}">详情</a>
					</div>
					<c:if test="${maintainInform}">
						<div class="kd-suggest">
							<p>建议：本月内进行常规保养</p>
							<a>马上预约</a>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>
		
		<c:if test="${veh.lastBeautyDate != null }">
			<div class="kd-item">
				<p>美容</p>
				<div>
					<div class="kd-date">
						<p>上次美容日期：<fmt:formatDate value="${veh.lastBeautyDate}" pattern="yyyy-MM-dd"/></p>
					</div>
				</div>
			</div>		
		</c:if>
		
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/kuding/common.js"></script>
	<script type="text/javascript">
		var basicSize = basicRem();
		//加载头像图片
		cropImg('canvas', '${path}/customer/headImg',basicSize,1.1*basicSize);
	</script>
</body>
</html>