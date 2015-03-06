$(document).ready(function() {
    $(".login dl dt").click(function() {
        $(this).blur();
        $(".login dl dd").show();
        return false;
    });
    $(".aa_right").click(function(){
        $(".aa").hide();
    });
     $('.notice_close').click(function(){
         clearInterval(notice);
         $.cookie('notice', 'notice', {path:cookie_path , domain:cookie_host});
        $('.user_notice').css("display","none");
    });
    $(document).click(function(event){
        if( $(event.target).attr("class") != "tool_dl" ){
            $(".login dl dd").hide();
        }
    });
    $('.mask').click(function(){
        $('.close_gif').click();
    });
    $(".close_gif").click(function(){
        $(".mask").css("display","none");
        $(".tool_pop").css("display","none");
        $(".preview").css("display","none");
        $(".video_pop").css("display","none");
        $(".message_pop").css("display","none");
        $(".login_pop").css("display","none");
        $('.send_pop').css("display","none");
        if(typeof siddd != 'undefined')clearInterval(siddd);
    });
$(".brand dt").hover(function() {
    $(this).addClass("hover");
}, function() {
    $(this).removeClass("hover");
});

$(".save_bnt").hover(function() {
    $(this).addClass("hover");
}, function() {
    $(this).removeClass("hover");
});

    $(".parts dd:first").addClass("block");
    $(".parts dt > span").each(function(index){
        $(this).click(function(){
            $(".parts dd").removeClass("block");
            $(".parts dt span").removeClass("crumb");
            $(".parts dd").eq(index).addClass("block");
            $(this).addClass("crumb");
        });
    });
    $(".news dt > span").each(function(index){
        $(this).click(function(){
            $(".news dd").removeClass("block");
            $(".news dt span").removeClass("crumb");
            $(".news dd").eq(index).addClass("block");
            $(".news dd li img").each(function(){$(this).attr("src",$(this).attr("data-url"));});
            $(this).addClass("crumb");
        });
    });
    $(".community dt > span").each(function(index){
        $(this).click(function(){
            $(".community dd").removeClass("block");
            $(".community dt span").removeClass("crumb");
            $(".community dd").eq(index).addClass("block");
            $(".community dd  li img").each(function(){$(this).attr("src",$(this).attr("data-url"));});
            $(this).addClass("crumb");
        });
    });

    $(".contrast dd:even").addClass("addbg");
    $(".price p label").hover(function() {
        $(this).parents("p").addClass("hover");
    }, function() {
        $(this).parents("p").removeClass("hover");
    });
    $(".price input[type='radio']").click(function(){
        if($(this).attr("p-url")){
            $(".big").attr("src",$(this).attr("p-url"));
        }
    });

    $(".related_video dd:first").addClass("block");
    $(".related_video dt > span").each(function(index){
        $(this).click(function(){
            $(".related_video dd").removeClass("block");
            $(".related_video dt span").removeClass("crumb");
            $(".related_video dd").eq(index).addClass("block");
            $(this).addClass("crumb");
        });
    });
    $(".sell h2 a").toggle(function(){
        $(this).parents(".sell").removeClass("show");
    },function(){
        $(this).parents(".sell").addClass("show");
    });

	$(".float_tool dt").toggle(function(){
		$(this).parents("dl").addClass("float_show");
	},function(){
        $(this).parents("dl").removeClass("float_show");
	});
//     $("#ping li").hover(function() {
//        $(this).css("z-index","40");
//    }, function() {
//       $(this).css("z-index","1");
//    });
	$(".parts_nav li").hover(function() {
        $(this).addClass("crumb");
    }, function() {
        $(this).removeClass("crumb");
    });
	
	$(".parts_list li").hover(function() {
        $(this).addClass("crumb");
    }, function() {
        $(this).removeClass("crumb");
    });
    $(".login_pop input").each(function(index){
        $(this).focus(function(){
            $(".login_pop input").parent('p').removeClass("inputbg2");
            $(".login_pop input").parent('p').eq(index).addClass("inputbg2");
        });
    });

//跳转头部按钮
if($(document).height() - 100 > $(window).height()){
if($.browser.msie && parseInt($.browser.version) <= 6){ //IE6
    var incheight = Math.floor($(window).height()*2/3);
    $(window).resize(function(){incheight = Math.floor($(window).height()*2/3);});
    $(".roll_top").css({'position':'absolute','right':'-60px','top':incheight+'px'});
    $(".roll_top_div").removeClass("roll_top_div");
    $(window).scroll(function(){
        if($(window).scrollTop() == 0){
             $(".roll_top").hide();
        }else{
             if($(".roll_top").is(":hidden"))$(".roll_top").show();
             $(".roll_top").css("top",($(window).scrollTop()+incheight)+'px');
        }
    });
}else{
        $(".roll_top").css('bottom','150px');
        $(window).scroll(function(){
            if($(window).scrollTop() == 0){
                $(".roll_top").hide();
            }else if($(".roll_top").is(":hidden")){
                $(".roll_top").show();
            }
        });
}
}

});