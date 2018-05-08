<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/customer/backlog/customer_coupons.css"/>
</head>
<body>
	<div class="kd-page">
			<div class="kd-item-hd" style="background: #fff;z-index: 0;">
				<i class="fa fa-money fa-2x"></i><span>优惠券</span> <i class="fa fa-angle-right fa-2x"></i> <span>全部(2)</span>
			</div> 
		<div class="kd-item">
			<div id="wrapper">
				<div id="scroller">
					<div class="kd-item-bd">
						<div>
							<span>通用优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>

					<div class="kd-item-bd">
						<div>
							<span>钣金优惠券</span> <span> 满1000减50</span>
						</div>
						<span class="kd-desc">可用时间：2018.01.01 - 2018.06.30</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/iscroll/iscroll-probe.js"></script>
	<script type="text/javascript">
	//设置wrapper高度
	function resetWrapperHeight(){
		var height = document.body.clientHeight-$('.kd-item-hd').height()-parseInt($('.kd-item-hd').css('marginTop').replace('px',''))-parseInt($('.kd-item-hd').css('marginBottom').replace('px',''))-10;
		$('#wrapper').height(height);
	}
	
	$(function(){
		
		//刷新状态 0-初始化 1-加载更多 2-刷新 3-加载中
		$('#wrapper').attr('state','0');
		var basicSize = basicRem();
		
		//设置wrapper高度
		resetWrapperHeight();
		$(window).resize(function(){
			resetWrapperHeight();
		});
		
		var myscroll = new IScroll("#wrapper",{
			probeType: 2, 
			mouseWheel:true,
			click:true,
			snap:'div',
			mouseWheel: true		
		});
		//保证记录较少时依然能够滚动
		myscroll.hasVerticalScroll = true;
		
		myscroll.on('scroll',function(){
			//加载中
			if($('#wrapper').attr('state') == '3'){
				return;
			}
			
			//向上
			if(this.y < 0){
				$('#header').remove();
				//滚动到底部,添加提示
				if(this.y > (this.maxScrollY-(0.1*basicSize))){
					if($('#footer').length <= 0){
						//$('#scroller').append('<div id="footer" class="load-indicator">上拉加载更多</div>');
						if(this.maxScrollY != 0){
							$('#wrapper').after('<div id="footer" class="load-indicator" style="margin-bottom:16px;">上拉加载更多</div>');
						}else{
							$('#scroller').append('<div id="footer" class="load-indicator">上拉加载更多</div>');
						}
					}
				}else if(this.y <= (this.maxScrollY-(0.2*basicSize)) ){
					if($('#footer').length <= 0){
						if(this.maxScrollY != 0){
							$('#wrapper').after('<div id="footer" class="load-indicator" >上拉加载更多</div>');
						}else{
							$('#scroller').append('<div id="footer" class="load-indicator">上拉加载更多</div>');
						}
					}
					$('#footer').text("释放加载更多");
					$('#wrapper').attr('state','1');
				}
			
			//向下
			}else{
				$('#footer').remove();
				if(this.y>0 && this.y < (0.1*basicSize)){
					if($('#header').length <= 0){
						$('#wrapper').before('<div id="header" class="load-indicator">下拉刷新</div>');
					}
				}else if(this.y >= (0.2*basicSize) ){
					if($('#header').length <= 0){
						$('#wrapper').before('<div id="header" class="load-indicator">下拉刷新</div>');
					}
					$('#header').text("释放刷新");
					$('#wrapper').attr('state','2');
				}
			}
		});
		
		myscroll.on('scrollEnd',function(){
			if($('#wrapper').attr('state') != '1' && $('#wrapper').attr('state') != '2'){
				$('#header').remove();
				$('#footer').remove();
				return;
			}
			
			$('#wrapper').attr('state','3');
			var indicator = $('#header').length > 0 ?  $('#header').html('<i class="fa fa-spinner"></i>加载中') : $('#footer').html('<i class="fa fa-spinner"></i>加载中');
			setTimeout(function(){
				$('#header').remove();
				$('#footer').remove();
				$('#wrapper').attr('state','0');
			},2000);
		});
		
		myscroll.on('refresh',function(){
			console.log('refresh');
			this.hasVerticalScroll = true;
		});
	});
	</script>
</body>
</html>