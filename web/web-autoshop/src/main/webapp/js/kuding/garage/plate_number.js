$(function() {

    $(document).ready(function() {
        formValidator();
        $('#provienceBox').hide();
        $('#textBox').hide();
    });

    var num = 6; //需要输入的车牌数
    $('body').click(function() {
        if ($(event.target).closest('.carLicenseMain').length === 0) {
            $('#provienceBox').hide();
            $('#textBox').hide();
            $('#mainForm').data('bootstrapValidator').updateStatus("plateNumber[]", "NOT_VALIDATED", null);
            $('#mainForm').data('bootstrapValidator').validateField('plateNumber[]');
        }
        event.stopPropagation();
    })


    $('.carLicenseMain li').click(function() {
        var index = getIndex();
        if (index == 0) {
            if (!$("#provienceBox").is(':visible')) {
                $('#provienceBox').show();
                $('#textBox').hide();
            }
        } else {
            if (!$("#textBox").is(':visible')) {
                $('#textBox').show();
                $('#provienceBox').hide();
            }
        }
        event.stopPropagation();
    })

    //切换键盘
    $('.changeContentBtn').click(function() {
        if ($(this).html() == 'ABC') {
            $('#textBox').show();
            $('#provienceBox').hide();
        } else {
            $('#textBox').hide();
            $('#provienceBox').show();
        }
        event.stopPropagation();
    })
    // 新能源点击事件
    $('.xinnengyuan').click(function() {
        num = 7;
        $(this).removeClass('xinnengyuan');
        event.stopPropagation();
    })
    //获取当前车牌数字索引
    function getIndex() {
        var index = 0;
        $('.carLicenseMain ul li').each(function() {
            var reg = new RegExp('active');
            if (reg.test($(this).attr('class'))) {
                index = $(this).index();
            }
        })
        return index;
    }
    //自定义键盘处理函数
    function keyboard(num, self) {
        var index = getIndex();
        if (index <= num) {
            if (index == num) {
                $('.carLicenseMain ul li.active').html($(self).html());
            } else {
                $('.carLicenseMain ul li.active').html($(self).html())
                    .removeClass('active').next().addClass('active');
            }
            $('#textBox').show();
            $('#provienceBox').hide();
            tranToPlateNumber();
        }
    }
    //省份键盘点击事件
    $('#provienceBox ul li:not(.other)').click(function() {
        var self = this;
        keyboard(num, self);
        event.stopPropagation();
    })
    //文本键盘点击事件
    $('#textBox ul li:not(.other)').click(function() {
        var self = this;
        keyboard(num, self);
        event.stopPropagation();
    })
    //回退按钮点击事件
    $('.deleteBtn').click(
        function() {
            var index = getIndex();
            if (index == num) {
                if ($('.carLicenseMain ul li.active').html() != '') {
                    $('.carLicenseMain ul li.active').html('');
                } else {
                    if (index == 7) {
                        $('.carLicenseMain ul li:last-of-type').addClass(
                            'xinnengyuan');
                        num = 6;
                    }
                    $('.carLicenseMain ul li.active').removeClass('active')
                        .prev().addClass('active').html('');
                }

            } else if (index < num && index > 1) {
                $('.carLicenseMain ul li.active').removeClass('active')
                    .prev().addClass('active').html('');
            } else if (index == 1) {
                $('.carLicenseMain ul li.active').removeClass('active')
                    .prev().addClass('active').html('省');
            }
            if (index <= 1) {
                if (!$("#provienceBox").is(':visible')) {
                    $('#provienceBox').show();
                    $('#textBox').hide();
                }
            }
            tranToPlateNumber();
            $('#mainForm').data('bootstrapValidator').updateStatus("plateNumber[]", "NOT_VALIDATED", null);
            event.stopPropagation();
        })

    //提交按钮点击事件
    $('#button_submit').click(function() {
        // $("#mainForm").data('bootstrapValidator').destroy();
        // $('#mainForm').data('bootstrapValidator', null);
        // formValidator();
        // preData();

        var data = $(mainForm).data('bootstrapValidator');
        if (data) {
            // 修复记忆的组件不验证
            data.validate();
            if (!data.isValid()) {
                console.log("button_submit validator failed");
                return false;
            }
        }
        $('#mainForm').ajaxSubmit( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/book/add',
                type: 'post',
                data: preData(),
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    alert("success");
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            });
    })

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
                                var pattern = /^(([\u4e00-\u9fa5][a-zA-Z]|[\u4e00-\u9fa5]{2}\d{2}|[\u4e00-\u9fa5]{2}[a-zA-Z])[-]?|([wW][Jj][\u4e00-\u9fa5]{1}[-]?)|([a-zA-Z]{2}))([A-Za-z0-9]{5}|[DdFf][A-HJ-NP-Za-hj-np-z0-9][0-9]{4}|[0-9]{5}[DdFf])$/;
                                //var pattern = '/^([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领][a-zA-Z](([DF](?![a-zA-Z0-9]*[IO])[0-9]{4})|([0-9]{5}[DF])))|([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领A-Z]{1}[a-zA-Z0-9]{5}[a-zA-Z0-9挂学警港澳]{1})$/';
                                return pattern.test(number);
                            }
                        }
                    }
                },
                // type
                type: {
                    message: '预约类型不能为空',
                    validators: {
                        //非空
                        notEmpty: {
                            message: '预约类型不能为空'
                        }
                    }
                },
                // bookTime
                bookTime: {
                    message: '日期不能为空',
                    validators: {
                        //                    //非空
                        // notEmpty: {
                        //     message: '日期不能为空'
                        // }
                        callback: {
                            message: '日期不能为空',
                            callback: function(value, validator) {
                                var time = $('#bookTime').val();
                                return time.length > 0;
                            }
                        }
                    }
                }
            }
        });
    }


    function preData() {
        var data = {};
        $(':input').each(function() {
            var $item = $(this);
            if (typeof($item.attr('name')) != "undefined") {
                console.log($item.attr('name'));
                data[$item.attr('name')] = $item.val();
            }
        });
        console.log(data);
        return data;
    }

    function tranToPlateNumber() {
        var plateNumber = "";
        $('.carLicenseMain').find('li').each(function() {
            plateNumber += $(this).text();
        })
        $('#plateNumber').val(plateNumber);
    }
})