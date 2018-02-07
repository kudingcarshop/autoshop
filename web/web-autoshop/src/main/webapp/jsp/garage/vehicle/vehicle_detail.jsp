<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/vehicle_detail.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 车主信息 -->
		<div class="kd-hd">
			<div class="kd-hd-img kd-left">头像</div>
			<div class="kd-hd-desc kd-left">
				<p>
					用户:${veh.user.name }<a class="kd-btn kd-right">编辑</a>
				</p>
				<p>联系电话:${veh.user.phoneNumber}</p>
				<p>车辆类型:${veh.model}</p>
			</div>
		</div>
		<!-- 到店情况 -->
		<div>
			<table>
				<thead>
					<tr>
						<th>到店情况</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>上次到店日期</td>
						<td><fmt:formatDate value="${veh.lastGarageDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td>未处理违章</td>
						<td><fmt:formatNumber value="${vioNum}" pattern="###,###.###"/>条</td>
					</tr>
					<tr>
						<td>消费总额</td>
						<td>&yen;<fmt:formatNumber value="${veh.totalConsumption}" pattern="###,###.###"/></td>
						<td><a href="#">详情&gt;&gt;</a></td>
					</tr>
					<tr>
						<td>欠账</td>
						<td>&yen;<fmt:formatNumber value="${veh.totalDebt}" pattern="###,###.###"/></td>
						<td><a href="#">详情&gt;&gt;</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 行驶证信息 -->
		<div>
		<table>
				<thead>
					<tr>
						<th>行驶证信息</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>车架号</td>
						<td>${veh.vin }</td>
					</tr>
					<tr>
						<td>发动机号</td>
						<td>${veh.engineNumber}</td>
					</tr>
					<tr>
						<td>登记日期</td>
						<td><fmt:formatDate value="${veh.registerDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td>车主</td>
						<td>${veh.owner}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 车况信息 -->
		<div>
		<table>
				<thead>
					<tr>
						<th>车况信息</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>当前公里数</td>
						<td><fmt:formatNumber value="${veh.lastMileage}" pattern="###,###.###"/>(KM)</td>
					</tr>
					<tr>
						<td>上次保养公里数</td>
						<td><fmt:formatNumber value="${veh.lastMaintainMileage}" pattern="###,###.###"/>(KM)</td>
					</tr>
					<tr>
						<td>上次保养日期</td>
						<td><fmt:formatDate value="${veh.lastMaintainDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td>轮胎使用时长</td>
						<td><fmt:formatNumber value="${veh.tireServiceYear}" pattern="###,###.###"/>(年)</td>
					</tr>
					<tr>
						<td>电池使用时长</td>
						<td><fmt:formatNumber value="${veh.batteryServiceYear}" pattern="###,###.###"/>(年)</td>
					</tr>
					<tr>
						<td>上次美容日期</td>
						<td><fmt:formatDate value="${veh.lastBeautyDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>