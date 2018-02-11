<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/backlog_booking.css"/>
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
	<div class="kd-page">
	<c:forEach items="${booking}" var="item" varStatus="status">
		<c:choose>
			<c:when test="${item.titleTime != null }">
				<div class="kd-hd">
					<div class="kd-hd-icon">
					<i class="fa fa-calendar fa-2x"></i>
					</div>
					<p class="kd-hd-text"><fmt:formatDate value="${item.titleTime}" pattern="yyyy-MM-dd"/></p>
					<div class="kd-hd-opr">
					<a class="fa fa-angle-up fa-2x"></a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="kd-bd">
					<p class="kd-bd-seq">${item.seq}</p>
					<div class="kd-bd-content">
						<p>
							<span class="kd-left">${item.plateNumber }</span> <span class="kd-right"><fmt:formatDate value="${item.bookTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						</p>
						<p>
							<span class="kd-left">汽修</span> <a class="kd-right">联系车主</a>
						</p>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>