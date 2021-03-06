if (!this.ajax) {
	var ajax = {};
}

 
ajax.syncJsonRequest= function(options ){
	if(options.isNeedLogin && userInfo==''){
		user.showLoginWin();
		return;
	}
	var opts = $.extend( true, {}, ajax.syncHtmlRequest.jsonsettings, options );
 	$.ajax({
		url : opts.url,
		data : opts.param,
		type : opts.type,
		async : opts.async,
		dataType : opts.dataType, 
		success :  opts.success,
		error : opts.err
	});
}


 
ajax.syncHtmlRequest= function(options){ 
    var opts = $.extend( true, {}, ajax.syncHtmlRequest.htmlsettings, options );
	if(opts.isNeedLogin && userInfo==''){ 
		user.showLoginWin();
		return;
	} 
 	$.ajax({
			url : opts.url,
			data : opts.param,
			type : opts.type,
			async : opts.async,
			dataType : opts.dataType, 
			success :  opts.success,
			error : opts.err
		});
}


ajax.asyncHtmlRequest= function(options){ 
    var opts = $.extend( true, {}, ajax.syncHtmlRequest.htmlsettings, options );
	if(opts.isNeedLogin && userInfo==''){ 
		user.showLoginWin();
		return;
	} 
 	$.ajax({
			url : opts.url,
			data : opts.param,
			type : opts.type,
			async : true,
			dataType : opts.dataType, 
			success :  opts.success,
			error : opts.err
		});
}
 
 

ajax.syncHtmlRequest.htmlsettings={      
	    type : 'POST', 
	    async : false, 
	    dataType : 'html'
};

ajax.syncHtmlRequest.jsonsettings={      
	    type : 'POST', 
	    async : false, 
	    dataType : 'json'
};


ajax.upload=function(options) {  
	var opts = $.extend( true, {}, ajax.upload.settings, options );
	var fileEleId = opts.fileEleId;
	var errorTip = opts.errTipId ? $('#'+opts.errTipId):$('#' + fileEleId + '_error_tip');
    var fileDetail = opts.fileDetailId ? $('#'+opts.fileDetailId):$('#' + fileEleId + '_detail');
    var filePath = $('#'+opts.filePathId);
    
	 $.ajaxFileUpload({
		url : contextPath + '/Busi-File-upload.action',
		secureuri : false,
		fileElementId : fileEleId,
		fileElementName : 'uploadfile',
		async : false,
		dataType : 'json',
		error : function(data, status, e) { 
			if(errorTip){ 
				errorTip.html('上传失败'); 
				errorTip.show();
		     }
			if(fileDetail)
				fileDetail.hide();
			if( $.isFunction(opts.errFn))
				opts.errFn(data);
		},
		success : function(data, status) { 
			if(!data.success){
				errorTip.html('上传失败'); 
				errorTip.show();
				return;
			}
			if(fileDetail){ 
				fileDetail.find('div.fileSize')
						.find('span').html(data.result.fileSizeFormat);
				fileDetail.find('div.fileName')
						.find('span').html(data.result.fileName);
				fileDetail.show();
		    }
			if(errorTip)
				errorTip.hide();
			if (filePath)
				filePath.val(data.result.filePath);
			if( $.isFunction(opts.successFn))
				opts.successFn(data);
		},
		complete : function(XMLHttpRequest, status) {
			//alert('complete');
		}
	});
	 
};

ajax.upload.settings = {      
		fileEleId : '', 
		errTipId :  '',  
		fileDetailId :  '',
		filePathId:'',
		errFn:'',
		sunccessFn:''
};
 