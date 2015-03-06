var i=-1; 
var offset = 4000; 
var timer = null;
function autoroll(){
	n = $(".flash_u li").length-1;
	i++;
	if(i > n){
		i = 0;
	}
	slide(i);
	timer = window.setTimeout(autoroll, offset);
}
function slide(i){
	$(".flash_u li").eq(i).addClass("crumb").siblings().removeClass("crumb");
	$(".news_flash dd").eq(i).addClass("block").siblings().removeClass("block");
}
function hookThumb(){    
	$(".flash_u li").hover(function () {
		if (timer) {
			clearTimeout(timer);
		i = $(this).prevAll().length;
		 slide(i); 
		}
	},function () {
  		timer = window.setTimeout(autoroll, offset);  
		this.blur();            
		return false;
	}); 
}
$(document).ready(function(){/*
    $('.pricediv em').each(function(i){
        $(this).mouseover(function(){
            var t = $(this).attr('id');
            t = t.split('_');
            $('#price_show_'+t[1]).show();
        });
        $(this).mouseout(function(){
            var t = $(this).attr('id');
            t = t.split('_');
            $('#price_show_'+t[1]).hide();
        });
    });
    */
	$(".flash_u li:first").addClass("crumb");
	$(".news_flash dd:first").addClass("block");
	autoroll();
	hookThumb();
	
	$(".flash_r").click(function(){
		var lis = $(".flash_u li").length - 4;
		var left = $(".flash_u ul").css("left");
		left = ($(".flash_u ul").css("left")).substr(0,left.length-2);
		var maxleft = lis * -247;
		if ($(".flash_u li").css("left") == "auto" && lis == 0) left = 0;
		if (maxleft >= left) return false;
		$(".flash_u ul").animate({left: '-=247'}, 1);
	});
	$(".flash_l").click(function(){
		var left = $(".flash_u ul").css("left");
		left = ($(".flash_u ul").css("left")).substr(0,left.length-2);
		if ($(".flash_u ul").css("left") == "auto" || left >= 0 ) return;
		$(".flash_u ul").animate({left: '+=247'}, 1);
 	});
});