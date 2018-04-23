<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/coupon_list.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/coupon_nav.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/order_coupon.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/art-template/lib/template-web.js"></script>
    <title>选择优惠券</title>
    <style type="text/css">
    body,
    html {
        height: 100%;
        -webkit-tap-highlight-color: transparent;
        background-color: #FAFAFA;
    }
    </style>
</head>

<body ontouchstart>
    <div class="coupon_nav">
        <a href="javascript:;" class="coupon_nav_item cur" id="can_use_tab"><span id="can_use_span" >可用优惠券</span></a>
        <a href="javascript:;" class="coupon_nav_item" id="cannot_use_tab"><span id="cannot_use_span">不可用优惠券</span></a>
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
    var canUseList = ${canUseList};
    var cannotUseList = ${cannotUseList};

    $(document).ready(function() {
        initTab();
        setListView(canUseList, true);
    });

    function setListView(list, canUse) {
        var html_unused = $.get('${path}/jsp/order/order_coupon_list_template.html',
            function(data) {
                var render = template.compile(data);
                var str = render(parseList(list, canUse));
                document.getElementById("coupon_sec_body").innerHTML = str;
                initCouponItem();
                initCouponClick();
            });
    }

    function initTab() {
        $('#can_use_span').text('可用优惠券 ' + canUseList.length);
        $('#cannot_use_span').text('不可用优惠券 ' + cannotUseList.length);
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
                    if ($(this).attr('id') == 'can_use_tab') {
                        setListView(canUseList, true);
                    } else if ($(this).attr('id') == 'cannot_use_tab') {
                        setListView(cannotUseList, false);
                    }
                }
            });
        });
    }


    function parseList(list, canUse) {
        var dataList = {
            canUseList: canUse,
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

    function initCouponItem() {
        var hasDefaultSelected = false;
        for (var i = 0; i < canUseList.length; i++) {
            if (canUseList[i].defaultSelected) {
                hasDefaultSelected = true;
            }
        }
        if (hasDefaultSelected == false) {
            for (var i = 0; i < canUseList.length; i++) {
                var id = canUseList[i].id;
                var $couponItem = $('#' + id);
                $couponItem.removeClass('selected');
                var $couponVoucher = $('#' + id + '_voucher');
                $couponVoucher.removeClass('type_disabled');
                var $couponFooter = $('#' + id + '_footer');
                $couponFooter.hide();
            }
        } else {
            for (var i = 0; i < canUseList.length; i++) {
                if (canUseList[i].defaultSelected) {
                    var id = canUseList[i].id;
                    var $couponItem = $('#' + id);
                    $couponItem.addClass('selected');
                    var $couponVoucher = $('#' + id + '_voucher');
                    $couponVoucher.removeClass('type_disabled');
                    var $couponFooter = $('#' + id + '_footer');
                    $couponFooter.hide();
                } else {
                    var id = canUseList[i].id;
                    var $couponItem = $('#' + id);
                    $couponItem.removeClass('selected');
                    var $couponVoucher = $('#' + id + '_voucher');
                    $couponVoucher.addClass('type_disabled');
                    var $couponFooter = $('#' + id + '_footer');
                    $couponFooter.show();
                }
            }
        }
    }

    function initCouponClick() {
        for (var i = 0; i < canUseList.length; i++) {
            var $couponItem = $('#' + canUseList[i].id);
            $couponItem.click(function() {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                    setCouponCancelSelect();
                } else {
                    for (var i = 0; i < canUseList.length; i++) {
                        var $c = $('#' + canUseList[i].id);
                        if ($c.hasClass('selected')) {
                            $.toast('请先取消已勾选优惠券再进行选择', 'text');
                            return;
                        }
                    }
                    $(this).addClass('selected');
                    setCouponSelect($(this).attr('id'));
                }
            });
        }
    }



    function setCouponCancelSelect() {
        for (var i = 0; i < canUseList.length; i++) {
            var id = canUseList[i].id;
            var $couponVoucher = $('#' + id + '_voucher');
            $couponVoucher.removeClass('type_disabled');
            var $couponFooter = $('#' + id + '_footer');
            $couponFooter.hide();
        }
    }

    function setCouponSelect(selectId) {
        for (var i = 0; i < canUseList.length; i++) {
            var id = canUseList[i].id;
            if (selectId == id) {
                continue;
            } else {
                var $couponVoucher = $('#' + id + '_voucher');
                $couponVoucher.addClass('type_disabled');
                var $couponFooter = $('#' + id + '_footer');
                $couponFooter.show();
            }
        }
    }
    </script>
</body>

</html>