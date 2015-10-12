var tableAction = {

	bindQueryAction : function(queryBtnId, queryUrl, searchFormId) {
		$('#' + queryBtnId).bind('click', function() {
			mainFrame.addContainer(queryUrl, searchFormId);
		});
	},

	bindEditAction : function(editUrl, isPopEditForm) {
		$("a[name='edit_action']").bind("click", function() {
			var url = editUrl;
			var id = $(this).attr("value");
			if (id)
				url += "?id=" + id;
			if (isPopEditForm)
				common.popHtml(url, 'navModel');
			else
				mainFrame.addContainer(url);
		});
	},
	bindNewAction : function(editBtnId, editUrl, isPopEditForm) {
		$("#" + editBtnId).bind("click", function() { 
			var url = editUrl;
			if (isPopEditForm)
				common.popHtml(url, 'navModel');
			else
				mainFrame.addContainer(url);
		});
	},
	bindSaveAction : function(saveBtnId, formId, saveUrl, queryUrl,
			beforeSaveFn) {

		$("#" + saveBtnId).bind("click", function() {
			if ($.isFunction(beforeSaveFn))
				beforeSaveFn();
			var valResult = AI.Validator.validForm(formId, 1, "errtip");
			if (!valResult)
				return;
			var param = $("#" + formId).serialize();
			var url = saveUrl;
			ajax.syncJsonRequest({
				url : url,
				param : param,
				success : function(data) {
					if (!data || !data.success) {
						$('#errtip').html('保存失败！');
						$('#errtip').show();
					}
					if (data.success) {
						$('#navModel').modal('hide');
						common.alert({
							content : '保存成功！',
							closeFn : function() {
								if (queryUrl != '' && queryUrl)
									mainFrame.addContainer(queryUrl);
								
							}
						});
					}
				}
			});
		});
	},
	bindDeleteAction : function(deleteBtnId, tableId, deleteUrl, queryUrl) {
		$('#' + deleteBtnId).bind('click', function() {
			var selectItems = tableMng.selectItem(tableId);
			if (selectItems.length == 0) {
				common.alert({
					content : '请选中需要操作的项！'
				});
				return;
			}
			common.alert({
				content : '是否删除选中项？',
				showAction : true,
				actionContent : '删除',
				actionFn : function() {
					var url = deleteUrl + '?selectItems=' + selectItems;
					ajax.syncJsonRequest({
						url : url,
						success : function(data) {
							if (!data || !data.success) {
								$('#errtip').html(data.retinfo);
								$('#errtip').show();
							}
							if (data.success) {
								common.alert({
									content : data.retinfo,
									closeFn : function() {
										if (queryUrl != '' && queryUrl)
											mainFrame.addContainer(queryUrl);
									}
								});
							}
						}
					});
				}
			});
		});
	}
};

tableAction.option = {
	tableId : '',
	formId : '',
	saveUrl : '',
	queryUrl : '',
	deleteUrl : '',
	editUrl : '',
	queryBtnId : '',
	deleteBtnId : '',
	editBtnId : '',
	saveBtnId : '',
	searchFormId : '',
	beforeSaveFn : '',
	isPopEditForm : true
}

tableAction.init = function(opt) {
	var option = $.extend(true, {}, tableAction.option, opt);
	if (option.tableId)
		tableMng.init(option.tableId);

	if (option.editUrl && option.editBtnId)
		tableAction.bindNewAction(option.editBtnId, option.editUrl,
				option.isPopEditForm);

	if (option.editUrl)
		tableAction.bindEditAction(option.editUrl, option.isPopEditForm);

	if (option.queryBtnId && option.queryUrl && option.searchFormId)
		tableAction.bindQueryAction(option.queryBtnId, option.queryUrl,
				option.searchFormId);

	if (option.deleteBtnId && option.tableId && option.deleteUrl
			&& option.queryUrl)
		tableAction.bindDeleteAction(option.deleteBtnId, option.tableId,
				option.deleteUrl, option.queryUrl);
}

/**
 * 初始化保存逻辑
 */
tableAction.initSaveAction = function(option) {
	if (option.saveBtnId && option.formId && option.saveUrl) {
		tableAction.bindSaveAction(option.saveBtnId, option.formId,
				option.saveUrl, option.queryUrl, option.beforeSaveFn);
	}
}

/**
 * 初始化查询table
 */
tableAction.initTable = function(tableId) {
	tableMng.init(tableId);
}
