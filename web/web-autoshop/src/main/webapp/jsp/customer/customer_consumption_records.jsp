<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/customer_consumption_records.css"/>
</head>
<body>
	<div class="kd-page">
		<c:set var="total" value="0"></c:set>
		<c:forEach var="item" items="${records}" varStatus="status">
			<c:set var="total" value="${total+item.value}"></c:set>
			<c:if test="${status.index == 0 }">
				<div class="kd-hd">
					<div>
						<p><fmt:formatDate value="${item.recordTime}" pattern="yyyy年MM月"/></p>
						<p id="total">支出 ¥0.00</p>
					</div>
					<i class="fa fa-calendar fa-2x"></i>
				</div>
			</c:if>
			<div class="kd-detail">
				<p>${item.maintainInfo.categoryName }</p>
				<div>
					<p><fmt:formatDate value="${item.recordTime}" pattern="yyyy-MM-dd"/></p>
					<p><fmt:formatNumber value="${item.value}" pattern="###,###,###"/></p>
				</div>
				<p>备注：${item.desc}</p>
			</div>
		</c:forEach>
		
		<c:if test="${fn:length(records)==0 }">
			<p class="kd-no-data">本月未有消费记录</p>
		</c:if>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script type="text/javascript">
	    $(function(){
	    	$('#total').text('支出 ¥${total}');
	    });
	</script>
</body>
</html>