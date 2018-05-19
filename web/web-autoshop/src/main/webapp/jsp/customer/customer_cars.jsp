<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/customer_cars.css"/>
</head>
<body>
	<div class="kd-page">
	<c:forEach var="item" items="${vehicles}">
			<div class="kd-item"
				onclick="javascript:window.location.href='${path}/customer/cars/detail/${item.veh.id}';">
				<table>
					<tbody>
						<tr>
							<td colspan="2">${item.veh.plateNumber}</td>
							<c:if test="${item.veh.certFlag == 1 }">
								<td><span class="kd-cer-lable">已认证</span></td>
							</c:if> 
							
							<c:if test="${item.veh.certFlag == 0 }">
								<td><span class="kd-uncer-lable">未认证</span></td>
							</c:if> 
						</tr>
						<tr>
							<td class="kd-working">${item.vioCount}</td>
							<td class="kd-working">${item.sugCount}</td>
							<td class="kd-working">${item.servCount }</td>
						</tr>
						<tr>
							<td>待办</td>
							<td>建议</td>
							<td>进度</td>
						</tr>
					</tbody>
				</table>
				<i class="fa fa-angle-right fa-2x"></i>
			</div>
		</c:forEach>
		
		<c:if test="${fn:length(vehicles)==0 }">
			<p class="kd-no-data">未添加车辆</p>
		</c:if>
		<a class="kd-btn-add" href="${path}/customer/cars/add"><i class="fa fa-plus-circle fa-2x"></i></a>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>