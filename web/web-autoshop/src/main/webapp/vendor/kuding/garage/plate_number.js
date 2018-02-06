$(function() {
    var num = 6; //需要输入的车牌数
    $('body').click(function() {
        if ($(event.target).closest('.carLicenseMain').length === 0) {
            $('#provienceBox').hide();
            $('#textBox').hide();
            $('#mainForm').data('bootstrapValidator').updateStatus("plateNumber[]", "NOT_VALIDATED", null);
            $('#mainForm').data('bootstrapValidator').validateField('plateNumber[]');
            onKeyBoardBoxHide();
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
            //$('#mainForm').data('bootstrapValidator').updateStatus("plateNumber[]", "NOT_VALIDATED", null);
            event.stopPropagation();
        })

    function tranToPlateNumber() {
        var plateNumber = "";
        $('.carLicenseMain').find('li').each(function() {
            plateNumber += $(this).text();
        })
        $('#plateNumber').val(plateNumber);
        onPlateNumChanged();
    }
})