function radioVar(obj)
{
    var o=document.getElementsByName(obj);
    var len=o.length;
    for (var i=0;i<len;i++)
    {
        if( o[i].checked==true )
        {
            return o[i].value;
        }
    }
}

function quicksearch()
{
    if ($('#keyword').val() == "" || $('#keyword').val() == "输入品牌机型") {
        showMsg('关键字不能为空！','msg','2000');
        return false;
    }
	
    $('#Vgooo_Form_top').submit();
}
function searchv()
{
    if ($('#keyword').val() == "" || $('#keyword').val() == "请输入关键字") {
        showMsg('关键字不能为空！','msg','2000');
        return false;
    }

    $('#Vgooo_Form_top').submit();
}
function _findCheckVar(obj){
    /*
	*Ver 1.5
	*Note: find out which Checkbox true value
	*Created: 17:30 10/17/07
	*Modified: 15:29 01/22/08
	*Author :Edison tsai
	*/
    var elements=document.Vgooo_Form.elements;
    var counter=elements.length;
    var seltext="";
    for(i=0;i<counter;i++){
        var element=elements[i];
        if((element.type=="checkbox")&&(element.checked==true)&&(element.name==obj)){
            seltext = seltext+element.value+",";
        }
    }
    return seltext.substr(seltext,seltext.length-1);
}

  function searchuser(){
        var user = $('#user').val();
        $('#user_tips').html('');
        if (user == ""){
            $('#user_tips').html('请填写收件人');return false;
        }
        $.ajax({
            url:user_server_url + '/message/checkuser',
            dataType:'jsonp',
            jsonp:'callback',
            data:$('#message_form').serialize(),
            success:function(msg){
                if(msg != '1'){
                    $('#user_tips').html('找不到收件人！请确认');
                    $('#user').val('');
                    $('#sendmsg_uid').val('0');
                    return false;
                }
                else return true
            }
        });
    }

/*短消息使用*/
function sendmessage() {
    var content = $('#content_textarea').val();
    var user = $('#user').val();
    if(user == ""){
        $('#user_tips').html('请填写收件人');
        $('#user').focus();
        return false;
    }
    $('#user_tips').html('');
    if (content == ""){
        $('#content_tips').show();
        $('#content_tips').html('请填写内容');
        $('#content_textarea').focus();
        return false;
    }
    $('#content_tips').html('');
    $.ajax({
        dataType:'jsonp',
        jsonp:'callback',
        url:user_server_url+'/message/post',
        data:$('#message_form').serialize(),
        success:function(msg){
            if(msg == '1'){
                $('.close_gif').click();
                showMsg('发送成功','msg','2000');
            }else{
                showMsg(msg,'msg','2000');
            }
        }
    });
    return false;
}
function comm2(){
    $('#content_textarea').val('');
    $('#strlen').html('300');
    $('#user').attr('readonly',true);
    $('#user_tips').html('');
    $('#content_tips').html('');
}
function comm(){
    if(!$.cookie('VgoooUserId')){showMsg('您还没有登录，不能发短消息','msg',2000);return false;}
    $(".mask").css("height",$(document).height());
    var siddd = setInterval(function(){$(".mask").css("height",$(document).height());},1000);
    $(".mask").css("z-index",'3');
    $(".message_pop").css("left",($(document).width()-$(".message_pop").width())/2);
    $('.mask').show();
    $('.message_pop').show();
    $(".message_pop").css("z-index",'4');
}

function loadCartInfo()
{
$.ajax({url:shop_server_url+'/other/loadcartinfo',dataType:'jsonp',jsonp:'callback',data:'t='+(new Date()).getTime(),success:function(d){if(d){$("#loadCartInfo").html(d);}else{$("#loadCartInfo").html('0');}},error:function(){$("#loadCartInfo").html('0');}});
}

function memberInfo(){
    if($.cookie("VgoooUserId")!= null && $.cookie("VgoooUserName")!= null){
        $(".beforelogin").hide();
        $('.afterlogin').show();
        var url = location.href;
        if(url.indexOf('share.') > 0 || url.indexOf('vip.')){
            website_domain = '';
        }
        $.ajax({
        dataType:'jsonp',
        jsonp:'callback',
        url:shop_server_url+'/other/notice',
        data:'',
        success:function(data){
          if(data.notice){
               notice = setInterval('AutoScroll(".login_tips")',2000);
               $('.user_notice').show();
               $('#user_notice').append(data.notice);
            }
           if(data.name){$(".afterlogin em").html(data.name);}
          }
        });
        if($.cookie("VgoooUserSina") !=null){
            $(".pl_fx").show();
            if($.cookie('VgoooSinaStatus'))$("#fx").attr("checked",true);
        }
    }else {
        $(".beforelogin").show();
    }
}
var notice;
$().ready(function(){
    loadCartInfo();
    memberInfo();
    $('#user_name_quick,#password_quick').keyup(function(event){
        if(event.keyCode==13){
            submitLogin();
        }
    });

});
function quick(){
    var user_name = $('#user_name_quick').val();
    var user_password = $('#password_quick').val();
    if(!user_name){$('#user_name_tips').html('<img src="/images/ui/login_tips2.gif" /> 用户名错误！');return false;}
    if(user_name){
        $('#user_name_tips').html('<img src="/images/ui/login_tips.gif" />');
        if(!user_password){$('#pass_tips').html('<img src="/images/ui/login_tips2.gif" /> 密码错误！');return false;}
    }
    $('#pass_tips').html('<img src="/images/ui/login_tips.gif" />');
}
$(function(){
  $('#password_quick').focus(function(){
       $('#pass_tips').empty();
  });
});

