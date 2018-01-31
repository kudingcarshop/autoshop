/**320x480 1rem=10px*/
function autoAdjust(){
	var $doc=$(window);
	var width = $doc.width();
	var height = $doc.height();

	width = Math.min(width,height);
	
	if(width < 320){
		width = 320;
	}else if(width >750){
		width = 750;
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