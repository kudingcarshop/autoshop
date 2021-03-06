<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" media="screen">
    <script src="${path}/vendor/jquery/jquery.min.js"></script>
    <script src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>个人中心</title>
    <style>
    .user_title_panle {
        margin-top: 0.5em;
        margin-left: 0.5em;
        margin-right: 0.5em;
    }

    .user_icon {
        margin-top: 1em;
        margin-bottom: 1em;
        width: 6em;
        height: 6em;
        border-radius: 5em;
        border: 1;
    }

    .weui-grid-for-four {
        width: 25%;
    }

    body {
        height: 100%;
    }
    </style>
</head>

<body ontouchstart>
    <div class="panel panel-default user_title_panle">
        <div class="panel-body">
            <img class="weui-media-box__thumb center-block user_icon" src="${path}/img/avatar3.jpg">
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    积分
                </div>
                <div class="weui-cell__ft">500</div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    注册车辆
                </div>
                <div class="weui-cell__ft">${requestScope.vehicleSum}</div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    待办事项
                </div>
                <div class="weui-cell__ft">无</div>
            </a>
        </div>
    </div>
    <div class="panel panel-default user_title_panle">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                我的卡片
            </div>
            <div class="weui-cell__ft">查看全部</div>
        </a>
        <div class="weui-grids">
            <a href="#" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">8</span>
                </div>
                <p class="weui-grid__label">
                    洗车卡(次)
                </p>
            </a>
            <a href="#" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">2</span>
                </div>
                <p class="weui-grid__label">
                    优惠卡(张)
                </p>
            </a>
            <a href="#" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">0</span>
                </div>
                <p class="weui-grid__label">
                    礼品卡(张)
                </p>
            </a>
        </div>
    </div>
    <div class="panel panel-default user_title_panle">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                消费记录(总消费: 20168元)
            </div>
            <div class="weui-cell__ft">查看全部</div>
        </a>
        <div class="weui-grids">
            <a href="#" class="weui-grid js_grid weui-grid-for-four">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">8</span>
                </div>
                <p class="weui-grid__label">
                    代付款
                </p>
            </a>
            <a href="#" class="weui-grid js_grid weui-grid-for-four">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">2</span>
                </div>
                <p class="weui-grid__label">
                    汽修
                </p>
            </a>
            <a href="#" class="weui-grid js_grid weui-grid-for-four">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">0</span>
                </div>
                <p class="weui-grid__label">
                    保养
                </p>
            </a>
            <a href="#" class="weui-grid js_grid weui-grid-for-four">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                    <span class="weui-badge" style="position: absolute;top: -.5em;right: -1.2em;">0</span>
                </div>
                <p class="weui-grid__label">
                    美容
                </p>
            </a>
        </div>
    </div>
</body>

</html>