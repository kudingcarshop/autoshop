<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/gra_pc_customers.css"/>
</head>
<body>
	<div class="kd-page">
		<!--搜索  -->
		<div class="kd-search">
			<input type="text" name="search" class="kd-srh-input" placeholder="搜素"/>
			<a id="search-btn" class="kd-srh-btn">搜索</a>
		</div>
		<!-- 总计 -->
		<p class="kd-sum">共${result.total}位客户</p>
		<!-- 列表 -->
		<div id="wrapper">
			<div id="scroller">
				<c:forEach items="${result.rows}" var="item" varStatus="status">
					<div class="kd-list-item" onclick="showDetail(${item.ID});">
						<span class="kd-seq">${status.index+1}</span>
						<div class="kd-content">
							<p>
								<span>${item.NAME}${item.GENDER eq "0" ? "女士" : "先生" }</span>
								<span>${item.TYPE eq "2" ? "个人" : "企业" }</span>
								<a href="callto:${item.PHONE_NUMBER}"><i class="fa fa-phone-square fa-lg"></i></a>
							</p>
							<p>
								车辆<font color="orange">${item.VEH_NUM}部</font>上次到店<font color="orange"><fmt:formatDate value="${item.LAST_GARAGE_DATE }" pattern="yyyy-MM-dd" /></font>
							</p>
						</div>
						<i class="fa fa-angle-right fa-2x kd-right"></i>
					</div>

				</c:forEach>

				<div id="more" class=kd-more page="1" rows="10">
					<a href="" state="0"><i class="fa fa-angle-double-up"></i><span>上拉加载更多</span></a>
				</div>
			</div>
		</div>
	</div>
	<script src="${path}/vendor/iscroll/iscroll-probe.js"></script>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script type="text/javascript">
	function isPassive() {
	    var supportsPassiveOption = false;
	    try {
	        addEventListener("test", null, Object.defineProperty({}, 'passive', {
	            get: function () {
	                supportsPassiveOption = true;
	            }
	        }));
	    } catch(e) {}
	    return supportsPassiveOption;
	}
	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, isPassive() ? {
		capture: false,
		passive: false
	} : false); 
	
	var pullOffSet = 16;
	
	var myscroll = new IScroll("#wrapper",{
		probeType: 2, 
		mouseWheel:true,
		click:true,
		snap:'div',
		mouseWheel: true		
	});
	myscroll.hasVerticalScroll = true;
	
	myscroll.on('scroll',function(){
		//this.y指的是拖动时垂直方向的位置,此时可以超过最大移动值,当垂直移动到加载提示按钮时修改状态准备加载
		if(this.y<this.maxScrollY){
			$('#more a i').removeClass();
			$('#more a i').addClass('fa fa-angle-double-down');
			$('#more a span').text('释放加载');
			$('#more a').attr('state','1');//0-初始值 1-等待拖动完成 2-加载数据中
		}else{
			$('#more a i').removeClass();
			$('#more a i').addClass('fa fa-angle-double-up');
			$('#more a span').text('上拉加载更多');
			$('#more a').attr('state','0');//0-初始值 1-等待拖动完成 2-加载数据中
		}
		var height = document.body.clientHeight-$('.kd-search').height()-$('.kd-sum').height();
		if($('#wrapper').height()>height){
			$('#wrapper').css('height',height);
		}
	});
	
	myscroll.on('refresh',function(){
		$('#more a i').removeClass();
		$('#more a i').addClass('fa fa-angle-double-up');
		$('#more a span').text('上拉加载更多');
		$('#more a').attr('state','0');
		this.hasVerticalScroll = true;
	});
	
	function retrieveParams(needClear){
		var params={};
		var search = $('input[name=search]').val();
		if(search && '' != search){
			params['search']= search;
		}
		var page = $('#more').attr('page');
		if(!needClear){
			if(page && parseInt(page) >= 1){
				params['page']= parseInt(page)+1;
			}
		}
		
		var rows =  $('#more').attr('rows');
		if(rows && parseInt(rows) >= 1){
			params['rows']= rows;
		}
		return params;
	}
	
	function loadMore(needClear){
		$.ajax({
			url:'${path}/garage/center/customers/more',
			data:retrieveParams(needClear),
			success:function(result){
				if(result && result.flag=='1'){
					if(needClear){
						$('.kd-list-item').each(function(){
							$(this).remove();
						});
					}
					var seq = $('#scroller').find('.kd-list-item').length;
					if(result.rows && result.rows.length > 0){
						var html;
						var len = result.rows.length;
						for(var i =0 ; i< len; i++){
							seq++;
							html ='<div class="kd-list-item">'
							 	 +'<span class="kd-seq">'
							 	 	+ seq
							 	 +'</span>'
							 	 +'<div class="kd-content">'
							 		+'<p>'
								 		+'<span>'
								 			+result.rows[i].NAME;
						    if(result.rows[i].GENDER == '0'){
						    	html += '女士';
						    }else{
						    	html += '先生';
						    }
							
						    html+=		'</span>'
								 		+'<span>';
							if(result.rows[i].TYPE == '2'){
								html+=		'个人';
							}else if(result.rows[i].TYPE == '3'){
								html+=		'企业';
							}else{
								html+=		'';//查询错误客户类型不显示
							}
						    html+=		'</span>'
								 		+'<a href="callto:'+result.rows[i].PHONE_NUMBER+'">'+'<i class="fa fa-phone-square fa-lg"></i>'+'</a>'
								 	+'</p>'
								 	+'<p>车辆<font color="orange">'+result.rows[i].VEH_NUM+'部</font>上次到店<font color="orange">'+result.rows[i].LAST_GARAGE_DATE+'</font></p>	'
								 +'</div>'
								 + '<i class="fa fa-angle-right fa-2x kd-right"></i>'
							 +'</div>';
							$('#more').before($(html));
						}
						$('.kd-sum').text('共'+result.extraData.total+'位客户');
						$('#more').attr('page',parseInt($('#more').attr('page'))+1);
					}
				}
				myscroll.refresh();
			}
		});
	}
	
	//搜索按钮点击事件
	$('#search-btn').on('click',function(){
		loadMore(true);
	});
	
	function showDetail(userId){
		if(userId){
			$(window).attr('location','${path}/garage/center/customers/detail/' + userId);
		}
	}
	
	myscroll.on('scrollEnd',function(){
		//停止滚动,若之前已经滚动到底部则开始加载数据
		if($('#more a').attr('state')=='1'){
			$('#more a i').removeClass();
			$('#more a i').addClass('fa fa-circle-o-notch fa-spin');
			$('#more a span').text('加载中');
			loadMore(false);
			$('#more a').attr('state','2');
		}
	});
	</script>
</body>
</html>
