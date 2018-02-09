<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/plate_number_common.css" rel="stylesheet" media="screen">
    <link href="${path}/css/kuding/plate_number.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/bootstrapvalidator/css/bootstrapValidator.css" rel="stylesheet" media="screen">
    <link href="${path}/vendor/jquery-weui/1.2.0/css/jquery-weui.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/jquery/jquery-3.2.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/jquery/jquery.form.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/lib/layer/layer.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/vendor/kuding/garage/plate_number.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/vendor/kuding/garage/car_book_action.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrapvalidator/js/bootstrapValidator.js"></script>
    <title>预约</title>
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

<body>
    <div class="toolbar">
        <div class="toolbar-inner">
            <a href="/web-autoshop/garage/vehicle/serviceCenter" class="back-button">
                < 返回 </a>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>预约</h2>
                </div>
                <form id="mainForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">车辆</label>
                        <div class="col-lg-5">
                            <div class="carLicenseMain">
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
                            <input type="hidden" class="form-control" id="plateNumber" name="plateNumber"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">预约项目</label>
                        <div class="col-lg-5">
                            <select class="form-control selectpicker" name="type" onchange="bizChange(this)">
                                <c:forEach items="${requestScope.biz_category}" var="biz">
                                    <option value='${biz.categoryCode}_${biz.code}'>${biz.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">到店时间</label>
                        <div class="col-lg-5">
                            <div class="input-group date form_datetime" data-date="" data-date-format="dd MM yyyy - HH:ii p" data-link-field="bookTime">
                                <input class="form-control" type="text" value="" name="bookTime" id="bookTime" readonly />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                            </div>
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
    function bizChange(obj) {
        var $value = obj.options[obj.selectedIndex].value;
    }
    function onPlateNumChanged() {
        console.log($('#plateNumber').val());
    }
    $('#bookTime').change(function() {
        $('#mainForm').data('bootstrapValidator').updateStatus("bookTime", "NOT_VALIDATED", null);
        $('#mainForm').data('bootstrapValidator').validateField('bookTime');
    })
    $('.form_datetime').datetimepicker({
        language: 'zh',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'fr',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'fr',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
    //override
    function onKeyBoardBoxHide() {
        
    }
    </script>
</body>

</html>