function submitLogin() {
    var user_name = $('#user_name_quick').val();
    var user_password = $('#password_quick').val();
    var remember = $('#is_remember').is(':checked');
    $.ajax({
        dataType: 'jsonp',
        data: 'user_name='+user_name+'&user_password='+user_password+'&remember='+remember,
        jsonp: 'callback',
        url: user_server_url+'/login/plogin',
        success: function (data) {
            $('#pass_tips').html('<img src="/images/ui/wait.gif" /> 正在登录...');
            memberInfo();
            if(data =='1'){$('#user_name_tips').html('<img src="/images/ui/login_tips2.gif" /> 用户名错误！');$('#pass_tips').hide();return false;}
            if(data =='2'){$('#pass_tips').html('<img src="/images/ui/login_tips2.gif" /> 密码错误！');return false;}
            if(data =='3'){$('#pass_tips').html('<img src="/images/ui/login_tips2.gif" /> 用户名或密码错误！');return false;}
            $('#pass_tips').html('<img src="/images/ui/wait.gif" /> 正在登录...');
            if(data.indexOf('|') > -1){
                data= data.split('|');
                if (data.length==3) $(document).append(data[2]);
                
                if (data[0]!="" && data[1] !=""){
                    showMsg(data[0],'msg','1000',encodeURI(data[1]));
                }else {
                    $('.close_gif').click();
                }
            }else{
                $('.close_gif').click();
            }
        }
    });
}
function loginpop(){
    $(".mask").css("height",$(document).height());
    $(".login_pop").css("left",($(document).width()-$(".login_pop").width())/2);
     if ($.browser.msie && ($.browser.version == "6.0")) {
        $('.login_pop').css("position","absolute");
        $(window).scroll(function (){
            var bodyTop = 0;
            if (typeof window.pageYOffset != 'undefined') {
                bodyTop = window.pageYOffset;
            } else if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
                bodyTop = document.documentElement.scrollTop;
            } else if (typeof document.body != 'undefined') {
                bodyTop = document.body.scrollTop;
            }
            var scrollpos = bodyTop + document.documentElement.clientHeight - 410;
            $('.login_pop').css("top", scrollpos);
        });
    }
    $('.mask').show();
    $('.login_pop').show();
    $('.login_pop').css('z-index','9999');
    $.ajax({
        url:user_server_url+'/login/getlogininfo',
        data:'t='+Math.random(10),
        dataType:'jsonp',
        jsonp:'callback',
        success:function(data){
            if(data){
                $("#user_name_quick").val(data.vlxxxa);
            }
        }
    });
}
function AutoScroll(obj){
    $(obj).find("ul:first").animate({
            marginTop:"-22px"
    },500,function(){
            $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
    });
}

function sendpop(){
    if(!$.cookie("VgoooUserId")){
         loginpop();
         return false;
    }
    $(".mask").css("height",$(document).height());
    $(".send_pop").css("left",($(document).width()-$(".send_pop").width())/2);
    $('.mask').show();
    $('.send_pop').show();
    $('#vcodeimg').attr('src',website_domain + '/vcode/index?t='+Math.random());
    $("#vsendcode").val('');
    $('.send_pop').css('z-index','10000');
}

function sendaddr(){
 if(!$.cookie("VgoooUserId")){
     showMsg('对不起，您还没登陆！','msg','1000');return false;
 }
 var nub = $("#cellphone").val();
 var vsendcode = $("#vsendcode").val();
 if(/^1[\d]{10}/.test(nub)){
      if(!(/[\d]{5}/.test(vsendcode))){
        showMsg('验证码填写有误！','msg','1000');
        return false;
     }
     var date = new Date();
     date = date.getTime();
     $.post(website_domain+'/introstatic/sendaddr',{number:nub,vsendcode:vsendcode,time:date},function(data){
         if(data){
           if(data.indexOf("|") > 0){
              showMsg(data.split("|")[1],'msg','1000');
              return false;
           }
           $('.mask').css('display','none');
           $('.send_pop').css('display','none');
           showMsg(data,'msg','1000');
         }
     });
 }else{
     showMsg('手机号码填写有误！','msg','1000');
  }
 }
