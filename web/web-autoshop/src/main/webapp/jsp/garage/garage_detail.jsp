<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
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
			<img alt="" src="${path}/img/avatar3.jpg">
			<div>
				<h3>XXX汽车养护中心</h3>
				<span>经营范围：汽车维修、保养、汽车美容、钣金喷漆</span>
			</div>
			<a>编辑</a>
		</div>
		<!-- 车行基本信息 -->
		<div class="kd-gra-basic">
			<p>基本信息</p>
			<div><label>成立日期</label> <input type="text" value="2008-12-12"/></div>
			<div><label>地址</label> <input type="text" value="惠州市惠城区小金口镇九龙路口"/></div>
			<div><label>联系电话</label> <input type="text" value="13232312123"/></div>
			<div><label>洗车为</label> <input type="text" value="4"/></div>
			<div><label>机修位</label> <input type="text" value="3"/></div>
			<div><label>烤漆位</label> <input type="text" value="1"/></div>
		</div>
		<div class="kd-gra-team">
			<p>我的团队</p>
			<div>
				<i class="fa fa-user"></i>
				<span>邱总</span>
				<span>老板、财务</span>
			</div>
			<div>
				<i class="fa fa-user"></i>
				<span>邱小姐</span>
				<span>前台接待</span>
			</div>
			<div>
				<i class="fa fa-user"></i>
				<span>xxx</span>
				<span>机修师傅</span>
			</div>
			<div>
				<i class="fa fa-user"></i>
				<span>xxx</span>
				<span>机修中工</span>
			</div>
			<div>
				<i class="fa fa-user"></i>
				<span>xxx</span>
				<span>机修中工</span>
			</div>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>
