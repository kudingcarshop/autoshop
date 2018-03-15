<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/garage_detail.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 车行logo及描述 -->
		<div class="kd-gra-header">
			<img alt="" src="${path}${garage.logo}">
			<div>
				<h3>${garage.name}</h3>
				<span>${garage.desc}</span>
			</div>
			<c:if test="${editable}">
				<a>编辑</a>
			</c:if>
		</div>
		<!-- 车行基本信息 -->
		<div class="kd-gra-basic">
			<p>基本信息</p>
			<div><label>成立日期</label> <input type="text" value='<fmt:formatDate value="${garage.establishDate}" pattern='yyyy-MM-dd'/>'/></div>
			<div><label>地址</label> <input type="text" value="${garage.address}"/></div>
			<div><label>联系电话</label> <input type="text" value="${garage.phoneNo}"/></div>
			<div><label>洗车为</label> <input type="text" value="${garage.carwashRack}"/></div>
			<div><label>机修位</label> <input type="text" value="${garage.carRepairingRack}"/></div>
			<div><label>烤漆位</label> <input type="text" value="${garage.paintingRack}"/></div>
		</div>
		<div class="kd-gra-team">
			<p>我的团队</p>
			<c:forEach items="${staffs}" var="item">
			<c:set var="len" value="${fn:length(item.positions)-1}"></c:set>
			<div>
				<i class="fa fa-user"></i>
				<span>${item.name}</span>
				<span>
				<c:forEach items="${item.positions}" var="position" varStatus="status">
					${position.positionName}
					<c:if test="${status.index< len}">
					、
					</c:if>
				</c:forEach>
				</span>
			</div>
			</c:forEach>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>
