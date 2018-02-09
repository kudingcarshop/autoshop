<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../path.jsp"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet">
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
    <title>首页</title>
    <style>
    body,
    html {
        height: 100%;
        -webkit-tap-highlight-color: transparent;
    }

    body {
        overflow:hidden;
    }

    iframe {
        height: 100%;
        width: 100%;
        margin-bottom: 50px;
    }

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

    .placeholder {
        margin: 5px;
        background-color: #ebebeb;
        height: 5em;
        width: 90%;
        line-height: 5em;
        text-align: center;
        color: #cfcfcf;
    }

    .weui-grid-for-four {
        width: 25%;
    }
    .block {
        height: 50px;
        width: 100%;
    }
    </style>
</head>

<body ontouchstart>
    <div class="weui-tab">
        <div class="weui-tab__bd">
            <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
                <iframe id="personal_frame" runat="server" frameborder="no" border="0" scrolling="auto" allowtransparency="yes" src="/web-autoshop/garage/vehicle/serviceCenter"></iframe>
            </div>
            <div id="tab2" class="weui-tab__bd-item">
                <h1>Mall</h1>
            </div>
            <div id="tab3" class="weui-tab__bd-item">
                <iframe id="personal_frame" runat="server" frameborder="no" border="0" scrolling="auto" allowtransparency="yes" src="${path}/jsp/sys/user/user_center.html"></iframe>
            </div>
        </div>
        <div class="block"></div>
        <div class="weui-tabbar">
            <a href="#tab1" class="weui-tabbar__item weui-bar__item--on">
                <div class="weui-tabbar__icon">
                    <img src="${path}/img/icon_nav_button.png" alt="">
                </div>
                <p class="weui-tabbar__label">我的服务</p>
            </a>
            <a href="#tab2" class="weui-tabbar__item">
                <div class="weui-tabbar__icon">
                    <img src="${path}/img/icon_nav_button.png" alt="">
                </div>
                <p class="weui-tabbar__label">积分商城</p>
            </a>
            <a href="#tab3" class="weui-tabbar__item">
                <div class="weui-tabbar__icon">
                    <img src="${path}/img/icon_nav_button.png" alt="">
                </div>
                <p class="weui-tabbar__label">个人中心</p>
            </a>
        </div>
    </div>
    <script type="text/javascript">
    $(function() {
        FastClick.attach(document.body);
    });
    </script>
</body>

</html>