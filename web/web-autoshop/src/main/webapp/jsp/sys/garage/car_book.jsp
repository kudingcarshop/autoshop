<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${path}/vendor/jquery-weui/1.2.0/lib/weui.min.css" rel="stylesheet">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet">
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/jquery-2.1.4.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/lib/fastclick.js"></script>
    <script src="${path}/vendor/jquery-weui/1.2.0/js/jquery-weui.js"></script>
    <title>预约</title>
    <style type="text/css">
    body,
    html {
        height: 100%;
        -webkit-tap-highlight-color: transparent;
    }

    .top_toolbar {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        font-size: .85rem;
        line-height: 1.5;
        color: #d6d6d6;
        background: #2e2e2e;
    }

    .top_toolbar .back-button {
        position: absolute;
        left: 0;
        box-sizing: border-box;
        height: 2.2rem;
        line-height: 2.2rem;
        color: #d6d6d6;
        padding: 0 .5rem;
    }

    .province {
        display: inline-block;
        width: 1.3rem;
        height: 1.3rem;
        border: solid;
        border-width: 1px;
        border-radius: 5px;
        text-align: center;
        font-size: 0.8rem;
    }

    .province_selector {
        display: inline-block;
        flex-direction: row;
        width: 1.3rem;
        height: 1.3rem;
        border: solid;
        border-width: 1px;
        border-radius: 5px;
        text-align: center;
        font-size: 0.8rem;
        margin-top: 0.2rem;
        margin-bottom: 0.2rem;
    }

    .city {
        display: inline-block;
        width: 1.3rem;
        height: 1.3rem;
        border: solid;
        border-width: 1px;
        border-radius: 5px;
        text-align: center;
        font-size: 0.8rem;
    }

    .city_selector {
        display: inline-block;
        flex-direction: row;
        width: 1.3rem;
        height: 1.3rem;
        border: solid;
        border-width: 1px;
        border-radius: 5px;
        text-align: center;
        font-size: 0.8rem;
        margin-top: 0.2rem;
        margin-bottom: 0.2rem;
    }

    .number_input {
        text-transform: uppercase;
        ime-mode:inactive;
    }

    .container {
        position: absolute;
        top: 3.5em;
        width: 100%;
    }

    .weui-popup__container {
        position: fixed;
        bottom: 50px;
        left: 0;
        right: 0;
        height: 0;
        width: 100%;
        height: 100%;
        z-index: 10;
    }
    </style>
</head>
<!-- 京 津 冀 晋 蒙 辽 吉 黑 沪 苏 浙 皖 闽 赣 鲁 豫 鄂 湘 粤 桂 琼 渝 川 贵 黔 云 滇 藏 陕 甘 青 宁 新 其他 -->

