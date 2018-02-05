/**320x480 1rem=100px chrome 允许的最小字体大小未12px*/
function autoAdjust(){
	var $doc=$(window);
	var width = $doc.width();
	if(width < 320){
		width = 320;
	}else if(width >750){
		width = 750;
	}
	
	var rem = width/3.2;
	$('html').css('font-size',rem+'px');
}

$(function(){
	autoAdjust();	
	$(window).resize(function(){
		autoAdjust();
	});
});