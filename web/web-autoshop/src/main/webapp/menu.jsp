<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="path.jsp"%>
<html>
<head>
<title>酷鼎车行</title>
	<title>酷鼎车行公众号</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="js/bootstrap/3.3.7/css/bootstrap.min.css"/>  
	<link rel="stylesheet" href="js/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/kuding/style.css"/>  
	
	<script src="js/jquery/jquery.min.js"></script>
	<script src="js/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/kuding/menu.js"></script>
</head>
<body>
<div class="left-menu">
					<ul>
						<li><a href="${path}/sys/user/show"  target="rightFrame">用户管理</a></li>
						<li><a href="#">系统管理</a></li>
						<li><a href="#">业务管理<i class="fa fa-caret-right"></i></a>
							<div class="left-submenu">
								<ul>
									<li><a>汽车维修</a></li>
									<li><a href="http://www.baidu.com" target="rightFrame">百度搜索</a></li>
									<li><a href="#">汽车保养<i class="fa fa-caret-right"></i></a>
										<div class="left-submenu">
											<ul>
												<li><a href="#">轮胎保养</a></li>
												<li><a href="#">变速箱保养</a></li>
												<li><a href="#">发动机保养</a></li>
											</ul>
										</div>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
</body>
</html>