<body>
    <div class="toolbar top_toolbar">
        <div class="toolbar-inner">
            <a href="/web-autoshop/garage/vehicle/serviceCenter" class="back-button">
                < 返回 </a>
        </div>
    </div>
    <div class="container">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <a href="javascript:;" class="province open-popup" id="province_input" data-target="#province_popup">粤</a>
                    <a href="javascript:;" class="city open-popup" id="city_input" data-target="#city_popup">A</a>
                </div>
                <div class="weui-cell__bd" style="margin-left: 1.1rem;">
                    <input class="weui-input number_input" id="number_input" placeholder="请输入车牌" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                </div>
            </div>
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label for="" class="weui-label">预约类型</label>
                </div>
                <div class="weui-cell__bd">
                    <select class="weui-select" id="type_input" name="type" onchange="bizChange(this)">
                        <c:forEach items="${requestScope.biz_category}" var="biz">
                            <option value='${biz.categoryCode}_${biz.code}'>${biz.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label for="time_input" class="weui-label">到店时间</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" id="time_input" name='bookTime' type="text" value="2018-01-01 12:00">
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="btn_submit">确定</a>
        </div>
    </div>
    <div id="province_popup" class='weui-popup__container popup-bottom'>
        <div class="weui-popup__overlay"></div>
        <div class="weui-popup__modal">
            <div class="toolbar">
                <div class="toolbar-inner">
                    <a href="javascript:;" class="picker-button close-popup">关闭</a>
                    <h1 class="title">标题</h1>
                </div>
            </div>
            <div class="modal-content" id="province_list">
                <a class="province_selector">京</a>
                <a class="province_selector">津</a>
                <a class="province_selector">冀</a>
                <a class="province_selector">晋</a>
                <a class="province_selector">蒙</a>
                <a class="province_selector">辽</a>
                <a class="province_selector">吉</a>
                <a class="province_selector">黑</a>
                <a class="province_selector">沪</a>
                <a class="province_selector">苏</a>
                <a class="province_selector">浙</a>
                <a class="province_selector">皖</a>
                <a class="province_selector">闽</a>
                <a class="province_selector">赣</a>
                <a class="province_selector">鲁</a>
                <a class="province_selector">豫</a>
                <a class="province_selector">鄂</a>
                <a class="province_selector">湘</a>
                <a class="province_selector">粤</a>
                <a class="province_selector">桂</a>
                <a class="province_selector">琼</a>
                <a class="province_selector">渝</a>
                <a class="province_selector">川</a>
                <a class="province_selector">贵</a>
                <a class="province_selector">黔</a>
                <a class="province_selector">云</a>
                <a class="province_selector">滇</a>
                <a class="province_selector">藏</a>
                <a class="province_selector">陕</a>
                <a class="province_selector">甘</a>
                <a class="province_selector">青</a>
                <a class="province_selector">宁</a>
                <a class="province_selector">新</a>
                <a class="province_selector" style="width: 2rem;">其他</a>
            </div>
        </div>
    </div>
    <div id="city_popup" class='weui-popup__container popup-bottom'>
        <div class="weui-popup__overlay"></div>
        <div class="weui-popup__modal">
            <div class="toolbar">
                <div class="toolbar-inner">
                    <a href="javascript:;" class="picker-button close-popup">关闭</a>
                    <h1 class="title">标题</h1>
                </div>
            </div>
            <div class="modal-content" id="city_list">
                <a class="city_selector">A</a>
                <a class="city_selector">B</a>
                <a class="city_selector">C</a>
                <a class="city_selector">D</a>
                <a class="city_selector">E</a>
                <a class="city_selector">F</a>
                <a class="city_selector">G</a>
                <a class="city_selector">H</a>
                <a class="city_selector">I</a>
                <a class="city_selector">J</a>
                <a class="city_selector">K</a>
                <a class="city_selector">L</a>
                <a class="city_selector">M</a>
                <a class="city_selector">N</a>
                <a class="city_selector">O</a>
                <a class="city_selector">P</a>
                <a class="city_selector">Q</a>
                <a class="city_selector">R</a>
                <a class="city_selector">S</a>
                <a class="city_selector">T</a>
                <a class="city_selector">U</a>
                <a class="city_selector">V</a>
                <a class="city_selector">W</a>
                <a class="city_selector">X</a>
                <a class="city_selector">Y</a>
                <a class="city_selector">Z</a>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var $selected_province_text;
    var $last_selected_province;
    var $selected_city_text;
    var $last_selected_city;
    var $select_type;
    $(document).ready(function() {
        initFirstProvice();
        initFirstCity();
    });
    $(document).on("open", ".weui-popup-modal", function() {
        console.log("open popup");
    }).on("close", ".weui-popup-modal", function() {
        console.log($selected_province_text);
    });

    function initFirstProvice() {
        $('#province_list').find('a').each(function() {
            if ($(this).text() == $('.province').text()) {
                $last_selected_province = $(this);
                $selected_province_text = $(this).text();
                $(this).css("backgroundColor", "#000");
            } else {
                $(this).css("backgroundColor", "transparent");
            }
            $(this).click(function() {
                $selected_province_text = $(this).text();
                $(".province").html($selected_province_text);
                $(this).css("backgroundColor", "#000");
                $last_selected_province.css("backgroundColor", "transparent");
                $last_selected_province = $(this);
            });

        });
    }

    function initFirstCity() {
        $('#city_list').find('a').each(function() {
            if ($(this).text() == $('.city').text()) {
                $last_selected_city = $(this);
                $selected_city_text = $(this).text();
                $(this).css("backgroundColor", "#000");
            } else {
                $(this).css("backgroundColor", "transparent");
            }
            $(this).click(function() {
                $selected_city_text = $(this).text();
                $('.city').html($selected_city_text);
                $(this).css("backgroundColor", "#000");
                $last_selected_city.css("backgroundColor", "transparent");
                $last_selected_city = $(this);
            });
        });
    }

    function bizChange(obj) {
        $select_type = obj.options[obj.selectedIndex].value;
    }
    $("#time_input").datetimePicker({
        onChange: function(picker, values, displayValues) {
            console.log(values);
        }
    });
    $("#btn_submit").click(function() {
        var number_input = $('#number_input').val();
        var car_number = $('#province_input').text() + $('#city_input').text() + number_input;
        var pattern = /^([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领][a-zA-Z](([DF](?![a-zA-Z0-9]*[IO])[0-9]{4})|([0-9]{5}[DF])))|([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领A-Z]{1}[a-zA-Z0-9]{5}[a-zA-Z0-9挂学警港澳]{1})$/;
        if (!pattern.test(car_number)) {
            $.toptip('请输入正确车牌号');
        } else {
            $.ajax( //ajax方式提交表单  
                {
                    url: '/web-autoshop/garage/car_book/add',
                    type: 'post',
                    data: preData(),
                    dataType: 'json',
                    beforeSubmit: function() {},
                    success: function(data) {
                        if (data.flag === 1) {
                            location.href = "/web-autoshop/garage/car_book/user/list";
                        } else {
                            alert(data.message);
                        }
                    },
                    clearForm: false, //禁止清楚表单  
                    resetForm: false //禁止重置表单  
                });

        }
    });

    function preData() {
        var data = {};
        var number_input = $('#number_input').val();
        var car_number = $('#province_input').text() + $('#city_input').text() + number_input;
        data['plateNumber'] = car_number;
        data['type'] = document.getElementById('type_input').options[document.getElementById('type_input').selectedIndex].value;
        data['bookTime'] = $('#time_input').val() + ":00";
        // $(':input').each(function() {
        //     var $item = $(this);
        //     if (typeof($item.attr('name')) !== "undefined") {
        //         data[$item.attr('name')] = $item.val();
        //     }
        // });
        console.log(data);
        return data;
    }
    </script>
</body>

</html>