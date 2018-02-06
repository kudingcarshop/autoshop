<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/bootstrapvalidator/css/bootstrapValidator.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/plate_number_common.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/plate_number.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/jquery/jquery-3.2.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/jquery/jquery.form.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/lib/layer/layer.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrapvalidator/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="${path}/lib/layer/layer.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/vendor/kuding/garage/plate_number.js" charset="utf-8"></script>
    <title>接车</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>接车</h2>
                </div>
                <form id="mainForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">车辆</label>
                        <div class="col-lg-5">
                            <div class="carLicenseMain" data-container="body" data-toggle="popover" data-placement="bottom">
                                <ul>
                                    <li class="active" id="number_province" name="plateNumber[]">省</li>
                                    <li id="number_1" name="plateNumber[]"></li>
                                    <li id="number_2" name="plateNumber[]"></li>
                                    <li id="number_3" name="plateNumber[]"></li>
                                    <li id="number_4" name="plateNumber[]"></li>
                                    <li id="number_5" name="plateNumber[]"></li>
                                    <li id="number_6" name="plateNumber[]"></li>
                                    <li id="number_7" name="plateNumber[]" class="xinnengyuan"></li>
                                </ul>
                            </div>
                            <input class="form-control" type="hidden" id="plateNumber" name="plateNumber" />
                            <p class="col-lg-offset-3 text-primary" name="bookTime" id="bookTime">预约: 无</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">项目</label>
                        <div class="col-lg-5">
                            <select class="form-control selectpicker" name="type" onchange="bizChange(this)">
                                <c:forEach items="${requestScope.biz_category}" var="biz">
                                    <option value='${biz.categoryCode}_${biz.code}'>${biz.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button id="button_submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
            <div id="keyboardBox">
                <div class="provienceBox" id="provienceBox">
                    <ul>
                        <li>京</li>
                        <li>津</li>
                        <li>渝</li>
                        <li>沪</li>
                        <li>冀</li>
                        <li>晋</li>
                        <li>辽</li>
                        <li>吉</li>
                        <li>黑</li>
                        <li>苏</li>
                    </ul>
                    <ul>
                        <li>浙</li>
                        <li>皖</li>
                        <li>闽</li>
                        <li>赣</li>
                        <li>鲁</li>
                        <li>豫</li>
                        <li>鄂</li>
                        <li>湘</li>
                        <li>粤</li>
                        <li>琼</li>
                    </ul>
                    <ul>
                        <li>川</li>
                        <li>贵</li>
                        <li>云</li>
                        <li>陕</li>
                        <li>甘</li>
                        <li>青</li>
                        <li>蒙</li>
                        <li>桂</li>
                        <li>宁</li>
                        <li>新</li>
                    </ul>
                    <ul>
                        <li class="changeContentBtn other">ABC</li>
                        <li>藏</li>
                        <li>使</li>
                        <li>领</li>
                        <li>警</li>
                        <li>学</li>
                        <li>港</li>
                        <li>澳</li>
                        <li class="deleteBtn other" align="center"><img src="${path}/img/backDeleteImg.jpg" /></li>
                    </ul>
                </div>
                <div class="textBox" id="textBox">
                    <ul>
                        <li>1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>0</li>
                    </ul>
                    <ul>
                        <li>Q</li>
                        <li>W</li>
                        <li>E</li>
                        <li>R</li>
                        <li>T</li>
                        <li>Y</li>
                        <li>U</li>
                        <li>I</li>
                        <li>O</li>
                        <li>P</li>
                    </ul>
                    <ul>
                        <li>A</li>
                        <li>S</li>
                        <li>D</li>
                        <li>F</li>
                        <li>G</li>
                        <li>H</li>
                        <li>J</li>
                        <li>K</li>
                        <li>L</li>
                    </ul>
                    <ul>
                        <li class="changeContentBtn other">返回</li>
                        <li>Z</li>
                        <li>X</li>
                        <li>C</li>
                        <li>V</li>
                        <li>B</li>
                        <li>N</li>
                        <li>M</li>
                        <li class="deleteBtn other"><img src="${path}/img/backDeleteImg.jpg" /></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var $suggestList = [];
    $(document).ready(function() {
        formValidator();
        $('#provienceBox').hide();
        $('#textBox').hide();
        var $autoCompleteInput = $('#plateNumber');
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/checkin/suggest_plate_number',
                type: 'post',
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(json) {
                    for (var i = 0; i < json.length; i++) {
                        var obj = {};
                        obj.id = json[i].id;
                        // obj.label = json[i].plateNumber;
                        obj.plateNumber = json[i].plateNumber;
                        // list[i] = json[i].plateNumber;
                        //list.push(json[i].plateNumber);
                        $suggestList.push(obj);
                    }
                    plateNumberSuggest($suggestList);
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            }
        );
    });

    function plateNumberSuggest(dataList) {
        $(".carLicenseMain").popover({
            html: true,
            placement: 'bottom',
            content: function() {
                return content(dataList);
            }
        });

        function content(dataList) {
            var dataString = "<div class='btn-group-vertical' id='suggestGroup' style='max-height:150px;overflow-y:scroll'>";
            var input = $('#plateNumber').val();
            for (var i = 0; i < dataList.length; i++) {
                var plateNumber = dataList[i].plateNumber;
                if (plateNumber.indexOf(input) != -1) {
                    dataString += "<button type='button' class='btn btn-default btn-block' onclick=\"suggestClick('"+plateNumber+"')\">"+plateNumber+"</button>";
                }
            }
            dataString += "</div>";
            var data = $(dataString);

            return data;
        }
    }

    function suggestClick(number) {
        var numbers = number.split("");
        var count = 0;
        $('.carLicenseMain').find('li').each(function() {
            if (count < numbers.length) {
                $(this).html(numbers[count]);
                $(this).removeClass('active');
                count++;
                if (count >= numbers.length) {
                    $(this).addClass('active');
                }
            }
        }) 
        $('#plateNumber').val(number);
        onPlateNumChanged();
    }


    $('#plateNumber').click(function() {
        var length = $('#plateNumber').length;
        if (length <= 1) {
            $('#textBox').hide();
            $('#provienceBox').show();
        } else {
            $('#textBox').show();
            $('#provienceBox').hide();
        }
    });

    function bizChange(obj) {
        var $value = obj.options[obj.selectedIndex].value;
    }

    function onKeyBoardBoxHide() {
        $('.carLicenseMain').popover('hide');
    }

    function onPlateNumChanged() {
        var input = $('#plateNumber').val();
        var flag = 0;
        for (var i = 0; i < $suggestList.length; i++) {
            var plateNumber = $suggestList[i].plateNumber;
            if (plateNumber.indexOf(input) != -1) {
                flag = 1;
            }
        }
        var $popoverItem = document.getElementById("suggestGroup");
        if (flag == 1) {
            if ($popoverItem === null) {
                $('.carLicenseMain').popover('show');
            } else {
                updateSuggestContent();
            }
        } else {
            if ($popoverItem !== null) {
                $('.carLicenseMain').popover('hide');
            }
        }
    }

    function updateSuggestContent() {
        var $popoverItem = document.getElementById("suggestGroup");
        $popoverItem.innerHTML = "";
        var input = $('#plateNumber').val();
        var flag = 0;
        var dataString = "";
        for (var i = 0; i < $suggestList.length; i++) {
            var plateNumber = $suggestList[i].plateNumber;
            if (plateNumber.indexOf(input) != -1) {
                flag = 1;
                dataString += "<button type='button' class='btn btn-default btn-block' onclick=\"suggestClick('"+plateNumber+"')\">"+plateNumber+"</button>";
            }
        }
        if (flag == 1) {
            $popoverItem.innerHTML = dataString;
        }
    }

    function checkBookTime() {
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/car_book/query_time',
                type: 'post',
                data: preData(),
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    if (data.flag == 0) {
                        $('#bookTime').html("预约: 无");
                    } else {
                        $('#bookTime').html("预约: " + data.message);
                    }
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            }
        );
    }

    function preData() {
        var data = {};
        $(':input').each(function() {
            var $item = $(this);
            if (typeof($item.attr('name')) !== "undefined") {
                data[$item.attr('name')] = $item.val();
            }
        });
        return data;
    }

    function formValidator() {
        $("#mainForm").bootstrapValidator({
            message: 'This value is not valid',
            //定义未通过验证的状态图标
            feedbackIcons: { /*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                validating: 'glyphicon glyphicon-refresh'
            },
            //字段验证
            fields: {
                // plateNumber
                'plateNumber[]': {
                    validators: {
                        callback: {
                            message: '请输入正确车牌',
                            callback: function(value, validator) {
                                var number = $('#plateNumber').val();
                                // var pattern = /^(([\u4e00-\u9fa5][a-zA-Z]|[\u4e00-\u9fa5]{2}\d{2}|[\u4e00-\u9fa5]{2}[a-zA-Z])[-]?|([wW][Jj][\u4e00-\u9fa5]{1}[-]?)|([a-zA-Z]{2}))([A-Za-z0-9]{5}|[DdFf][A-HJ-NP-Za-hj-np-z0-9][0-9]{4}|[0-9]{5}[DdFf])$/;
                                var pattern = /^([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领][a-zA-Z](([DF](?![a-zA-Z0-9]*[IO])[0-9]{4})|([0-9]{5}[DF])))|([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领A-Z]{1}[a-zA-Z0-9]{5}[a-zA-Z0-9挂学警港澳]{1})$/;
                                var result = pattern.test(number);
                                if (result) {
                                    checkBookTime();
                                } else {
                                    $('#bookTime').html("预约: 无");
                                }
                                return pattern.test(number);
                            }
                        }
                    }
                },
                'plateNumber': {
                    validators: {
                        callback: {
                            message: '请输入正确车牌',
                            callback: function(value, validator) {
                                var number = $('#plateNumber').val();
                                var pattern = /^(([\u4e00-\u9fa5][a-zA-Z]|[\u4e00-\u9fa5]{2}\d{2}|[\u4e00-\u9fa5]{2}[a-zA-Z])[-]?|([wW][Jj][\u4e00-\u9fa5]{1}[-]?)|([a-zA-Z]{2}))([A-Za-z0-9]{5}|[DdFf][A-HJ-NP-Za-hj-np-z0-9][0-9]{4}|[0-9]{5}[DdFf])$/;
                                //var pattern = '/^([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领][a-zA-Z](([DF](?![a-zA-Z0-9]*[IO])[0-9]{4})|([0-9]{5}[DF])))|([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领A-Z]{1}[a-zA-Z0-9]{5}[a-zA-Z0-9挂学警港澳]{1})$/';
                                var result = pattern.test(number);
                                if (result) {
                                    checkBookTime();
                                } else {
                                    $('#bookTime').html("预约: 无");
                                }
                                return pattern.test(number);
                            }
                        }
                    }
                }
            }
        });
    }
    </script>
</body>

</html>