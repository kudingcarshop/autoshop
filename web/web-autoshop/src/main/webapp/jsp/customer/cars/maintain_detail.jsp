<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/car_detail_maintain.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-desc">
			<i class="fa fa-exclamation-circle"></i>
			<p>所有保养建议根据大数据分析所得，仅作参考，不具实际考量意义，实际情况需到店咨询</p>
		</div>
		<div class="kd-bd">
			<div class="kd-bd-title">
				<p>上次保养至今</p>
			</div>
			<!-- <div class="kd-bd-detail">
				<label>已开：</label> <span>8000公里</span>
			</div> -->
			<div class="kd-bd-detail">
				<label>已开：</label> <span><fmt:formatNumber value="${maintainMonths}" pattern="###,###,###"></fmt:formatNumber>个月</span>
			</div>
			<c:if test="${maintainInform }">
				<div class="kd-bd-warm">
					<i class="fa fa-exclamation-circle"></i>
					<p>根据您的使用情况分析，建议做一次常规保养</p>
				</div>
			</c:if>
		</div>
		<div class="kd-bd">
			<div class="kd-bd-title">
				<p>轮胎</p>
				<c:choose>
					<c:when test="${tireYears<=5}">
						<label>正常</label>
					</c:when>
					<c:otherwise>
						<label style="background: orange;width:0.56rem;">建议更换</label>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- <div class="kd-bd-detail">
				<label>已开：</label> <span>6800公里</span>
			</div> -->
			<div class="kd-bd-detail">
				<label>已用：</label> <span><fmt:formatNumber value="${tireYears}" pattern="###,###,##0.0"></fmt:formatNumber> 年</span>
			</div>
		</div>
		
		<div class="kd-bd">
			<div class="kd-bd-title">
				<c:choose>
				 <c:when test="${batteryYears<=3}">
				 	<label>正常</label>
				 </c:when>
				 <c:otherwise>
				 	<label style="background:orange;width:0.56rem;">建议更换</label>
				 </c:otherwise>
				</c:choose>
				<p>电池</p>
			</div>
			<div class="kd-bd-detail">
				<label>已用：</label> <span><fmt:formatNumber value="${batteryYears}" pattern="###,###,##0.0"></fmt:formatNumber> 年</span>
			</div>
		</div>

	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>