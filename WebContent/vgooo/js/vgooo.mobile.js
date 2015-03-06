// 切换
function setTab(id,style,url) {
    for(i=1; i<9; i++) {
        if(document.getElementById('tab'+i) && document.getElementById('tab'+i).className=='mainContent'){
            document.getElementById('tab'+i).style.display='none';
        }
    }
    document.getElementById(id).style.display='';

    var tabs=document.getElementById('ctltabs').getElementsByTagName('a');
    for(i=0; i<tabs.length; i++) {
        tabs[i].className='';
    }
    $('#'+id+'tab').toggleClass(style);
    $('#'+id+'tab').blur();
    if(url!="" && document.getElementById(id+"frame")){
        document.getElementById(id+"frame").src=url;
    }
}

// 订单
function order_now(id){
    if(id == "" || isNaN(id)) {
        window.scrollTo(0,0);
        showMsg('发生错误，暂不提供操作，抱歉！','msg','2000');
        return false;
    }
    if(!$.cookie("VgoooUserId")){
        loginpop();
        return false;
    }

    var type_cat = 11; //default category
    var arr= radioVar('mobile_price');
    //var standard = encodeURIComponent($('#standard').val());
    var vt= $("input[name='mobile_price']:checked").attr('class');
    vt = vt.split('_');
    var standard = encodeURIComponent($('#standard_'+vt[1]).val());
    if (standard == "undefined") standard = '';

    if (arr)
    {
        arr=arr.split('|');
        price=arr[0];
    }
    else
    {
        var arr = $('#mobile_price').val().split('|');
        price=arr[0];
    }
    if(price == '' || isNaN(price)) {
        window.scrollTo(0,0);
        showMsg('Sorry！请选择您要购买手机版本价格！','msg','2000');
        return false;
    }
    //if(id=='804' && arr[3]=='3'){showMsg('Sorry！行货联通版,邮购不能办理！','msg','2000');return false;}
    if(document.getElementById("buynow"))document.getElementById("buynow").disabled = true;

    var data = "mobile_id="+id+"&pid="+arr[1]+"&price="+parseFloat(price)+"&goods_cat="+type_cat+"&standard="+standard+"&prefix="+encodeURIComponent(arr[2])+"&name="+encodeURIComponent(prod_name)+"&brand="+encodeURIComponent(brand_name)+"&prod_type_id="+arr[3]+"&sub_id="+vt[1]+"&action=order_now";

//    location.href="/cart/addcart?"+data;
    $.ajax({
        url: shop_server_url+"/cart/addcart",
//        timeout:8000,
        dataType:"jsonp",
        jsonp:'addcart',
        data: data,
        success:function(d){
            if(d){
                if(d.msg && d.url){
                    showMsg(d.msg,'msg',2000,d.url);
                }else if(d.msg){
                    showMsg(d.msg,'msg',2000);
                }else if(d.url){
                    window.location.href = d.url;
                }
            }
        },
        complete:function(){
           try{document.getElementById("buynow").disabled = true;}catch(e){}
        },
        error:function(){
            showMsg('对不起，下单失败，请稍候再操作!','msg',2000);
        }
    });

}
function book_now(id){
    if(id == "" || isNaN(id)) {
        window.scrollTo(0,0);
        showMsg('发生错误，暂不提供操作，抱歉！','msg','2000');
        return false;
    }

    var type_cat = 11; //default category
    var arr= radioVar('mobile_price');
    //var standard = encodeURIComponent($('#standard').val());
    var vt= $("input[name='mobile_price']:checked").attr('class');
    vt = vt.split('_');
    var standard = encodeURIComponent($('#standard_'+vt[1]).val());
    if (standard == "undefined") standard = '';

    if (arr)
    {
        arr=arr.split('|');
        price=arr[0];
    }
    else
    {
        var arr = $('#mobile_price').val().split('|');
        price=arr[0];
    }
    if(price == '' || isNaN(price)) {
        window.scrollTo(0,0);
        showMsg('Sorry！请选择您要购买手机版本价格！','msg','2000');
        return false;
    }

    var data = "mobile_id="+id+"&pid="+arr[1]+"&price="+parseFloat(price)+"&goods_cat="+type_cat+"&standard="+standard+"&prefix="+encodeURIComponent(arr[2])+"&name="+encodeURIComponent(prod_name)+"&brand="+encodeURIComponent(brand_name)+"&action=book_now";

    location.href="/booking/addcart?"+data;
}


