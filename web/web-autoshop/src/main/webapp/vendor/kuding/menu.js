$(function() {
			$(".left-menu a").on('click',function(event){
				var $sp = $(this).children("i");
				if($sp && $sp.length > 0){
					$(this).parent().children(".left-submenu").slideToggle(150);
				}
			});
		});