<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/customer_cards.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-item">
			<div class="kd-item-hd">
				<i class="fa fa-credit-card fa-2x"></i><span>我的卡包</span>
				<span>全部(${fn:length(cards)})</span>
			</div>
			
			<c:forEach items="${cards}" var="item">
				<div class="kd-item-bd">
					<div class="kd-item-bd-left">
						<span>${item.typeName }</span> <span style="margin-top: 0.34rem;"> 当前剩余<fmt:formatNumber value="${item.balance}" pattern="###,##0.##"></fmt:formatNumber>次 </span>
					</div>
					<span class="kd-desc">办卡日期:<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/> </span>
					
				</div>
			</c:forEach>
			<c:if test="${fn:length(cards)==0 }">
				<p class="kd-no-data">欢迎您到车行办卡!</p>
			</c:if>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>
