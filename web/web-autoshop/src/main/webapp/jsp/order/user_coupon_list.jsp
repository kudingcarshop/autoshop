<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/coupon_list.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/coupon_nav.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/art-template/lib/template-web.js"></script>
    <title>我的优惠券</title>
</head>

<body ontouchstart>
    <div class="coupon_nav">
        <a href="javascript:;" class="coupon_nav_item cur" id="unused_tab"><span id="unused_span" >待使用</span></a>
        <a href="javascript:;" class="coupon_nav_item" id="used_tab"><span id="used_span">已使用</span></a>
        <a href="javascript:;" class="coupon_nav_item" id="overdue_tab"><span id="overdue_span">已过期</span></a>
    </div>
    <div class="coupon_sec_body" id="coupon_sec_body">
    </div>
    <script type="text/javascript" src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
    <script type="text/javascript">
    $(function() {
        FastClick.attach(document.body);
    });
    </script>
    <script type="text/javascript" src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
    <script type="text/javascript">
    var unusedList = ${unusedList};
    var usedList = ${usedList};
    var overdueList = ${overdueList};
    $(document).ready(function() {
        $('#unused_span').text('待使用 ' + unusedList.length);
        $('#used_span').text('已使用 ' + usedList.length);
        $('#overdue_span').text('已过期 ' + overdueList.length);
        setListView(unusedList);
    });
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
    $('.coupon_nav').find('a').each(function() {
        $(this).click(function() {
            if ($(this).hasClass('cur')) {
                return;
            } else {
                $('.coupon_nav').find('a').each(function() {
                    if ($(this).hasClass('cur')) {
                        $(this).removeClass('cur')
                    }
                });
                $(this).addClass('cur');
                if ($(this).attr('id') == 'unused_tab') {
                    setListView(unusedList);
                } else if ($(this).attr('id') == 'used_tab') {
                    setListView(usedList);
                } else if ($(this).attr('id') == 'overdue_tab') {
                    setListView(overdueList);
                }
            }
        });
    });


    function parseList(list) {
        var dataList = {
            list: []
        };
        for (var i = 0; i < list.length; i++) {
            var item = list[i];
            var startDate = list[i].couponStartDate;
            var endDate = list[i].couponEndDate;
            item.couponStartDate = new Date(startDate).format("yyyy.MM.dd");
            item.couponEndDate = new Date(endDate).format("yyyy.MM.dd");
            dataList.list.push(list[i]);
        }
        return dataList;
    }

    function setListView(list) {
        var html_unused = $.get('${path}/jsp/order/user_coupon_list_template.html',
            function(data) {
                var render = template.compile(data);
                var str = render(parseList(list));
                document.getElementById("coupon_sec_body").innerHTML = str;
            });
    }
    </script>
</body>

</html>