// 判断浮点型    
$.validator.addMethod("isFloat", function (value, element) {  
    return this.optional(element) || /^[-\+]?\d+(\.\d+)?$/.test(value);  
}, "只能包含数字、小数点等字符");  

//判断是否合法的车牌号码
$.validator.addMethod("isPlateNumber", function (value, element) {  
    return this.optional(element) || /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/.test(value);  
}, "请输入合法的车牌号码"); 