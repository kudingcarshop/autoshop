<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/backlog/bills.css"/>
</head>
<body>
	<div class="kd-page">
		<c:forEach var="item" items="${unpay}">
			<c:if test="${plateNumber == null || item.vehicle.plateNumber ne plateNumber}">
				<div class="kd-bills"><!-- kd-bills begin -->
					<div class="kd-bills-hd">
						<input type="checkbox" kd-type="${item.vehicle.plateNumber}" onchange="vehicleCheckChange(this,'${item.vehicle.plateNumber}');"/> <span>${item.vehicle.plateNumber}</span>
					</div>
					<c:set var="plateNumber" value="${item.vehicle.plateNumber}"></c:set>
			</c:if>
			
				<div class="kd-bills-bd">
					<input type="checkbox" kd-type="${item.vehicle.plateNumber}" kd-data='<fmt:formatNumber value="${item.payPrice}" pattern="###,##0.###"></fmt:formatNumber>' onchange="dataCheckChange('${item.vehicle.plateNumber}');"/>
					<div>
						<p>${item.categoryName }</p>
						<p>${item.memo }</p>
					</div>
					<p><fmt:formatNumber value="${item.payPrice}" pattern="###,##0.###"></fmt:formatNumber></p>
				</div>
				
				<c:if test="${plateNumber != null && item.vehicle.plateNumber ne plateNumber}">
					</div><!-- kd-bills end -->
				</c:if>
		</c:forEach>
		<div class="kd-bills-ft">
			<input type="checkbox" id="totalCheck" onchange="checkAll($(this).is(':checked'));"/> 
			<a>结算<label id="itemCount">(2)</label></a>
			<span id="total">630.00</span>
			<span>合计：</span> 
			 <span>全选</span>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script type="text/javascript">
	
	//全选
	function checkAll(checked){
		var items = 0;
		var total = 0;
		//默认全选
		$('input[type=checkbox]').each(function(){
			$(this).prop('checked',checked);
			if($(this).attr('kd-data') && checked){
				items++;
				total+=parseFloat($(this).attr('kd-data'));
			}
		});
		$('#itemCount').text('('+items+')');
		$('#total').text(total);
	}
	
	//根据勾选状态，更新全选状态
	function updateAll(){
		var items = 0;
		var total = 0;
		var isAllChecked = true;
		$('input[type=checkbox]').each(function(){
			if($(this).attr('kd-data')){
				if($(this).is(':checked')){
					items++;
					total+=parseFloat($(this).attr('kd-data'));
				}else{
					isAllChecked = false;
				}
			}
		});
		$('#itemCount').text('('+items+')');//记录条数
		$('#total').text(total);//待付款总数
		$('#totalCheck').prop('checked',isAllChecked);//checkbox状态
	}

	//根据类型数据状态，更新类型的勾选状态
	function updateAllType(type){
		var isAllTypeChecked = true;
		$('input[type=checkbox][kd-type='+type+']').each(function(){
			var $item = $(this);
			if($item.attr('kd-data') && !$item.is(':checked')){
				isAllTypeChecked = false;
				return false;
			}
		});
		$('input[type=checkbox][kd-type='+type+']').each(function(){
			var $chk = $(this);
			if($chk.attr('kd-data')==undefined){
				$chk.prop('checked',isAllTypeChecked);
				return false;
			}
		});
	}
	
	//单条数据事件响应
	function dataCheckChange(type){
		updateAllType(type);
		updateAll();//全选相关更新
	}
	
	//响应车辆勾选状态变化
	function vehicleCheckChange(checkbox,type){
		var $chk = $(checkbox);
		$('input[kd-type='+$chk.attr('kd-type')+']').each(function(){
			var $item = $(this);
			if($item.attr('kd-data')){
				$item.prop('checked',$chk.is(':checked'));
			}
		});
		updateAll();
	}
	
	$(function(){
		checkAll(true);
	});
	</script>
</body>
</html>