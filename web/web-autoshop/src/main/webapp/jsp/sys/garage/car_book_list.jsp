<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/vendor/jquery/jquery-3.2.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js" charset="UTF-8"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
    <title>预约列表</title>
    <style type="text/css">
    body {
        background-color: #d6d6d6;
    }

    .toolbar {
        position: fixed;
        z-index: 1;
        width: 100%;
        font-size: .85rem;
        line-height: 1.5;
        color: #d6d6d6;
        background: #2e2e2e;
        top: 0;
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

    .list-container {
        padding-top: 2.5rem;
        height: 100%;
        width: 100%;
    }

    .list-item {
        margin-bottom: .5rem;
        background-color: #fff;
        border: none;
        border-radius: 0;
        -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    }

    .list-body {
        padding: 5px;
    }



    .list-item-header {
        font-size: 24px;
    }

    .list-item-content .list-item-footer-text .list-item-footer-button {
        font-size: 18px;
    }

    .list-footer {
        width: 100%;
        color: #3d4145;
        border-top: 1px solid #ddd;
    }

    .list-item-footer-text {
        display: inline;
        position: relative;
        left: 0;
        height: 2.2rem;
        line-height: 2.2rem;
        padding: 0 .5rem;
    }

    .list-item-footer-button {
        position: absolute;
        display: inline;
        right: 0;
        height: 2.2rem;
        line-height: 2.2rem;
        padding: 0 .5rem;
    }

    .default-button {
        color: #04BE02;
    }

    .warning-button {
        color: #ff0000;
    }

    .disable-button {
        color: #bdbdbd;
    }
    </style>
</head>

<body>
    <div class="toolbar">
        <div class="toolbar-inner">
            <a href="/web-autoshop/garage/vehicle/serviceCenter" class="back-button">
                < 返回
            </a>
            <a href="javascript:;" class="picker-button open-popup" data-target="#half" style="margin-right: 1em">筛选</a>
        </div>
    </div>
    <div class="list-container">
        <div class="car-list" id="car-list">
        </div>
    </div>
    <div id="half" class='weui-popup__container popup-bottom'>
        <div class="weui-popup__overlay"></div>
        <div class="weui-popup__modal">
            <div class="toolbar">
                <div class="toolbar-inner">
                    <a href="javascript:;" class="picker-button close-popup">关闭</a>
                </div>
            </div>
            <div class="modal-content">
                <div class="weui-grids">
                    <a onclick="filterList(-1)" class="weui-grid js_grid" data-id="dialog">
                        <div class="weui-grid__icon">
                            <img src="${path}/img/icon_nav_new.png" alt="">
                        </div>
                        <p class="weui-grid__label">
                            显示全部
                        </p>
                    </a>
                    <a onclick="filterList(0)" class="weui-grid js_grid" data-id="progress">
                        <div class="weui-grid__icon">
                            <img src="${path}/img/icon_nav_new.png" alt="">
                        </div>
                        <p class="weui-grid__label">
                            已预约
                        </p>
                    </a>
                    <a onclick="filterList(3)" class="weui-grid js_grid" data-id="msg">
                        <div class="weui-grid__icon">
                            <img src="${path}/img/icon_nav_new.png" alt="">
                        </div>
                        <p class="weui-grid__label">
                            已取消
                        </p>
                    </a>
                    <a onclick="filterList(2)" class="weui-grid js_grid" data-id="dialog">
                        <div class="weui-grid__icon">
                            <img src="${path}/img/icon_nav_new.png" alt="">
                        </div>
                        <p class="weui-grid__label">
                            已接车
                        </p>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var $listData = ${book_list};
    var $isUser = ${isUser};
    var $carListView = document.getElementById('car-list');

    Date.prototype.format = function(format) {
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        };
        if (/(y+)/.test(format) || /(Y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    };

    function setDiv(item) {
        var time = new Date(item.bookTime).format("yyyy-MM-dd hh:mm:ss");
        var div = '<div class="list-item">';
        div += '<div class="list-body">';
        div += '<div class="list-item-header">';
        div += item.plateNumber;
        div += '</div>'; //list-item-header
        div += '<div class="list-item-content">';
        div += '服务类型:&nbsp;'
        div += item.typeName;
        div += '</div>'; //list-item-content
        div += '<div class="list-item-content">';
        div += '到店时间:&nbsp;'
        div += time;
        div += '</div>'; //list-item-content        
        div += '</div>';
        div += '<div class="list-footer">';
        div += '<div class="list-item-footer-text">';
        if (item.state == 0) {
            div += '已预约';
        } else if (item.state == 2) {
            div += '已接车';
        } else if (item.state == 3) {
            div += '已取消';
        }
        div += '</div>'; //list-item-footer-text
        if ($isUser) {
            if (item.state == 0) {
                div += '<a class="list-item-footer-button warning-button" onclick="cancelClick(\'' + item.id + '\')">取消</a>'
            } else {
                div += '<a class="list-item-footer-button disable-button">取消</a>'
            }
        } else {
            if (item.state == 0) {
                div += '<a class="list-item-footer-button default-button" onclick="checkinClick(\'' + item.id + '\')">接车</a>'
            } else {
                div += '<a class="list-item-footer-button disable-button">接车</a>'
            }
        }
        div += '</div>'; //list-footer
        div += '</div>';
        return div;
    }

    function getnoApplicationData(data) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += setDiv(data[i]);
        }
        $carListView.innerHTML = html;
    }

    window.onload = getnoApplicationData($listData);

    function preData($id) {
        var data = {};
        data['id'] = $id;
        console.log(data);
        return data;
    }

    function filterList(state) {
        var html = '';
        for (var i = 0; i < $listData.length; i++) {
            var data = $listData[i];
            if (state == -1) {
                html += setDiv($listData[i]);
            } else if (data.state == state) {
                html += setDiv($listData[i]);
            }
        }
        $carListView.innerHTML = html;
    }

    function cancelClick(obj) {
        var $data = preData(obj);
        console.log($data);
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/car_book/cancel',
                type: 'post',
                data: $data,
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    location.reload();
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            }
        );
    }

    function checkinClick(obj) {
        var $data = preData(obj);
        console.log($data);
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/car_book/checkin',
                type: 'post',
                data: $data,
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    location.reload();
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            }
        );
    }
    </script>
</body>

</html>