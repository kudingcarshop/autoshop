<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path}/js/bootstrap/3.3.7/css/bootstrap.min.css"/>  
	<link rel="stylesheet" href="${path}/js/bootstrap-table/bootstrap-table.min.css"/> 
	
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="${path}/vendor/bootstrap-table/js/bootstrap-table.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#table").bootstrapTable({
			url: '/sys/user/show',
			striped: true, 
			pagination: true,
			sortable: false,
			sidePagination: "server",
			pageNumber:1,
			pageSize: 10,
			showColumns: true,  
			columns: [{
				field: 'loginName',
                title: '帐号'
			}]
		});
	});
	</script>
<title>测试</title>
</head>
<body>
	<table id="table"></table>
</body>
</html>