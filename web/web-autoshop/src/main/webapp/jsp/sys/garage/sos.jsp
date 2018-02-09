<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/demos/css/demos.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/jquery/jquery-3.2.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/jquery/jquery.form.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.3&key=ab94b5326624fb29059bfce2b970b3f8"></script>
    <title>救援服务</title>
    <style type="text/css">
    .toolbar {
        position: fixed;
        z-index: 1;
        width: 100%;
        font-size: .85rem;
        line-height: 1.5;
        color: #d6d6d6;
        background: #2e2e2e;
    }

    .toolbar .back-button {
        position: absolute;
        left: 0;
        box-sizing: border-box;
        height: 2.2rem;
        line-height: 2.2rem;
        color: #d6d6d6;
        z-index: 1;
        padding: 0 .5rem;
    }

    .container {
        padding-top: 3.5em;
    }
    </style>
</head>

<body ontouchstart>
    <div class="toolbar">
        <div class="toolbar-inner">
            <a href="/web-autoshop/garage/vehicle/serviceCenter" class="back-button">
                < 返回 </a>
        </div>
    </div>
    <div class="container">
        <header class='demos-header'>
            <h1 class="demos-title">道路救援</h1>
        </header>
        <div class="weui-cells weui-cells_form">
            <div id="container" style="display: none;"></div>
            <div class="weui-cell" id='name_cell'>
                <div class="weui-cell__bd" id="loc_name"></div>
            </div>
            <div class="weui-cell" id='address_cell'>
                <div class="weui-cell__bd" id="loc_address"></div>
            </div>
            <div class="weui-btn-area">
                <a id='loading_btn' class="weui-btn weui-btn_default weui-btn_loading">正在定位...</a>
                <a id="locBtn" class="weui-btn weui-btn_plain-primary">手动选取救援地址</a>
                <!-- <a href="http://apis.map.qq.com/tools/locpicker?search=1&type=0&backurl=http://3gimg.qq.com/lightmap/components/locationPicker2/back.html&key=DLVBZ-II3KU-JLBVD-4JO4J-NDCU7-CBFSA&referer=kuding_garage" class="weui-btn weui-btn_plain-primary">点击获取当前地址</a> -->
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var $location = '116.407526,39.90403';
    $(document).ready(function() {
        getLocationDefault();
        $('#locBtn').hide();
        $('#loading_btn').show();
        $('#name_cell').hide();
        $('#address_cell').hide();
        putAddress();
    });

    $('#locBtn').click(function() {
        window.location.href = "/web-autoshop/garage/vehicle/showAddressPikcker?location=" + $location;
    });

    function getLocationDefault() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition, showError, {
                // 指示浏览器获取高精度的位置，默认为false
                enableHighAccuracy: true,
                // 指定获取地理位置的超时时间，默认不限时，单位为毫秒
                timeout: 5000,
                // 最长有效期，在重复获取地理位置时，此参数指定多久再次获取位置。
                maximumAge: 3000
            });
        } else {
            alert("浏览器不支持地理定位。");
        }
    }

    function showPosition(data) {
        console.log(data);
    }

    function showError(data) {
        console.log(data);
    }

    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

    function putAddress() {
        var a = GetRequest();
        var index_1 = a['address'];
        var index_2 = a['name'];
        var index_3 = a['loc'];
        if (typeof(index_3) !== "undefined") {
            $location = index_3;
        }
        if (typeof(index_1) !== "undefined" || typeof(index_2) !== "undefined") {
            if (typeof(index_1) !== "undefined") {
                $('#address_cell').show();
                $('#loc_address').html(index_1);
            } else {
                $('#address_cell').hide();
                $('#loc_address').html('');
            }
            if (typeof(index_2) !== "undefined") {
                $('#name_cell').show();
                $('#loc_name').html(index_2);
            } else {
                $('#name_cell').hide();
                $('#loc_name').html('');
            }
            $('#locBtn').show();
            $('#loading_btn').hide();
        } else {
            $('#loc_name').html('');
            $('#name_cell').hide();
            $('#loc_address').html('');
            $('#address_cell').hide();
            getLocation();
        }
    }

    function getLocation() {
        /***************************************
        由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
        ***************************************/
        var map, geolocation;
        //加载地图，调用浏览器定位服务
        map = new AMap.Map('container', {
            resizeEnable: true
        });
        map.plugin('AMap.Geolocation', function() {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true, //是否使用高精度定位，默认:true
                timeout: 10000, //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true, //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition: 'RB'
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', onComplete); //返回定位信息
            AMap.event.addListener(geolocation, 'error', onError); //返回定位出错信息
        });
    }

    //解析定位结果
    function onComplete(data) {
        // var str = ['定位成功'];
        // str.push('经度：' + data.position.getLng());
        // str.push('纬度：' + data.position.getLat());
        // if (data.accuracy) {
        //     str.push('精度：' + data.accuracy + ' 米');
        // } //如为IP精确定位结果则没有精度信息
        // str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        $('#name_cell').hide();
        $('#address_cell').show();
        $('#loc_name').html('');
        $('#loc_address').html(data.formattedAddress);
        $location = data.position.getLng() + ',' + data.position.getLat();
        // document.getElementById('tip').innerHTML = str.join('<br>');
        console.log(data);
        $('#locBtn').show();
        $('#loading_btn').hide();
    }
    //解析定位错误信息
    function onError(data) {
        alert(data.message);
        $('#name_cell').show();
        $('#loc_name').html('自动定位失败');
        $('#address_cell').hide();
        $('#loc_address').html('');
        // document.getElementById('tip').innerHTML = '定位失败';
        $('#locBtn').show();
        $('#loading_btn').hide();
    }
    </script>
</body>

</html>