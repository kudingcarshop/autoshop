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

function cropImg(canvasId, url,cw,ch) {
	console.log("cw="+cw+" ch=" + ch);
	var canvas = document.getElementById(canvasId);
	ctx = canvas.getContext('2d');
	canvas.width = cw;//使用CSS设置canvas宽、高会自动拉伸，通过代码重新设置
	canvas.height = ch;
	var img = new Image();
	img.src = url;
	console.log("img.src = " + img.src);
	img.onload = function() {
		var w = img.width;
		var h = img.height;
		ctx.clearRect(0, 0, cw, ch);//清除画布
		ctx.drawImage(img, 0, 0, w, h, 0, 0, cw, ch);
	}
}