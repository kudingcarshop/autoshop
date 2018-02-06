$(function() {
    $(document).ready(function() {
        formValidator();
        $('#provienceBox').hide();
        $('#textBox').hide();
    });
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
                                console.log(number);
                                // var pattern = /^(([\u4e00-\u9fa5][a-zA-Z]|[\u4e00-\u9fa5]{2}\d{2}|[\u4e00-\u9fa5]{2}[a-zA-Z])[-]?|([wW][Jj][\u4e00-\u9fa5]{1}[-]?)|([a-zA-Z]{2}))([A-Za-z0-9]{5}|[DdFf][A-HJ-NP-Za-hj-np-z0-9][0-9]{4}|[0-9]{5}[DdFf])$/;
                                var pattern = /^([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领][a-zA-Z](([DF](?![a-zA-Z0-9]*[IO])[0-9]{4})|([0-9]{5}[DF])))|([冀豫云辽黑湘皖鲁苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼渝京津沪新京军空海北沈兰济南广成使领A-Z]{1}[a-zA-Z0-9]{5}[a-zA-Z0-9挂学警港澳]{1})$/;
                                return pattern.test(number);
                            }
                        }
                    }
                },
//                // type
//                type: {
//                    message: '预约类型不能为空',
//                    validators: {
//                        //非空
//                        notEmpty: {
//                            message: '预约类型不能为空'
//                        }
//                    }
//                },
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
            if (typeof($item.attr('name')) !== "undefined") {
                data[$item.attr('name')] = $item.val();
            }
        });
        console.log(data);
        return data;
    }
})