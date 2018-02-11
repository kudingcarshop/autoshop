<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/backlog_unpay.css"/>
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 今天 -->
		<c:if test="${unpays.todayList!= null}">
			<div class="kd-unp-item">
				<div class="kd-unp-hd">
					<input type="checkbox" id="todaySum" class="kd-unp-chk kd-left" />
					<p class="kd-left">今天</p>
					<i id="icon-today" class="fa fa-angle-up fa-2x kd-right"></i>
				</div>
				<ul class="kd-unp-bd" id="bd-today">
		</c:if>
		<c:forEach items="${unpays.todayList}" var="item">
				<li><input type="checkbox" kd-type="1" kd-data="${item.payPrice}" class="kd-unp-chk kd-left" />
					<div class="kd-left">
						<p><fmt:formatDate value="${item.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						<p>${item.plateNumber}</p>
						<p>${item.categoryName }</p>
					</div>
					<div class="kd-unp-opr kd-right">
						<span>&yen;<fmt:formatNumber value="${item.payPrice}" pattern="###,###.###"/></span>
					</div>
				</li>
		</c:forEach>
		<c:if test="${unpays.todayList!= null}">
				</ul>
			</div>
		</c:if>
		
		<!-- 月内 -->
		<c:if test="${unpays.monthList!= null}">
			<div class="kd-unp-item">
				<div class="kd-unp-hd">
					<input type="checkbox" id="monthSum" class="kd-unp-chk kd-left" />
					<p class="kd-left">一个月内</p>
					<i id="icon-month" class="fa fa-angle-up fa-2x kd-right"></i>
				</div>
				<ul class="kd-unp-bd" id="bd-month">
		</c:if>
		<c:forEach items="${unpays.monthList}" var="item">
				<li><input type="checkbox" kd-type="2" kd-data="${item.payPrice}"  class="kd-unp-chk kd-left" />
					<div class="kd-left">
						<p><fmt:formatDate value="${item.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						<p>${item.plateNumber}</p>
						<p>${item.categoryName }</p>
					</div>
					<div class="kd-unp-opr kd-right">
						<span>&yen;<fmt:formatNumber value="${item.payPrice}" pattern="###,###.###"/></span>
					</div>
				</li>
		</c:forEach>
		<c:if test="${unpays.monthList!= null}">
				</ul>
			</div>
		</c:if>
		
		<!-- 超过一个月 -->
		<c:if test="${unpays.overMonthList!= null}">
			<div class="kd-unp-item">
				<div class="kd-unp-hd">
					<input type="checkbox" id="overSum" class="kd-unp-chk kd-left" />
					<p class="kd-left">超过一个月</p>
					<i id="icon-over" class="fa fa-angle-up fa-2x kd-right"></i>
				</div>
				<ul class="kd-unp-bd" id="bd-over">
		</c:if>
		<c:forEach items="${unpays.overMonthList}" var="item">
				<li><input type="checkbox" kd-type="3" kd-data="${item.payPrice}"  class="kd-unp-chk kd-left" />
					<div class="kd-left">
						<p><fmt:formatDate value="${item.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						<p>${item.plateNumber}</p>
						<p>${item.categoryName}</p>
					</div>
					<div class="kd-unp-opr kd-right">
						<span>&yen;<fmt:formatNumber value="${item.payPrice}" pattern="###,###.###"/></span>
					</div>
				</li>
		</c:forEach>
		<c:if test="${unpays.overMonthList!= null}">
				</ul>
			</div>
		</c:if>
		
		<p class="kd-total">
			<input id="totalCheck" type="checkbox" class="kd-unp-chk kd-left"  id="checkAll"/> <span>全选</span>
			<span>总计:&nbsp;&nbsp;&yen;<i id="total"></i></span>
		</p>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script type="text/javascript">
	$(function(){
		var total = 0;
		
		$('input[type="checkbox"]').each(function(){
			var $chk = $(this);
			$chk.prop("checked",true);
			if($chk.attr('kd-data')){
				total += Number($chk.attr('kd-data'));
			}
			console.log("init total = " + total);
		});
		
		//更新总数显示
		function updateTotal(total){
			$('#total').text(total);
		}
		
		//更新check all 勾选状态
		function updateTotalCheck(){
			var allLen = $('input[kd-type="1"],input[kd-type="2"],input[kd-type="3"]').length;
			var chkLen = 0;
			$('input[kd-type="1"],input[kd-type="2"],input[kd-type="3"]').each(function(){
				var $chk=$(this);
				if($chk.is(":checked")){
					chkLen++;
				}
			});
			$('#totalCheck').prop("checked",(allLen==chkLen));
		}
		
		updateTotal(total);
		
		//分类checkbox选择响应函数
		function titleChange(titleObj,itemSelector){
			var $inpt = titleObj;
			var stat = $inpt.is(':checked');
			$(itemSelector).each(function(){
				var $item = $(this);
				$item.prop("checked",stat);
				if($item.attr('kd-data')){
					if(stat){
						total += Number($item.attr('kd-data'));
					}else{
						total -= Number($item.attr('kd-data'));
					}
				}
			});	
			updateTotal(total);
			updateTotalCheck();
		}
		
		//具体行数据选择响应函数
		function itemChange(itemObj,titelSelector,itemSelector){
			var $inpt =itemObj;
			var stat = $inpt.is(':checked');
			if($inpt.attr('kd-data')){
				if(stat){
					total += Number($inpt.attr('kd-data'));
				}else{
					total -= Number($inpt.attr('kd-data'));
				}
			}
			var inpCheckedSize = 0;
			$(itemSelector).each(function(){
				var $item =$(this);
				if($item.is(":checked")){
					inpCheckedSize ++;
				}
			});
			var inpSize = $(itemSelector).length;
			$(titelSelector).prop("checked",(inpCheckedSize== inpSize));
			updateTotal(total);
			updateTotalCheck();
		}
		
		//今天
		$('#todaySum').change(function(){
			titleChange($(this),'input[kd-type="1"]');
		});
		
		$('input[kd-type="1"]').change(function(){
			itemChange($(this),'#todaySum','input[kd-type="1"]');
		});
		
		//月内
		$('#monthSum').change(function(){
			titleChange($(this),'input[kd-type="2"]');
		});
		
		$('input[kd-type="2"]').change(function(){
			itemChange($(this),'#monthSum','input[kd-type="2"]');
		});
		
		//超过一个月
		$('#overSum').change(function(){
			titleChange($(this),'input[kd-type="3"]');
		});
		
		$('input[kd-type="3"]').change(function(){
			itemChange($(this),'#overSum','input[kd-type="3"]');
		});
		
		$('#totalCheck').change(function(){
			console.log("**************");
			var $chk = $(this);
			var sta = $chk.is(":checked");
			console.log("sta = " + sta);
			total = 0;
			$('input[type="checkbox"]').each(function(){
				var $item = $(this);
				$item.prop("checked",sta);
				if($item.attr("kd-data") && sta){
					total += Number($item.attr("kd-data"));
				}
			});	
			updateTotal(total);
		});
		
		function toggleIcon(iconSelector,bdSelectory){
			$(iconSelector).click(function(){
				$(bdSelectory).toggle();
				if($(this).hasClass('fa-angle-up')){
					$(this).removeClass('fa-angle-up');
					$(this).addClass('fa-angle-down');
				}else{
					$(this).removeClass('fa-angle-down');
					$(this).addClass('fa-angle-up');
				}
			});
		}
		toggleIcon('#icon-today','#bd-today');
		toggleIcon('#icon-month','#bd-month');
		toggleIcon('#icon-over','#bd-over');
		
	});
	</script>
</body>
</html>