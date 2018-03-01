<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/demos/css/demos.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/jquery/jquery-3.2.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/jquery/jquery.form.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.3&key=ab94b5326624fb29059bfce2b970b3f8"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.3&key=ab94b5326624fb29059bfce2b970b3f8&plugin=AMap.Geocoder"></script>
    <title>选取救援位置</title>
    <style>
    body {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        margin: 0;
    }

    iframe {
        width: 100%;
        height: 100%;
    }
    </style>
</head>

<body>
    <div id="container" style="display: none;"></div>
    <iframe id="loc_map"></iframe>
    <script type="text/javascript">
    var $loc = '116.3972,39.9696';
    var $pick_loc = [];
    var $pick_name;
    var $pick_address;
    $(document).ready(function() {
        getLocation();

    });

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

    function getLocation() {
        var a = GetRequest();
        var index_1 = a['location'];
        if (typeof(index_1) !== "undefined") {
            $loc = index_1;
            document.getElementById('loc_map').src = 'https://m.amap.com/picker/?center=' + $loc + '&key=ab94b5326624fb29059bfce2b970b3f8';
            showMap();
        } else {
            getLocationFromMap();
        }
    }

    function showMap() {
        var iframe = document.getElementById('loc_map').contentWindow;
        document.getElementById('loc_map').onload = function() {
            iframe.postMessage('hello', 'https://m.amap.com/picker/');
        };
        window.addEventListener("message", function(e) {
            $pick_loc = e.data.location.split(',');
            $pick_name = e.data.name;
            $pick_address = e.data.address;
            getFormattedAddress();
            // window.location.href = "/web-autoshop/garage/vehicle/showSos?address=" + e.data.address + "&" + "name=" + e.data.name;
            // alert('您选择了:' + e.data.name + ',' + e.data.location)
        }, false);
    }

    function getLocationFromMap() {
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
        //$('#address-input').html(data.formattedAddress);
        $loc = data.position.getLng() + ',' + data.position.getLat();
        document.getElementById('loc_map').src = 'https://m.amap.com/picker/?center=' + $loc + '&key=ab94b5326624fb29059bfce2b970b3f8';
        showMap();
        // document.getElementById('tip').innerHTML = str.join('<br>');
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('loc_map').src = 'https://m.amap.com/picker/?center=' + $loc + '&key=ab94b5326624fb29059bfce2b970b3f8';
        showMap();
        // document.getElementById('tip').innerHTML = '定位失败';
    }

    function getFormattedAddress() {
        var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
        });
        geocoder.getAddress($pick_loc, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });
    }

    function geocoder_CallBack(data) {
        var address = data.regeocode.addressComponent.province + data.regeocode.addressComponent.city + $pick_address; //返回地址描述
        console.log('callback'+address);
        window.location.href = "/web-autoshop/garage/vehicle/showSos?address=" +address + "&" + "name=" + $pick_name + "&" + "loc=" + $pick_loc;
        // document.getElementById("result").innerHTML = address;
    }
    </script>
</body>

</html>
