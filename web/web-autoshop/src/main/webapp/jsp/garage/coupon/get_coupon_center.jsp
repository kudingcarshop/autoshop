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
    <script type="text/javascript" src="${path}/vendor/art-template/lib/template-web.js"></script>
    <title>领券中心</title>
</head>

<body ontouchstart>
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
    var couponCenterList = {
        list: []
    };
    var parseList = ${couponCenterList};

    function parseData() {
        console.log(parseList);
        for (var i = 0; i < parseList.length; i++) {
            var item = parseList[i];
            var startDate = parseList[i].couponStartDate;
            var endDate = parseList[i].couponEndDate;
            item.couponStartDate = new Date(startDate).format("yyyy.MM.dd");
            item.couponEndDate = new Date(endDate).format("yyyy.MM.dd");
            couponCenterList.list.push(parseList[i]);
        }
    }

    var html_overdue = $.get('${path}/jsp/garage/coupon/get_coupon_center_template.html',
        function(data) {
            var render = template.compile(data);
            parseData();
            var str = render(couponCenterList);
            document.getElementById("coupon_sec_body").innerHTML = str;
            setCouponClick();
        });


    function setCouponClick() {
        // $('#coupon_sec_body').find('a').each(function() {
        //     $(this).click(function() {
        //         var $id = $(this).attr("id");
        //         var coupon = parseList[parseInt($id)];
        //         if (coupon.userGetStatus = 0 && coupon.couponRemainPercent > 0) {
        //             console.log("getCouponAction");
        //             getCouponAction(coupon);
        //         }
        //         console.log(coupon);
        //     });
        // })
        for (var i = 0; i < parseList.length; i++) {
            var item = parseList[i];
            var nodeId = '#' + item.id;
            $(nodeId).click(function() {
                var $index = $(this).attr('index');
                var coupon = parseList[parseInt($index)];
                if (coupon.userGetStatus == 0 && coupon.couponAmount > 0) {
                    getCouponAction(coupon);
                } else if (coupon.userGetStatus == 1) {
                    $.toast("您已领取过该优惠券", "cancel", function(toast) {
                    });
                } else if (coupon.couponRemainPercent <= 0) {
                    $.toast("该优惠券已被抢完", "cancel", function(toast) {
                    });
                }
            });
        }
    }

    function getCouponAction(coupon) {
        $.showLoading();
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/coupon/coupon_center/getAction',
                type: 'post',
                data: preData(coupon),
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    $.hideLoading();
                    if (data.flag === 1) {
                        location.reload();
                        $.toast("优惠券领取成功", function() {
                            console.log('close');
                        });
                    } else {
                        $.toast("优惠券领取失败", "cancel", function(toast) {
                            console.log(toast);
                        });
                    }

                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            });
    }

    function preData(coupon) {
        var data = {};
        data['couponId'] = coupon.id;
        console.log(data)
        return data;
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
    </script>
</body>

</html>
