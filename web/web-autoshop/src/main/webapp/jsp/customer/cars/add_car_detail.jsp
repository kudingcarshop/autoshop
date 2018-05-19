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
<form id="myform" action="${path}/customer/cars/add/save" method="post">
	<div class="kd-page">
		<div class="kd-hd">
			<div class="kd-hd-item">
				<label>所有者：</label><span>${carView.name }</span>
				<input type="hidden" name="name" value="${carView.name}">
			</div>
			
			<div class="kd-hd-item">
				<label>车牌号：</label><input type="text" name="plateNumber" value="${carView.plateNumber}" />
			</div>
			
			<div class="kd-hd-item">
				<label>登记日期：</label><input type="text" id="registerDate" name="registerDate" value="${carView.registerDate}"/>
			</div>
			<div class="kd-hd-item">
				<label>车架号：</label><input type="text" name="vin" value="${carView.vin}"/>
			</div>
			<div class="kd-hd-item">
				<label>发动机号：</label><input type="text" name="engineNumber" value="${carView.engineNumber }"/>
			</div>
		</div>
		<div class="kd-desc">
			<i class="fa fa-exclamation-circle"></i>
			<p>为了给您提供更准确的保养建议，请完善以下信息</p>
		</div>
		<div class="kd-bd">
			<div class="kd-bd-item">
				<label>当前公里数：</label><input type="text" name="lastMileage" value="${carView.lastMileage }"/><span>公里</span>
			</div>
			<div class="kd-bd-item">
				<label>上次保养日期：</label><input type="text" id="lastMaintainDate" name="lastMaintainDate" value="${carView.lastMaintainDate }"/>
			</div>
			<div class="kd-bd-item">
				<label>上次美容日期：</label><input type="text" id="lastBeautyDate" name="lastBeautyDate" value="${carView.lastBeautyDate }"/>
			</div>
			<div class="kd-bd-item">
				<label>轮胎使用时长：</label><input type="text" name="tireYears" value="${carView.tireYears}"/><span>年</span>
			</div>
			<div class="kd-bd-item">
				<label>电池使用时长：</label><input type="text" name="batteryYears" value="${carView.batteryYears}"/><span>年</span>
			</div>
		</div>
		<input class="kd-ft-save" type="submit" value="保存"/>
	</div>
</form>
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
	<script src="${path}/vendor/jquery-validation/jquery.validate.min.js"></script>
	<script src="${path}/vendor/kuding/validate.js"></script>
	<script src="${path}/vendor/kuding/common.js"></script>
	<script type="text/javascript">
	 $(function() {
		    FastClick.attach(document.body);
		    
		    //服务端检验信息显示
		    var msg = '${msg}';
			showMessage('.kd-page', msg, '0');
			
			$("#registerDate").calendar({
				 dateFormat: 'yyyy-mm-dd' 
			});
			$("#lastMaintainDate").calendar({
				 dateFormat: 'yyyy-mm-dd' 
			});
			$("#lastBeautyDate").calendar({
				 dateFormat: 'yyyy-mm-dd' 
			});
		  
			$('#myform').validate({
		    	rules:{
		    		plateNumber:{
		    			required:true,
		    			isPlateNumber:true
		    		},
		    		vin:{
		    			required:true,
		    			minlength:17,
		    			maxlength:17
		    		},
		    		engineNumber:{
		    			required:true,
		    			maxlength:50
		    		},
		    		lastMileage:{
		    			required:true,
		    			digits:true,
		    			min:0
		    		},
		    		tireYears:{
		    			required:true,
		    			isFloat:true
		    		},
		    		batteryYears:{
		    			required:true,
		    			isFloat:true
		    		}
		    	},
		    	messages:{
		    		plateNumber:{
		    			required:"请输入车牌号"
		    		},
		    		vin:{
		    			required:"请输入车架号",
		    			minlength:"请输入17位车架号",
		    			maxlength:"请输入17位车架号"
		    		},
		    		engineNumber:{
		    			required:"请输入发动机号",
		    			maxlength:"发动机号长度不能超过50"
		    		},
		    		lastMileage:{
		    			required:"请输入里程数",
		    			digits:"里程数只能是数字",
		    			min:"里程数需大于0"
		    		},
		    		tireYears:{
		    			required:"请输入轮胎使用年限",
		    			isFloat:"轮胎使用年限需是数字"
		    		},
		    		batteryYears:{
		    			required:"请输入电池使用年限",
		    			isFloat:"电池使用年限需是数字"
		    		}
		    	},
				errorPlacement: function( error, element ) {
					error.insertAfter( element.parent() );
				}

		    });
		  });
	</script>
</body>
</html>