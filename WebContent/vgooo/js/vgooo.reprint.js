//产品
var str=$("#zhuan_prod_name").val();

var content_text = "";
var title_text = "";

function renren(){
    var sellstr = $(".lab em").html()?$(".lab em").html()+'元':$("#notsell").html().substring(2);
    var url ="http://share.renren.com/share/buttonshare.do?link="+encodeURIComponent(location.href)+"&title="+encodeURIComponent('@幻想曲通讯 推荐：'+str+'最新报价：'+sellstr);
    window.open(url,'hexun','scrollbars=no,width=600,height=450,left=80,top=80,status=no,resizable=yes');
}
function kaixin(){
    var sellstr = $(".lab em").html()?$(".lab em").html()+'元':$("#notsell").html().substring(2);
    var url = "http://www.kaixin001.com/~repaste/repaste.php";
    url += "?rtitle="+encodeURIComponent('@幻想曲通讯 推荐：'+str+'最新报价：'+sellstr);
    url += "&rurl="+encodeURIComponent(location.href);
    url += "&rcontent="+content_text;
    window.open(url);
}
function sina(){
    var sellstr = $(".lab em").html()?$(".lab em").html()+'元':$("#notsell").html().substring(2);
    window.open('http://v.t.sina.com.cn/share/share.php?appkey=2271382318&title='+encodeURIComponent('@幻想曲通讯 推荐：'+str+'最新报价：'+ sellstr)+'&url='+encodeURIComponent(location.href)+'&source=bookmark&content=utf-8&pic='+encodeURIComponent($('#zhuan_prod_img').val())+'&retcode=0','sina','width=450,height=400');
}
function qqweibo() {
    var sellstr = $(".lab em").html()?$(".lab em").html()+'元':$("#notsell").html().substring(2);
    window.open('http://v.t.qq.com/share/share.php?title='+encodeURIComponent('@幻想曲通讯 推荐：'+str+'最新报价：'+ sellstr)+'&url='+encodeURIComponent(location.href)+'&pic='+encodeURIComponent($('#zhuan_prod_img').val())+'&site=www.vgooo.com');
}