<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="stylesheet" href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css">
	<link rel="stylesheet" href="${path}/vendor/jquery-weui/1.2.0/css/kd-jquery-weui.css">
	<link rel="stylesheet" href="${path}/css/kuding/commons.css"/>
	<link rel="stylesheet" href="${path}/css/kuding/backlog_inservice.css"/>
</head>
<body ontouchstart>
	<div class="kd-page">
		<div id="msg" style="display: none;"></div>
		<ul class="kd-tab">
			<li><a href="${path}/garage/backlog/serving">全部(<fmt:formatNumber value="${all}" pattern="###,###.###"/>)</a></li>
			<li><a href="${path}/garage/backlog/inqueue">排队中(<fmt:formatNumber value="${queueNum}" pattern="###,###.###"/>)</a></li>
			<li><a href="${path}/garage/backlog/inserving">服务中(<fmt:formatNumber value="${servingNum}" pattern="###,###.###"/>)</a></li>
		</ul>
		<c:forEach items="${list}" varStatus="status" var="item">
			<div class="kd-bd">
				<p class="kd-seq">${status.index+1}</p>
				<div class="kd-bd-text">
					<p>${item.plateNumber}</p>
					<p>${item.categoryName }</p>
					<p><fmt:formatDate value="${item.receiveTime}" pattern="yyyy-MM-dd hh:mm:ss"/></p>
				</div>
				<select kd-data='${item.state}' kd-id="${item.maintainId}">
					<option value="0" ${item.state eq "0"  ? "selected='selected' " : ""}>排队中</option>
					<option value="1" ${item.state eq "1"  ? "selected='selected' " : ""}>服务中</option>
					<option value="2" ${item.state eq "2"  ? "selected='selected' " : ""}>服务完成</option>
					<option value="3" ${item.state eq "3"  ? "selected='selected' " : ""}>已交车</option>
				</select>
			</div>
		</c:forEach>
		<form id="myForm" action="${path}/garage/backlog/changeState" method="post">
			<input type="hidden" name="act">
			<input type="hidden" name="maintainId">
			<input type="hidden" name="state">
		</form>
	</div>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
	<script>
	  $(function() {
	    FastClick.attach(document.body);
	  });
	</script>
	<script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script type="text/javascript">
	
		var act = ${act==null ? '0' : act};
		var message = '${msg}';
		var msgType = '${msgType}';
		
		$(function(){
			//初始化当前tab
			$('.kd-tab').children().each(function(index){
				var $li= $(this);
				if(index == act){
					$li.addClass('kd-active');
				}
			});
			
			//系统提示
			function showMsg(isError){
				var $msg = $('#msg');
				if(message && message!= ''){
					setTimeout(function(){
						if(isError && isError == 1){
							if($msg.hasClass('kd-success')){
								$msg.removeClass('kd-success');
							}
							$msg.addClass('kd-danger');
						}else{
							if($msg.hasClass('kd-danger')){
								$msg.removeClass('kd-danger');
							}
							$msg.addClass('kd-success');
						}
						if(message == 'success'){
							$msg.text("更新成功!");
						}else{
							$msg.text(message);
						}
						$msg.toggle('normal');
						dismissMsg();
					},500); 
				}
			}
			
			function dismissMsg(){
				var $msg = $('#msg');
				if(message && message!= ''){
					setTimeout(function(){
						$msg.toggle('normal');
					},2000); 
				}
			}
			
			showMsg(msgType);
			
			//ajax提交变更服务状态
			function changeState(sel){
				//重新设置参数
				if(sel){
					if(!sel.attr('kd-id')){
						alert("error");
					}
					$('input[name="maintainId"]').val(sel.attr('kd-id'));
					$('input[name="state"]').val(sel.val());
					$('input[name="act"]').val(act);
					$('#myForm').submit();
				}
			}
			
			//重置服务状态
			function resetOption(sel){
				if(sel){
					sel.children().each(function(){
						var $opt = $(this);
						if($opt.val() == sel.attr('kd-data')){
							$opt.prop('selected',true);
						}
					});
				}
			}
			
			
			
			//车辆状态变更
			$('select').each(function(){
				$(this).change(function(){
					var $sel = $(this);
					$('#myconfirm').show();
					$.confirm({
						  title: '确认变更',
						  text: '确认变更当前服务状态吗？',
						  onOK: function () {
							  changeState($sel);
						  },
						  onCancel: function () {
							  resetOption($sel);
						  }
						}); 
					
				});
			});
		});
	</script>
</body>
</html>
