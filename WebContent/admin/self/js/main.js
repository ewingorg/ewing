if (!this.mainFrame) {
	var mainFrame = {};
}

/**
 * 在container元素中显示内容
 * 
 * @param htmlhref
 *            页面链接
 */
mainFrame.addContainer = function(htmlhref) {
	common.writeHtml(htmlhref, 'main-container');
};

/**
 * 在业务主页中显示内容
 * 
 * @param htmlhref
 *            页面链接
 * @param searchFormId
 *            搜索表单id
 */
mainFrame.addContainer = function(htmlhref, searchFormId) { 
	var param = '';
	if (searchFormId && searchFormId != '')
		param = $("#" + searchFormId).serialize(); 
	var containerId = $('#main-container').attr('id');
	var container;
	if (!containerId) {
		container = $(document.body);
	} else {
		container = $('#' + containerId);
	}
	ajax.asyncHtmlRequest({
		url : htmlhref,
		param : param,
		success : function(data) {  
			mainFrame.renderHtml(data,container);
		}
	});
};

mainFrame.renderHtml=function(data,container){
	if(data){
	    if(data.indexOf('loginBtn')>-1)
	    	window.location='Admin-SellerLogin-toLogin.action';
		else 
			container.html(data);
	}
}

/**
 * 在指定区域中显示内容
 * 
 * @param htmlhref
 *            页面链接
 * @param searchFormId
 *            搜索表单id
 * @param areaId
 *            区域id
 */
mainFrame.addArea = function(htmlhref, areaId, searchFormId) {
	var param = '';
	if (searchFormId && searchFormId != '')
		param = $("#" + searchFormId).serialize();
	var container = $('#' + areaId); 
	ajax.syncHtmlRequest({
		url : htmlhref,
		param : param,
		success : function(data) {
			mainFrame.renderHtml(data,container);
		}
	});
};

/**
 * 点击菜单时候变高亮
 */
mainFrame.clickMenu = function(obj) { 
	$(".page-sidebar-menu li").each(function(index, element) {  
		$(element).removeClass("active");
		$(element).addClass("notactive");
	});
	$(obj).addClass("active");
};

/**
 * 点击置顶菜单时候变高亮
 */
mainFrame.clickTopMenu = function(obj) {
	$(".topnav a").each(function(index, element) {  
		$(element).removeClass("blue");
	});  
	var curMenuId = $(obj).attr("menuId");
	$(".page-sidebar-menu li").each(function(index, element) {  
		if($(element).attr("parentMenuId") == curMenuId) 
			$(element).show();
		else
			$(element).hide(); 
	});
	$(obj).addClass("blue");
};