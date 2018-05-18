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
	<link rel="stylesheet" href="${path}/css/kuding/customer/add_car_detail.css"/>
</head>
<body>
	<div class="kd-page">
		<div class="kd-hd">
			<div class="kd-hd-item">
				<label>所有者：</label><span>李阿四</span>
			</div>
			
			<div class="kd-hd-item">
				<label>车牌号：</label><input type="text" value="粤LSD888" />
			</div>
			
			<div class="kd-hd-item">
				<label>登记日期：</label><input type="text" />
			</div>
			<div class="kd-hd-item">
				<label>车架号：</label><input type="text" />
			</div>
			<div class="kd-hd-item">
				<label>发动机号：</label><input type="text" />
			</div>
		</div>
		<div class="kd-desc">
			<i class="fa fa-exclamation-circle"></i>
			<p>为了给您提供更准确的保养建议，请完善以下信息</p>
		</div>
		<div class="kd-bd">
			<div class="kd-bd-item">
				<label>当前公里数：</label><input type="text" /><span>公里</span>
			</div>
			<div class="kd-bd-item">
				<label>上次保养日期：</label><input type="text" id="maintainDate"/>
			</div>
			<div class="kd-bd-item">
				<label>上次美容日期：</label><input type="text" id="beautyDate"/>
			</div>
			<div class="kd-bd-item">
				<label>轮胎使用时长：</label><input type="text" /><span>年</span>
			</div>
			<div class="kd-bd-item">
				<label>电池使用时长：</label><input type="text" /><span>年</span>
			</div>
		</div>
		<a class="kd-ft-save">保存</a>
	</div>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
	<script src="${path}/vendor/jquery-validation/jquery.validate.min.js"></script>
	<script src="${path}/vendor/kuding/validate.js"></script>
	<script type="text/javascript">
	 $(function() {
		    FastClick.attach(document.body);
		    
		    //服务端检验信息显示
		    var msg = '${msg}';
			showMessage('.kd-page', msg, '0');
		  
		  });
	</script>
</body>
</html>