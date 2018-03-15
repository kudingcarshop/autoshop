<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<title>酷鼎车行</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<style type="text/css">
*{
	margin:0;
	padding:0;
}

a{
	text-decoration: none;
	color: #101010;
}

.kd-container {
	margin:0 auto;
	padding:0.08rem;
	width: 3.2rem;
	font-size:0.14rem;
}


/**标题*/
.kd-title{
	margin:0;
	text-align:center;
}

/**搜索*/
.kd-search{
	overflow: hidden;
	margin-top: 0.1rem;
}

.kd-input{
	display:block;
	float:left;
	width:2.2rem;
	height:0.32rem;
}

.kd-btn{
	width:0.84rem;
	display:block;
	float:left;
	height:0.3rem;
	line-height:0.24rem;
	text-align:center;
	color:#999;
	border:0.01rem solid #999;
}

.kd-right{
	float:right;
	text-align: right;
}

/**导航*/
.kd-nav{
	list-style-type:none;
	overflow: hidden;
	margin-top: 0.1rem;
}
.kd-nav ul{
	overflow: hidden;
	margin:0;
}
.kd-nav li{
	width:0.76rem;
	height:0.24rem;
	display:block;
	text-align:center;	
	float: left;
}

/**列表*/
.kd-list{
	padding:0;
	list-style:none;
}

.kd-list-item{
	display:block;
	margin-top:0.1rem;
	overflow: hidden;
}

.kd-list-seq{
	width:0.24rem;
	height:0.72rem;
	float:left;
	line-height:0.72rem;
	text-align: center;
} 
.kd-list-content{
	list-style:none;
	width:2.5rem;
	float:left;
}

.kd-list-content div{
	height:0.24rem;
	line-height:0.26rem;
}
</style>
</head>
<body>
<div style="font-size: 12px;">
${errors}
</div>
<div class="kd-container">
	<!-- 标题 -->
	<div class="kd-title">我的车辆</div>
	<!-- 搜索 -->
	<div class="kd-search">
		<form action="/vehicle/qry" method="post">
			<div>
				<input name="queryStr" class="kd-input"/><a href="${path}/garage/vehicle/addCarOptions" class="kd-btn">添加车辆</a>
			</div>
		</form>
	</div>
	<!-- 功能导航 -->
	<div class="kd-nav">
		<ul>
			<li>全部</li>
			<li>服务中${working_count}</li>
			<li>代办</li>
			<li>赊账${un_pay_count}</li>
		</ul>
	</div>
	<!-- 车辆列表 -->
		<div class="kd-list">
			<!-- 单条记录 -->
			<c:forEach items="${vehicles}" var="item" varStatus="status">
			<hr style="height: 1px dotted;">
			<a href="${path}/garage/vehicle/showVehicleDetail?vehicleId=${item.id}" class="kd-list-item">
				<div class="kd-list-seq">${status.index+1}</div>
				<div class="kd-list-content">
					<div style="overflow: hidden;">${item.plateNumber}(${item.model})</div>
					<div>车主：${item.user.name}(${item.user.phoneNumber})<i class="fa fa-angle-right pull-right fa-2x" style="height: 0.26rem; line-height: 0.26rem;text-align: center;"></i></div>
					<div>上次到店时间：2017-12-10</div>
				</div>
			</a>
			</c:forEach>
		</div>
</div>
<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>