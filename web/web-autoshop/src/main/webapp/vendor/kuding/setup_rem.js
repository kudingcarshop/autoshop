/**320x480 1rem=10px*/
function autoAdjust(){
	var $doc=$('html');
	var width = $doc.width();
	if(width < 320){
		width = 320;
	}else if(width > 640){
		width = 640;
	}
	var rem = width/32;
	$('html').css('font-size',rem+'px');
}

$(function(){
	autoAdjust();	
	$(window).resize(function(){
		autoAdjust();
	});
});