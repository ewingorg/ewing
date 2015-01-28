if (!this.tableMng) {
	var tableMng = {};
}

tableMng.init = function(tableId) {
	jQuery('#' + tableId + ' .group-checkable').change(function() {
		var set = jQuery(this).attr("data-set");
		var checked = jQuery(this).is(":checked");
		jQuery(set).each(function() {
			if (checked) {
				$(this).attr("checked", true);
			} else {
				$(this).attr("checked", false);
			}

		});
		jQuery.uniform.update(set);
	});
}

tableMng.selectItem = function(tableId) {
	var selectItem ='';
	var selectAll = jQuery('#' + tableId + ' .group-checkable');
	var set = jQuery(selectAll).attr("data-set"); 
	jQuery(set).each(function() {
		var checked = jQuery(this).is(":checked");
		var value = jQuery(this).val();
		if(checked && value)
			selectItem += value+','; 
	});
	if(selectItem.length>0)
		selectItem = selectItem.substring(0,selectItem.length-1);
	return selectItem;
}
