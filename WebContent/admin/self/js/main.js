 if (!this.main) {
	var main = {};
}

/**
 * 在container元素中显示内容
 * 
 * @param htmlhref
 *            页面链接
 */
main.addContainer = function(htmlhref) { 
	common.writeHtml(htmlhref,'main-container');
};


/**
 * 在container元素中显示内容
 * 
 * @param htmlhref
 *            页面链接
 * @param searchFormId
 *            搜索表单id           
 */
main.addContainer = function(htmlhref,searchFormId) {  
	var param = '';
	if(searchFormId && searchFormId!='')
		param = $("#" + searchFormId).serialize(); 
	var containerId = $('#main-container').attr('id');
	var container ; 
	if(!containerId){
		container = $(document.body); 
	}else{
		container = $('#'+containerId);
	}
    ajax.asyncHtmlRequest({
    	url:htmlhref,
    	param : param,
    	success:function(data){  
    		container.html(data);
    	}
    });
}; 


/**
 * 点击菜单时候变高亮
 */
main.clickMenu = function(obj) {
	$(".sub-menu li").each(function(index, element) { 
		$(element).removeClass("active");
	});
	$(obj).addClass("active");
};