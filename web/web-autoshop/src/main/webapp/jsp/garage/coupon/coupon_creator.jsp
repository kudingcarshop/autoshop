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
    <script type="text/javascript" src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
    <script type="text/javascript" src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
    <title>创建优惠券</title>
    <style type="text/css">
    body,
    html {
        height: 100%;
        -webkit-tap-highlight-color: transparent;
    }

    footer {
        text-align: center;
        font-size: 14px;
        padding: 20px;
    }

    footer a {
        color: #999;
        text-decoration: none;
    }

    .weui-label {
        width: 160px;
        text-align: right;
    }

    #create_btn {
        margin-left: 30px;
        margin-right: 30px;
        margin-top: 50px;
    }
    </style>
</head>

<body ontouchstart>
    <div class="weui-cells__title">优惠券信息</div>
    <div class="weui-cells">
        <div class="weui-cell weui-cell_select" style="padding-left: 15px;">
            <div class="weui-cell__hd">
                <label for="couponType" class="weui-label">优惠券类型：</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="couponType" name="couponType">
                    <option selected="" value="0">请选择</option>
                    <option value="1">满减优惠券</option>
                    <option value="2">折扣券</option>
                </select>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">优惠券名称：</label>
            </div>
            <div class="weui-cell__bd" style="padding-left: 15px;">
                <input class="weui-input" id="couponName" name="couponName" placeholder="30字以内" type="text" value="">
            </div>
        </div>
        <div class="weui-cell weui-cell_select" style="padding-left: 15px;">
            <div class="weui-cell__hd">
                <label for="couponForType" class="weui-label">适用类型：</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="couponForType" name="couponForType">
                    <option selected="" value="0">请选择</option>
                    <option value="1">通用类型</option>
                    <option value="2">商城</option>
                    <option value="3">车行服务</option>
                </select>
            </div>
        </div>
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <label for="" class="weui-label">选择限制商品/服务：</label>
            </div>
            <div class="weui-cell__ft">无限制</div>
        </a>
        <div class="weui-cell" id="couponDiscount_cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">折扣：</label>
            </div>
            <div class="weui-cell__bd" style="padding-left: 15px;">
                <input class="weui-input" id="couponDiscount" name="couponDiscount" placeholder="如85折，输入0.85" type="text" value="">
            </div>
        </div>
        <div class="weui-cell" id="couponPrice_cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">面额：</label>
            </div>
            <div class="weui-cell__bd" style="padding-left: 15px;">
                <input class="weui-input" id="couponPrice" name="couponPrice" placeholder="请输入金额" type="text" value="">
            </div>
        </div>
        <div class="weui-cell weui-cell_select" style="padding-left: 15px;">
            <div class="weui-cell__hd">
                <label for="conditionType" class="weui-label">折扣条件类型：</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="conditionType" name="conditionType">
                    <option selected="" value="0">请选择</option>
                    <option value="1">购买价格条件</option>
                    <option value="2">购买数量条件</option>
                </select>
            </div>
        </div>
        <div class="weui-cell" id="couponConditionCount_cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">购买数量条件：</label>
            </div>
            <div class="weui-cell__bd" style="padding-left: 15px;">
                <input class="weui-input" id="couponConditionCount" name="couponConditionCount" placeholder="请输数量" type="tel" value="">
            </div>
        </div>
        <div class="weui-cell" id="couponCondition_cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">满减条件：</label>
            </div>
            <div class="weui-cell__bd" style="padding-left: 15px;">
                <input class="weui-input" id="couponCondition" name="couponCondition" placeholder="请输入金额" type="text" value="">
            </div>
        </div>
    </div>
    <div class="weui-cells__title">有效期</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="couponStartDate" class="weui-label">开始日期：</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" id="couponStartDate" name="couponStartDate" type="text">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="couponEndDate" class="weui-label">失效日期：</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" id="couponEndDate" name="couponEndDate" type="text">
            </div>
        </div>
    </div>
    <div class="weui-cells__title">数量</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">发行量：</label>
            </div>
            <div class="weui-cell__bd" style="padding-left: 15px;">
                <input class="weui-input" id="couponAmount" name="couponAmount" placeholder="上限100000" type="tel" value="">
            </div>
        </div>
    </div>
    <a href="javascript:;" class="weui-btn weui-btn_primary" id="create_btn">创建优惠券</a>
    <script type="text/javascript">
    var COUPON_TYPE_NONE = '0';
    var COUPON_TYPE_CASH = '1';
    var COUPON_TYPE_DISCOUNT = '2';
    var COUPON_TYPE_CONDITION_NONE = '0';
    var COUPON_TYPE_CONDITION_PRICE = '1';
    var COUPON_TYPE_CONDITION_COUNT = '2';
    var COUPON_FOR_TYPE_NONE = '0;'
    var COUPON_FOR_TYPE_ALL = '1';
    var COUPON_FOR_TYPE_MALL = '2';
    var COUPON_FOR_TYPE_GARAGE = '3';
    $(function() {
        FastClick.attach(document.body);
    });
    </script>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#couponDiscount_cell').hide();
        $('#couponPrice_cell').hide();
        $('#couponCondition_cell').hide();
        $('#couponConditionCount_cell').hide();
    });
    $("#couponStartDate").calendar({
        onChange: function(p, values, displayValues) {
            console.log(values, displayValues);
        }
    });
    $("#couponEndDate").calendar({
        onChange: function(p, values, displayValues) {
            console.log(values, displayValues);
        }
    });
    $("#couponType").change(function() {
        var selected = $(this).children('option:selected').val();
        if (selected == COUPON_TYPE_NONE) {
            $.toptip('请输入优惠券类型');
            $('#couponDiscount_cell').hide();
            $('#couponPrice_cell').hide();
        } else if (selected == COUPON_TYPE_CASH) {
            $('#couponDiscount_cell').hide();
            $('#couponPrice_cell').show();
        } else if (selected == COUPON_TYPE_DISCOUNT) {
            $('#couponDiscount_cell').show();
            $('#couponPrice_cell').hide();
        }
    });

    $("#conditionType").change(function() {
        var selected = $(this).children('option:selected').val();
        if (selected == COUPON_TYPE_CONDITION_NONE) {
            $.toptip('请选择条件类型');
            $('#couponCondition_cell').hide();
            $('#couponConditionCount_cell').hide();
        } else if (selected == COUPON_TYPE_CONDITION_PRICE) {
            $('#couponCondition_cell').show();
            $('#couponConditionCount_cell').hide();
        } else if (selected == COUPON_TYPE_CONDITION_COUNT) {
            $('#couponCondition_cell').hide();
            $('#couponConditionCount_cell').show();
        }
    });

    $("#create_btn").click(function() {
        var priceTest = /^\d+(\.\d+)?$/;
        var numberTest = /^\+?[1-9][0-9]*$/;
        if ($('#couponType option:selected').val() == COUPON_TYPE_NONE) {
            $.toptip('请选择优惠券类型');
            return;
        }

        if ($('#couponName').val().replace(/\s+/g, '') == '') {
            $.toptip('请输入优惠券名称');
            return;
        }

        if ($('#couponName').val().trim().length > 30) {
            $.toptip('请输入优惠券仅限30字');
            return;
        }

        if ($('#couponForType option:selected').val() == COUPON_FOR_TYPE_NONE) {
            $.toptip('请选择适用类型');
            return;
        }

        if ($('#couponType option:selected').val() == COUPON_TYPE_CASH) {
            // 满减优惠券类型
            var price = $('#couponPrice').val().replace(/\s+/g, '');
            if (price == '') {
                $.toptip('请输入面额');
                return;
            }
            if (!priceTest.test(price)) {
                $.toptip('请输入正确面额');
                return;
            }
        } else if ($('#couponType option:selected').val() == COUPON_TYPE_DISCOUNT) {
            // 折扣优惠券类型
            var discount = $('#couponDiscount').val().replace(/\s+/g, '');
            if (discount == '') {
                $.toptip('请输入折扣');
                return;
            }
            if (!numberTest.test(discount) || discount > 100 || discount <= 0) {
                $.toptip('请输入正确折扣');
                return;
            }
        }


        if ($('#conditionType option:selected').val() == COUPON_TYPE_CONDITION_NONE) {
            $.toptip('请选择优惠条件类型');
            return;
        } else if ($('#conditionType option:selected').val() == COUPON_TYPE_CONDITION_PRICE) {
            // 价格条件
            var condition = $('#couponCondition').val().replace(/\s+/g, '');
            if (condition == '') {
                $.toptip('请输入价格条件');
                return;
            }
            if (!priceTest.test(condition)) {
                $.toptip('请输入正确价格条件');
                return;
            }

        } else if ($('#conditionType option:selected').val() == COUPON_TYPE_CONDITION_COUNT) {
            // 数量条件
            var count = $('#couponConditionCount').val().replace(/\s+/g, '');
            if (count == '') {
                $.toptip('请输入数量条件');
                return;
            }
            if (!numberTest.test(count) || count < 0) {
                $.toptip('请输入正确数量条件');
                return
            }
        }

        if (!dateCheck()) {
            return;
        }
        if (!amountCheck()) {
            return;
        }

        createCoupon();
    });

    function createCoupon() {
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/couponFactory/creator',
                type: 'post',
                data: preData(),
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    alert(data.message);
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            });
    }

    function preData() {
        var data = {};
        var couponType = $('#couponType option:selected').val();
        var conditionType = $('#conditionType option:selected').val();
        data['couponType'] = couponType;
        data['couponName'] = $('#couponName').val();

        if (couponType == COUPON_TYPE_CASH) {
            data['couponPrice'] = $('#couponPrice').val();
            data['couponDiscount'] = '100';
        } else if (couponType == COUPON_TYPE_DISCOUNT) {
            data['couponPrice'] = '0';
            data['couponDiscount'] = $('#couponDiscount').val();
        }
        if (conditionType == COUPON_TYPE_CONDITION_PRICE) {
            data['couponCondition'] = $('#couponCondition').val();
            data['couponConditionCount'] = '0';
        } else if (conditionType == COUPON_TYPE_CONDITION_COUNT) {
            data['couponCondition'] = '0';
            data['couponConditionCount'] = $('#couponConditionCount').val();
        }
        data['couponForType'] = $('#couponForType option:selected').val();
        data['couponForSku'] = '0';
        data['couponStartDate'] = new Date(Date.parse($('#couponStartDate').val())).format("yyyy-MM-dd");
        data['couponEndDate'] = new Date(Date.parse($('#couponEndDate').val())).format("yyyy-MM-dd");
        data['couponAmount'] = $('#couponAmount').val();
        console.log(data);
        return data;
    }


    function dateCheck() {
        var start = $('#couponStartDate').val().replace(/\s+/g, '');
        var end = $('#couponEndDate').val().replace(/\s+/g, '');
        var currentDate = Date.parse(new Date());
        if (start == '') {
            $.toptip('请选择开始日期');
            return false;
        } else if (end == '') {
            $.toptip('请选择结束日期');
            return false;
        } else {
            var startDate = Date.parse(start);
            var endDate = Date.parse(end);
            if (endDate <= currentDate) {
                $.toptip('结束日期必须大于今天');
                return false;
            } else if (endDate == startDate) {
                $.toptip('结束日期不能与开始日期相同');
                return false;
            } else if (endDate < startDate) {
                $.toptip('结束日期不能小于开始日期');
                return false;
            }
        }
        return true;
    }

    function amountCheck() {
        var amount = $('#couponAmount').val().replace(/\s+/g, '');
        var number = /^\+?[1-9][0-9]*$/;
        if (amount == '') {
            $.toptip('请输入发行量');
            return false;
        } else if (!number.test(amount)) {
            $.toptip('请输入正确发行量');
            return false;
        } else if (amount > 1000000) {
            $.toptip('发行量不得超过100000');
            return false;
        }
        return true;
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