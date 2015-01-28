if (!this.common) {
	var common = {};
}

common.writeHtml=function(htmlhref,targetdiv){   
	var containerId = $('#'+targetdiv).attr('id');
	var container ; 
	if(!containerId){
		container = $(document.body); 
	}else{
		container = $('#'+containerId);
	}
    ajax.asyncHtmlRequest({
    	url:htmlhref,
    	success:function(data){  
    		container.html(data);
    	}
    });
};

common.getHtml=function(htmlhref,isNeedLogin){ 
    var html=null;  
	  ajax.syncHtmlRequest({
      	url:htmlhref,
      	success:function(data){
		  html =data;
      	},
      	isNeedLogin:isNeedLogin
      });
	  return html;
};
 

common.popHtml=function(htmlhref,showId){ 
    var html=common.getHtml(htmlhref);  
	$('#'+showId).remove();
	$(document.body).append(html);
    $('#'+showId).modal('show'); 
};

common.hideHtml=function(showId){  
	$('#'+showId).remove(); 
};

common.showImgBtn=function(obj){  
	 $(obj).find("#imagebtn").show();
	 
};
common.hideImgBtn=function(obj){ 
	 $(obj).find("#imagebtn").hide();
};

common.alert=function(options){ 
	  var opts = $.extend( true, {}, common.alert.settings, options ); 
	  var html=common.getHtml(contextPath+"/common/alert.html");   
		$('#alert_result_win').remove();
		$(document.body).append(html);
	    $('#alert_result_win').modal('show');
	    $('#alert_title').html(opts.title);
	    $('#alert_content').html(opts.content);
	    if(opts.showAction){
	    	$('#alert_action_btn').html(opts.actionContent);
	    	$('#alert_action_btn').show();
	    	
	    }
	    
	   if( $.isFunction(opts.actionFn)){
	    	$('#alert_action_btn').bind("click",function(){
	    		 $('#alert_result_win').modal('hide'); 
	    			 opts.actionFn();
	    		}
	    	);
	    }  
	
	   if( $.isFunction(opts.closeFn)){
	    	$('#alert_close_btn').bind("click",function(){
	    		 $('#alert_result_win').modal('hide'); 
	    			 opts.closeFn();
	    		}
	    	);
	    }  
}

common.alert.settings={
		title:'提示',
	    content:'',
	    showAction:false,
	    actionContent:'',
	    actionFn: function(){},
	    closeFn:function(){}
}
 