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
			container.html(data);
		}
	});
};

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
	ajax.asyncHtmlRequest({
		url : htmlhref,
		param : param,
		success : function(data) {
			container.html(data);
		}
	});
};

/**
 * 点击菜单时候变高亮
 */
mainFrame.clickMenu = function(obj) {
	$(".sub-menu li").each(function(index, element) {
		$(element).removeClass("active");
	});
	$(obj).addClass("active");
};