var compareMobilePosition = "";
var mobile_compare_data;
var mobile_compare_url;




function compareTime(a,b){
    return b.time-a.time;
}
// 浏览过的产品
function browserHistory()
{
    if($.cookie("VgoooBrowserProd")!=null){
        prods = $.cookie("VgoooBrowserProd");
        eval('var  prod = ('+prods+');');
        var str='';
        var counts= 0;//个数
        var pages=1;
        var ary = new Array();
        for(var i in prod){
            counts++;
            ary.unshift(prod[i]);
        }
        prod = ary.sort(compareTime);
        pages= Math.ceil(counts/5);
        var count = 0;
        var page = 1;
        for(var i in prod){
            var prod_id = prod[i].prod_id;
            var prod_name = prod[i].prod_name;
            var prod_html = prod[i].prod_html;
            var brand_name = prod[i].brand_name;
            var brand_html = prod[i].brand_html;
            var prod_image = prod[i].prod_image;
            var d_path = prod[i].d_path;
            if(count%5==0 ){
                if(count>0){
                    str+='</div>';
                }
                str+='<div id=page_'+page+' style="display:none">';
                page++;
            }
            str+='<li><p><a class="goods_img" target="_blank" href="'+baseUrl+'/peijian/'+brand_html+'/'+prod_html+'.html"><img title="'+brand_name+' '+prod_name+'" alt="'+brand_name+' '+prod_name+'" src="'+fitting_upload_view+'/images/'+d_path+'/fittingimage/sthumb/'+prod_image+'"/></a></p><!--p>'+brand_name+' '+prod_name+'</p--></li>';
            count++;
        }
        str+='</div>';
        if (pages > 1)
        {
            $('#browse_history_page_bar').html('<a onclick="browse_page( '+(pages-1)+' )" title="前页" href="javascript:void(0);"><img align="absmiddle" alt="" src="/images/ui/page_l.gif"/></a><em id="curpage"> '+1+'</em> / '+pages+' <a onclick="browse_page( '+(pages)+' )" title="下页" href="javascript:void(0)"><img align="absmiddle" alt="" src="/images/ui/page_r.gif"/></a>');
        }
        $('body').append(str);
        $('#browse_history').html($('#page_1').html());

    }
    else
    {
        $('#browse_history').empty();
        $('#browse_history').html('没有找到相关的记录！');
        $('#browse_history_page_bar').empty();
    }
    
}

function browse_page( go_page ) {
    $('#browse_history').html($('#page_'+go_page).html());
    $('#curpage').html(go_page);
}




function copyToClipboard(theField,isalert)
{
    var obj=document.getElementById(theField);
    if(obj!=null)
    {
        var clipBoardContent=obj.value;
        obj.select();
        window.clipboardData.setData("Text",clipBoardContent);
        if(isalert!=false)
            alert("复制成功。现在您可以粘贴（Ctrl+v）到QQ群中了。\r\r温馨提示：需要登录后推广此链接才能获得积分哦！");
    }
    else
    {
        alert("推荐好友!请先登录！");
    }
}



// 显示长度
function showLen(obj)
{
    var myLen  = obj.value.length;
    var msgs   = $('#content').val();

    if(myLen > 300) {
        showMsg('Sorry！咨询内容不能大于300个字符！','msg','2000');
        $('#content').val(msgs.substr(msgs,300));
        return false;
    } else {
        $('#msgLen').html(myLen);
    }
}
var rely_html = '';
// 评论发表
function post_comment()
{
    if(!$.cookie('VgoooUserId')){
        loginpop();
        return false;
    }
    var content = $("#comment_content").val();
    var parent_id = parseInt($('#parent_id').val());
    if(content == "") {
       showMsg('请填写评论内容！','msg','2000');
       $("#comment_content").focus();
        return false;
    }
    var fx = $("#fx").is(':checked');
    $.post('/other/postcomment',{comment_content:encodeURIComponent(content),id:mobile_id,parent_id:parent_id,fx:fx,f_id:f_id},function(data){
        if(data.indexOf("||")>0){
            data = data.split("||");
            savesina();
            showMsg(data[0],'msg','2000');return false;
        }else{
            $("#comment_content").val('')
            $('#parent_id').val('0');
            $('#quotecite').hide();
            getComment(0,1);
            $(".rob").hide();
            savesina();
            $.cookie('prod_feed','',{expires:-1,path:cookie_path , domain:cookie_host});
            showMsg(data,'msg','2000');
        }
    });
}

