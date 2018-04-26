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
		<form action="${path}/customer/cars/edit/save" id="myform" method="post">
		<div class="kd-bd">
			<!-- <div class="kd-bd-item">
				<label>当前公里数：</label><input type="text" /><span>公里</span>
			</div> -->
			<input type="hidden" name="vehicleId" value="${veh.id}">
			<div class="kd-bd-item">
				<label>上次保养公里数：</label><input type="text" name="lastMileage" value="${veh.lastMileage}"/><span>公里</span>
			</div>
			<div class="kd-bd-item">
				<label>上次保养日期：</label><input type="text" id="maintainDate" name="lastMaintainDate" value='<fmt:formatDate value="${veh.lastMaintainDate}" pattern="yyyy-MM-dd"/>'/>
			</div>
			<div class="kd-bd-item">
				<label>上次美容日期：</label><input type="text" id="beautyDate" name="lastBeautyDate" value='<fmt:formatDate value="${veh.lastBeautyDate}" pattern="yyyy-MM-dd"/>'/>
			</div>
			<div class="kd-bd-item">
				<label>轮胎使用时长：</label><input type="text" name="tireYears" value='<fmt:formatNumber value="${tireYears}" pattern="#0.00"/>'/><span>年</span>
			</div>
			<div class="kd-bd-item">
				<label>电池使用时长：</label><input type="text" name="batteryYears" value='<fmt:formatNumber value="${batteryYears}" pattern="#0.00"/>'/><span>年</span>
			</div>
		</div>
			<input class="kd-ft-save" type="submit" value="保存"/>
		</form>
	</div>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
	<script src="${path}/vendor/kuding/setup_rem.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
	<script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
	<script src="${path}/vendor/jquery-validation/jquery.validate.min.js"></script>
	<script src="${path}/vendor/kuding/validate.js"></script>
	<script src="${path}/vendor/kuding/common.js"></script>
	<script>
	  var date = new Date();
	  var currenDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
	  $(function() {
	    FastClick.attach(document.body);
	    
	    //服务端检验信息显示
	    var msg = '${msg}';
		showMessage('.kd-page', msg, '0');
	    
	   $('#myform').validate({
	    	rules:{
	    		lastMileage:{
	    			required:true,
	    			digits:true,
	    			min:${veh.lastMileage}
	    		},
	    		tireYears:{
	    			required:true,
	    			isFloat:true,
	    			min:<fmt:formatNumber value='${tireYears}' pattern='#0.00'/>
	    		},
	    		batteryYears:{
	    			required:true,
	    			isFloat:true,
	    			min:<fmt:formatNumber value='${batteryYears}' pattern='#0.00'/>
	    		}
	    	},
	    	messages:{
	    		lastMileage:{
	    			required:"请输入上次保养公里数",
	    			digits:"上次保养公里数只能输入数字",
	    			min:$.validator.format( "上次保养公里数不小于 {0}" )
	    		},
	    		tireYears:{
	    			required:"请输入轮胎使用时长",
	    			isFloat:"轮胎使用时长必须为数字",
	    			min:$.validator.format( "轮胎使用时长不能小于<fmt:formatNumber value='${tireYears}' pattern='#0.00'/>" )
	    		},
	    		batteryYears:{
	    			required:"请输入电池使用时长",
	    			isFloat:"电池使用时长必须为数字",
	    			min:$.validator.format( "电池使用时长不能小于<fmt:formatNumber value='${batteryYears}' pattern='#0.00'/>" )
	    		}
	    	},
			errorPlacement: function( error, element ) {
				error.insertAfter( element.parent() );
			}

	    });
	  
	  });
	  
	  $("#maintainDate").calendar({
		    value:['<fmt:formatDate value="${veh.lastMaintainDate}" pattern="yyyy-MM-dd"/>'],
		    minDate:'${veh.lastMaintainDate}',
	        dateFormat: 'yyyy-mm-dd'  // 自定义格式的时候，不能通过 input 的value属性赋值 '2016年12月12日' 来定义初始值，这样会导致无法解析初始值而报错。只能通过js中设置 value 的形式来赋值，并且需要按标准形式赋值（yyyy-mm-dd 或者时间戳)
	      });
	      
	   $('#beautyDate').calendar({
		   value:['<fmt:formatDate value="${veh.lastBeautyDate}" pattern="yyyy-MM-dd"/>'],
		   minDate:'${veh.lastBeautyDate}',
		   dateFormat:'yyyy-mm-dd'
	   });
	   
	</script>
</body>
</html>