function info(typeid,id,prefix){
    if(!prefix)prefix = 'info_';
    $("#"+prefix+typeid).show();
    $.ajax({
        dataType: 'jsonp',
        jsonp: 'callback',
        url:website_domain+'/other/info',
        data:'id='+id,
        success: function (data) {
            if(data){
              $("#"+prefix+typeid).html(data);
            }
        }
    });
    return;
}

function savesina(){
    if($.cookie("VgoooUserSina") && $("#fx").is(":checked")){
        $.cookie("VgoooSinaStatus",'true',{expires:js_cookie_expire,path:cookie_path , domain:cookie_host});
    }else{
        $.cookie("VgoooSinaStatus",'',{expires:-1,path:cookie_path , domain:cookie_host});
    }
}

function photoOnload(img)
{
	var halfw = img.width / 2;
	$("#showpic").mousemove(function(e)
	{
             var mpos = mouseCoords(e);
             var x = mpos.x - $(this).offset().left;
            if(x < halfw)
            {
                $("#showpic").css("cursor","url("+shop_server_url+"/images/ui/next.cur), auto");
            }else
            {
                $("#showpic").css("cursor","url("+shop_server_url+"/images/ui/pre.cur), auto");
            }
	});
	$("#showpic").click(function(e)
	{
            var mpos = mouseCoords(e);
            var x = mpos.x - $(this).offset().left;
            if(x < halfw)
            {
                location.href = prevpage;
            }
            else
            {
                location.href = nextpage;
            }
	});
}
function mouseCoords(ev){
        if(ev.pageX || ev.pageY){
            return{x:ev.pageX, y:ev.pageY};
        }
        return{
            x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
            y:ev.clientY + document.body.scrollTop - document.body.clientTop
        };
  }

function getserchcontent(str){
    var ss = $('#'+str+'p').val();
    $('#'+str+'more').remove();
    $.ajax({
        url:shop_server_url+'/search/getcontent',
        data:'act='+str+'&kw='+encodeURI(kw)+'&p='+ss,
        type:'post',
        success:function(data){
            if(data){
                if(data.indexOf('||')>-1){
                    data = data.split('||');
                    $('#ul_'+str).append(data[1]);
                    $('#'+str+'p').val(parseInt(ss)+1);
                    if(data[0].indexOf('1') > -1){
                    $("#ul_"+str).after("<p id='"+str+"more'><a onclick=\"getserchcontent('"+str+"')\"><img src=\""+shop_server_url+"/images/ui/more_bnt.gif\" /></a></p>");
                    }
                }
            }
        }
    });
}

function parse_update(url) {
    $.get(url);
}

function setOpacity(obj, value) {
    if ($.browser.msie) {
        if (value == 100) {
            obj.style.filter = "";
        } else {
            obj.style.filter = "alpha(opacity="+value+")";
        }

    } else if($.browser.msie) {
        obj.style.MozOpacity = value/100;
    } else{
        obj.style.opacity = value/100;
    }
}



function changeOpacity(obj, startValue, endValue, step, speed) {

    if (step>0 && startValue<endValue || step<0 && startValue>endValue) {

        setOpacity(obj,endValue);

        return;

    }

    setOpacity(obj,startValue);

    setTimeout(function () {

        changeOpacity(obj,startValue-step,endValue,step,speed);

    },speed);

}


var seedTimeWait,settimeIntervalId = 0;
function showMsg(a,b,t,u) {
    var msg = document.getElementById(b);
    $('#'+b).css('display','block');
    if ($.browser.msie && ($.browser.version == "6.0")) {
        $('#'+b).css("position","absolute");
        $(window).scroll(function (){
            var bodyTop = 0;
            if (typeof window.pageYOffset != 'undefined') {
                bodyTop = window.pageYOffset;
            } else if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
                bodyTop = document.documentElement.scrollTop;
            } else if (typeof document.body != 'undefined') {
                bodyTop = document.body.scrollTop;
            }
            var scrollpos = bodyTop + document.documentElement.clientHeight - 500;
            $('#'+b).css("top", scrollpos);
        });
    }else{
        $('#'+b).css('position','fixed');
    }
    var step =15, speed = 100;
    $("#"+b+"_1").html(a);
    changeOpacity(msg,0,100,-step,speed);
    setTimeout("closeMsg('"+b+"')",t);
    if((typeof(u) !='undefined')){
        seedTimeWait=t/1000;
        settimeIntervalId =setInterval('seed_settime("'+u+'")', 1000);
    }
}
function seed_settime(u){
    seedTimeWait = seedTimeWait-1;
    if(seedTimeWait<=0){
        clearInterval(settimeIntervalId);
        window.location.href = u;
    }
}
function closeMsg(a){
    var step =10, speed = 100;
    var msg = document.getElementById(a);
    changeOpacity(msg,100,0,step,speed);
    $('#'+a).css('display','none');
}