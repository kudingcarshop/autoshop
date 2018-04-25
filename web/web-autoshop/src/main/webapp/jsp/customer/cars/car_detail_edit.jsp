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
	<link rel="stylesheet" href="${path}/css/kuding/customer/car_detail_edit.css"/>
</head>
<body>
	<div class="kd-page">
		<!-- 车主 -->
		<div class="kd-hd">
			<div class="kd-hd-item">
				<label>所有者：</label><span>${veh.user.name }</span>
			</div>
			<div class="kd-hd-item">
				<label>登记日期：</label><span><fmt:formatDate value="${veh.registerDate}" pattern="yyyy-MM-dd"/></span>
			</div>
		</div>
		<!-- 描述 -->
		<div class="kd-desc">
			<i class="fa fa-exclamation-circle"></i> 
			<p>为了给您提供更准确的保养建议，请完善以下信息</p>
		</div>
		<!-- 车辆信息编辑 -->
		<div class="kd-bd">
		<form action="${path}/customer/edit/save" id="myform">
			<!-- <div class="kd-bd-item">
				<label>当前公里数：</label><input type="text" /><span>公里</span>
			</div> -->
			<div class="kd-bd-item">
				<label>上次保养公里数：</label><input type="text" name="lastMileage" value="${veh.lastMileage}"/><span>公里</span>
			</div>
			<div class="kd-bd-item">
				<label>上次保养日期：</label><input type="text" id="maintainDate" name="lastMaintainDate" value="${veh.lastMaintainDate}"/>
			</div>
			<div class="kd-bd-item">
				<label>上次美容日期：</label><input type="text" id="beautyDate" name="lastBeautyDate" value="${veh.lastBeautyDate}"/>
			</div>
			<div class="kd-bd-item">
				<label>轮胎使用时长：</label><input type="text" name="tireYears" value='<fmt:formatNumber value="${tireYears}" pattern="#0.00"/>'/><span>年</span>
			</div>
			<div class="kd-bd-item">
				<label>电池使用时长：</label><input type="text" name="batteryYears" value='<fmt:formatNumber value="${batteryYears}" pattern="#0.00"/>'/><span>年</span>
			</div>
		</form>
		</div>
		<a class="kd-ft-save">保存</a>
	</div>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
	<script src="${path}/vendor/jquery-validation/jquery.validate.min.js"></script>
	<script>
	  var date = new Date();
	  var currenDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
	  $(function() {
	    FastClick.attach(document.body);
	    
	    $('#myform').validate({
	    	debug:true,
	    	rules:{
	    		lastMileage:{
	    			required:true,
	    			digits:true,
	    			min:${veh.lastMileage}
	    		}
	    	},
	    	messages:{
	    		lastMileage:{
	    			required:"请输入上次保养公里数",
	    			digits:"上次保养公里数只能输入数字",
	    			min:$.validator.format( "上次保养公里数不小于 {0}" )
	    		}
	    	},
			errorPlacement: function( error, element ) {
				error.insertAfter( element.parent() );
			}

	    });
	  });
	  
	  $("#maintainDate").calendar({
	        dateFormat: 'yyyy-mm-dd'  // 自定义格式的时候，不能通过 input 的value属性赋值 '2016年12月12日' 来定义初始值，这样会导致无法解析初始值而报错。只能通过js中设置 value 的形式来赋值，并且需要按标准形式赋值（yyyy-mm-dd 或者时间戳)
	      });
	      
	   $('#beautyDate').calendar({
		   dateFormat:'yyyy-mm-dd'
	   });
	   
	</script>
</body>
</html>