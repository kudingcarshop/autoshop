<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
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
		<div class="kd-item" onclick="javascript:window.location.href='${path}/customer/cars/detail';">
			<div class="kd-hd-img"></div>
			<table>
				<tbody>
					<tr>
						<td colspan="2">粤L52017</td>
						<td><span class="kd-cer-lable">已认证</span></td>
					</tr>
					<tr>
						<td class="kd-working">2</td>
						<td class="kd-working">1</td>
						<td class="kd-working">1</td>
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

		<div class="kd-item">
			<div class="kd-hd-img"></div>
			<table>
				<tbody>
					<tr>
						<td colspan="2">粤L59217</td>
						<td><span class="kd-cer-lable">已认证</span></td>
					</tr>
					<tr>
						<td>0</td>
						<td class="kd-working">1</td>
						<td>0</td>
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
		
			<div class="kd-item">
			<div class="kd-hd-img"></div>
			<table>
				<tbody>
					<tr>
						<td colspan="2">粤L88888</td>
						<td><span class="kd-uncer-lable">未认证</span></td>
					</tr>
					<tr>
						<td>0</td>
						<td>0</td>
						<td>0</td>
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
		<a class="kd-btn-add" href="${path}/customer/cars/add"><i class="fa fa-plus-circle fa-2x"></i></a>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
</body>
</html>