//
 function rely(id){
    if(id< 0) return false;
    $.getJSON('/other/commentrow', {comment_id:id},function(data){
        if(data){
            $('#parent_id').val(id);
            $("#quotecite span").html(data.user_name);
            $("#quotecite").show();
            $(window).scrollTop($("#info_review_form").offset().top);
        }
    });
}

function cancelquote(){
    $('#parent_id').val("0");
    $("#quotecite").hide();
}

function tstrlen(obj) {
    var myLen  = obj.value.length;
    var msgs   = obj.value;
    if(myLen > 140) {
        $('#strlen').html('0');
        showMsg('Sorry！内容不能大于140个字符！','msg','2000');
        obj.value = msgs.substr(msgs,140);
        return false;
    } else {
        $('#strlen').html(140-myLen);
    }

}
// 产品页评论
function getComment( page ,t)
{
        $('.list_more').remove();
	if(!isNaN(page) && page < 0) return;
	$.getJSON(baseUrl + "/other/getcomment?id=" + mobile_id + "&f_id="+f_id+"&page=" + page+'&time='+new Date().getTime(), function(data){
            if(t > 0)$("#comments_list").html('');
            if(data.comment_list){$("#comments_list").append(data.comment_list);}
            if(data.is_ok_list){$("#is_ok_list").append(data.is_ok_list);}
            if(data.page_info){$("#info_review").append(data.page_info);}
	});
        $("#comment_content").val('')
        $('#parent_id').val('0');
}
// g购买记录
function getbuyrecrod( page )
{
	if(!isNaN(page) && page < 0) return;
	$.get(baseUrl + "/other/buyrecord/?id=" + mobile_id + "&page=" + page , function(data){
		$("#mell").html(data);
	});
}
function getshare(prod_name, page )
{
        if(arguments[2] > 0){
            var sid = arguments[2];
            $.get(baseUrl + "/mosell/index/?brand=" + encodeURIComponent(brand_name) + "&pme=" + encodeURIComponent(prod_name) + "&sid=" + sid , function(data){
                if(data){
                    $("#share").html(data);
                    switch_dd_show('share');
                    $(window).scrollTop($("#share_list_sid_"+sid).offset().top);
                    show_review(sid);
                }
            });
            return;
        }
	if(!isNaN(page) && page < 0) return;
	$.get(baseUrl + "/mosell/index/?brand=" + encodeURIComponent(brand_name) + "&pme=" + encodeURIComponent(prod_name) + "&page=" + page , function(data){
		$("#share").html(data);
	});
}
function add_fav(prod_id)
{
    VgoooBox('<img src="/images/progress.gif" alt="正在加入收藏夹中，请稍候..." />&nbsp;正在加入收藏夹中，请稍候...');

    $.ajax({
        url: baseUrl+'/other/collection/prod_id/'+prod_id,
        timeout:8000,
        type:"get",
        dataType:"text",
        success:function(data)
        {
            if (data == 1) VgoooBox('请先登录再进行操作，谢谢！');
            else if(data == 2) VgoooBox('<font color="red">Sorry！已有相同的记录在您的收藏夹里，不能重复添加！</font>');
            else if(data == 3) VgoooBox('已成功加入您的收藏夹中！');
            else VgoooBox('操作失败，请联系管理员！');
        },
        error:function(){
            alert('操作失败，请联系管理员！');
        }
    });
}

function pricephotos( clickPriceNum )
{
    for(var i=0;i<pricecounts.length;i++)
    {
        if (clickPriceNum == pricecounts[i])
        {
            $("#photos_"+pricecounts[i]).show();
        }
        else
        {
            $("#photos_"+pricecounts[i]).hide();
        }
    }
}

function close_comment_tip()
{
    $("#comment_tip").hide();
    $(".feedback_input_zone").show();
}
//$(function(){
//
//    });


function getQueryStringRegExp(name) {
    var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
    if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " "));return "";

}
function prod_news(){
    $.post('/other/prodnews',{prod_id:mobile_id},function(data){
        $('#news').html(data);
    });
}
function prod_help(){
    $.post('/other/prodhelp',{prod_id:mobile_id},function(data){
        $('#help').html(data);
    });
}
$().ready(function(){
   // getComment(0);
    
});