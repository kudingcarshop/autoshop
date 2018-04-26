// 判断浮点型    
$.validator.addMethod("isFloat", function (value, element) {  
    return this.optional(element) || /^[-\+]?\d+(\.\d+)?$/.test(value);  
}, "只能包含数字、小数点等字符");  