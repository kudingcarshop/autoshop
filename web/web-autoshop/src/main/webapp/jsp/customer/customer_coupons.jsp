<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
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
				<i class="fa fa-money fa-2x"></i><span>优惠券</span> <span id="total">全部(${result.total})</span>
			</div> 
		<div class="kd-item">
			<div id="wrapper" page='${page}' rows='${rows}'>
				<div id="scroller" style="overflow: hidden;">
					<c:forEach items="${result.rows }" var="item">
						<div class="kd-item-bd">
							<div>
								<span>${item.couponFactoryEntity.couponName }</span> <span> ${item.couponFactoryEntity.couponDesc}</span>
							</div>
							<span class="kd-desc">可用时间：<fmt:formatDate value="${item.couponFactoryEntity.couponStartDate }" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${item.couponFactoryEntity.couponEndDate }" pattern="yyyy-MM-dd"/></span>
						</div>
					</c:forEach>
				</div>
			</div>
			<c:if test="${result.total == 0 }">
				<p class="kd-no-data">未有优惠券</p>
			</c:if>
		</div>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/iscroll/iscroll-probe.js"></script>
	<c:if test="${result.total > 0 }">
	<script type="text/javascript">

	var basicSize = basicRem();
	
	//设置wrapper高度
	function resetWrapperHeight(size){
		var height = document.body.clientHeight-$('.kd-item-hd').height()-parseInt($('.kd-item-hd').css('marginTop').replace('px',''))-parseInt($('.kd-item-hd').css('marginBottom').replace('px',''))-10;
		if(size != undefined && size > 0){
			height -= size;
		}
		$('#wrapper').height(height);
	}
	
	//加载更多数据
	function loadMore(){
		var mydata = {rows:10};
		if($('#wrapper').attr('page') != undefined){
			mydata.page=parseInt($('#wrapper').attr('page'))+1;
		}
		//状态设置为加载中
		$('#wrapper').attr('state','3');
		console.log('state=' +$('#wrapper').attr('state') );
		$('#footer').html('<i class="fa fa-spinner"></i>加载中');
		//请求数据
		$.ajax({
			url:'${path}/customer/coupons/list',
			method:'post',
			data:mydata,
			success:function(result){
				if(result && result.flag=='1'){
					if(result.extraData != undefined && result.extraData.total != undefined){
						$('#total').text('全部('+result.extraData.total+')');
					}
					if(result.rows && result.rows.length > 0){
						var len = result.rows.length;
						var html = '';
						for(var i =0 ; i< len; i++){
								html = '<div class="kd-item-bd">'
										    +'<div>'
										    		+'<span>'+result.rows[i].couponFactoryEntity.couponName+'</span><span>'+result.rows[i].couponFactoryEntity.couponDesc+'</span>'
										    +'</div>'
										    +'<span class="kd-desc">可用时间：'+result.rows[i].couponFactoryEntity.couponStartDate+'-'+result.rows[i].couponFactoryEntity.couponEndDate+'</span>';
									   +'</div>';
								$('#scroller').append(html);
						}
					}
				}
				
				//重置状态
				$('#header').remove();
				$('#footer').remove();
				$('#wrapper').attr('state','0');
				myscroll.refresh();
			}
		});
		
	}
	
	$(function(){
		
		//刷新状态 0-初始化 1-加载更多 2-刷新 3-加载中
		$('#wrapper').attr('state','0');
		
		//设置wrapper高度
		resetWrapperHeight(0);
		$(window).resize(function(){
			resetWrapperHeight(0);
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
		
		var lastY = 0;
		
		myscroll.on('scroll',function(){
			//加载中
			if($('#wrapper').attr('state') == '3'){
				return;
			}
			
			//向上
			if((this.y - lastY) < 0){
				$('#header').remove();
				//滚动到底部,添加提示
				if(this.y > (this.maxScrollY-(0.1*basicSize))){
					if($('#footer').length <= 0){
						if(this.maxScrollY != 0){
							$('#wrapper').after('<div id="footer" class="load-indicator" style="margin-bottom:16px;">上拉加载更多</div>');
							resetWrapperHeight(0.16*basicSize);
							myscroll.refresh();
						}else{
							$('#scroller').append('<div id="footer" class="load-indicator">上拉加载更多</div>');
						}
					}
				}else if(this.y <= (this.maxScrollY-(0.2*basicSize)) ){
					if($('#footer').length <= 0){
						if(this.maxScrollY != 0){
							$('#wrapper').after('<div id="footer" class="load-indicator" >上拉加载更多</div>');
							resetWrapperHeight(0.16*basicSize);
							myscroll.refresh();
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
			
			lastY=this.y;
		});
		
		myscroll.on('scrollEnd',function(){
			if($('#wrapper').attr('state') != '1' && $('#wrapper').attr('state') != '2'){
				$('#header').remove();
				$('#footer').remove();
				return;
			}
			
			//加载更多
			if($('#wrapper').attr('state') == '1'){
				loadMore();
			}
			
			//刷新
			if($('#wrapper').attr('state') == '2'){
				
			}
			/*var indicator = $('#header').length > 0 ?  $('#header').html('<i class="fa fa-spinner"></i>加载中') : $('#footer').html('<i class="fa fa-spinner"></i>加载中');
			setTimeout(function(){
				$('#header').remove();
				$('#footer').remove();
				$('#wrapper').attr('state','0');
			},2000);*/
		});
		
		myscroll.on('refresh',function(){
			this.hasVerticalScroll = true;
		});
	});
	</script>
	</c:if>
</body>
</html>