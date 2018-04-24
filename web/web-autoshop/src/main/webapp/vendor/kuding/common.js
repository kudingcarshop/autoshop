/**
 * 获取图片地址
 * @param file
 * @returns
 */
function getObjectURL(file) {
	var url = null;
	if (window.createObjcectURL != undefined) {
		url = window.createOjcectURL(file);//standar
	} else if (window.URL != undefined) {//firefox
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) {//webkit/chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

/**
 * 缩放图片
 * @param canvasId
 * @param url
 * @param cw
 * @param ch
 * @returns
 */
function cropImg(canvasId, url,cw,ch) {
	var canvas = document.getElementById(canvasId);
	ctx = canvas.getContext('2d');
	canvas.width = cw;//使用CSS设置canvas宽、高会自动拉伸，通过代码重新设置
	canvas.height = ch;
	var img = new Image();
	img.src = url;
	img.onload = function() {
		var w = img.width;
		var h = img.height;
		ctx.clearRect(0, 0, cw, ch);//清除画布
		ctx.drawImage(img, 0, 0, w, h, 0, 0, cw, ch);
	}
}

/**
 * 显示提示信息
 * @param containerSelector 提示信息显示容器选择器
 * @param message 提示信息
 * @param flag 标志位，0表示警告信息 1及其它表示正常提醒
 * @returns
 */
function showMessage(containerSelector, message, flag) {
	setTimeout(function() {
		if (containerSelector && message && message != '') {
			var $msg;
			if($('#msg').length == 0){
				$msg = $('<div id="msg"></div>');
			}else{
				$msg = $('#msg');
				$msg.removeClass();
			}
			if (flag && flag == '0') {
				$msg.addClass('kd-danger');
			} else {
				$msg.addClass('kd-success');
			}
			$msg.text(message);
			$(containerSelector).prepend($msg);//显示提示信息
			dissMessage();//自动隐藏
		}
	},500);
}

/**
 *隐藏提示信息
 */
function dissMessage() {
	setTimeout(function() {
		$('#msg').hide(800);
	}, 2000);
}
