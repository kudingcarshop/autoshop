/**320x480 1rem=100px chrome 允许的最小字体大小未12px*/
function basicRem(){
	var $doc=$(window);
	var width = $doc.width();
	if(width < 320){
		width = 320;
	}else if(width >750){
		width = 750;
	}
	var rem = width/3.2;
	return rem;
}

function autoAdjust(){
	$('html').css('font-size',basicRem()+'px');
}

$(function(){
	autoAdjust();	
	$(window).resize(function(){
		autoAdjust();
	});
});
