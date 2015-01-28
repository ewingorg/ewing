$.imageLoad = function(options) {
	$(window).unbind('scroll');
	$(window).bind('scroll', this.onScroll)
};
$.imageLoad.setting = {
	apiURL : 'http://www.wookmark.com/api/json/popular',
	page : 1,

	loaderCircle : $('#loaderCircle'),
	autoResize : true, // This will auto-update the layout when the browser
	// window is resized.
	container : $('#tiles'), // Optional, used for some extra CSS styling
	offset : 2, // Optional, the distance between grid items
	itemWidth : 210
// Optional, the width of a grid item

}
$.imageLoad.prototype = {
	html : "",
	handler : null,
	page : 1,
	opt : {},
	isLoading : false,
	isInit : false,
	onScroll : function(event) {
		var me = $.imageLoad.prototype;

		// Only check when we're not still waiting for data.
	if (!me.isLoading) {

		// Check if we're within 100 pixels of the bottom edge of the broser
	// window.
	var closeToBottom = ($(window).scrollTop() + $(window).height() > $(
			document).height() - 100);
	if (closeToBottom) {
		me.loadData();
	}
}
},
applyLayout : function(options) {
var me = $.imageLoad.prototype;
// Clear our previous layout handler.
	if (this.handler)
		this.handler.wookmarkClear();

	// Create a new layout handler.
	this.handler = $('#tiles li');
	this.handler.wookmark($.imageLoad.prototype.opt);
},
loadData : function(options) {
	var me = $.imageLoad.prototype;
	if (!me.isInit) {
		$(window).bind('scroll', this.onScroll);
		me.opt = $.extend(true, {}, $.imageLoad.setting, options);
		me.page = me.opt.page;
	}
	me.isInit = true;
	me.isLoading = true;
	me.opt.loaderCircle.show();
	$.ajax( {
		url : me.opt.apiURL,
		dataType : 'html',
		data : {
			curPage : me.page
		}, // Page parameter to make sure we load new data
		success : me.onLoadData,
		error : function() {
			alert('error')
		}
	});
},
onLoadData : function(data) {
	var me = $.imageLoad.prototype;
	me.isLoading = false;
	$('#loaderCircle').hide();
	// Increment page index for future calls.
	me.page++;

	// Create HTML for the images.
	me.wrapData(data);
	if (me.html == '') {
		me.page--;
	} else {
		// Add image HTML to the page.
		$('#tiles').append(me.html);

		// Apply layout.
		me.applyLayout();
	}
},
wrapData : function(data) {
	
	var me = $.imageLoad.prototype;
	/*
	var i = 0, length = data.result.length, image;
	me.html = '';
	for (; i < length; i++) {
		image = data.result[i].attachList[0];
		var imagePath = data.result[i].attachList[0].path;
		me.html += '<li>';

		// Image tag (preview in Wookmark are 200px wide, so we calculate the
	// height based on that).
	me.html += '<div><img src="' + imagePath + '" width="200" height="'
			+ Math.round(image.height / image.width * 200) + '"></div>';

	// Image title.
	me.html += '<p>' + data.result[i].article.contents + '</p>';

	me.html += '</li>';
	
	}*/
	me.html = data;

}

}