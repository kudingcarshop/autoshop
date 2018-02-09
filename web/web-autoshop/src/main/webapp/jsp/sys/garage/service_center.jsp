<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet">
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/js/swiper.min.js"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>服务中心</title>
    <style>
    .swiper-container {
        height: 20%;
    }

    .swiper-container img {
        display: block;
        height: 20%;
    }
    .service-block {
        background-color: #fff;
        border: none;
        border-radius: 0;
        -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    }
    .block-header {
        width: 100%;
        color: #3d4145;
        height: 4rem;
        border-bottom: 1px solid #ddd;
    }

    .block-header-text {
        display: inline;
        position: relative;
        left: 0;
        height: 4rem;
        line-height: 4rem;
        padding: 0 .5rem;
        margin-left: 1rem;
    }

    .block-header-end {
        position: fixed;
        display: inline;
        right: 0;
        height: 4rem;
        line-height: 4rem;
        padding: 0 .5rem;
        color: #8e8e8e;
    }
    </style>
</head>

<body>
    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="${path}/img/swiper-1.jpg" alt="First slide" style="margin: 0 auto;">
                <div class="carousel-caption">广告１</div>
            </div>
            <div class="item">
                <img src="${path}/img/swiper-2.jpg" alt="Second slide" style="margin: 0 auto;">
                <div class="carousel-caption">广告２</div>
            </div>
            <div class="item">
                <img src="${path}/img/swiper-3.jpg" alt="Third slide" style="margin: 0 auto;">
                <div class="carousel-caption">广告３</div>
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
    <div class="service-block">
        <div class="block-header">
            <div class="block-header-text">我的服务</div>
            <a href="/web-autoshop/garage/car_book/user/list" class="block-header-end">查看预约历史></a>
        </div>
        <div class="weui-grids">
            <a href="/web-autoshop/garage/car_book" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                </div>
                <p class="weui-grid__label">
                    预约美容
                </p>
            </a>
            <a href="/web-autoshop/garage/car_book" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                </div>
                <p class="weui-grid__label">
                    预约保养
                </p>
            </a>
            <a href="/web-autoshop/garage/car_book" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                </div>
                <p class="weui-grid__label">
                    预约维修
                </p>
            </a>
            <a href="/web-autoshop/garage/vehicle/showSos" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                </div>
                <p class="weui-grid__label">
                    救援服务
                </p>
            </a>
            <a href="#" class="weui-grid js_grid">
                <div class="weui-grid__icon" style="position: relative;">
                    <img src="${path}/img/icon_nav_new.png" alt="">
                </div>
                <p class="weui-grid__label">
                    车行信息
                </p>
            </a>
        </div>
    </div>
    <script type="text/javascript">
    $(document).ready(function() {
        $("#myCarousel").carousel('cycle');
    });
    </script>
</body>

</html>