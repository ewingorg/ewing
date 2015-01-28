if (!this.AI) {
	var AI = {};
}
// 避免首页取不到变量
var _validator_mobile_regex_str; // 手机正则
if (!_mobilePattern) {
	var _mobilePattern = '';
	_validator_mobile_regex_str = '';
} else {
	_validator_mobile_regex_str = '|' + _mobilePattern; // 附加整合平台正则
}
/**
 * Desc: form Validator lib <br>
 * 
 * Copyright (c) 2010 by Asiainfo-Linkage.COM, Inc. All Rights Reserved. <br>
 * 
 * Date: 2010-09-03 <br>
 * 
 */
AI.Validator = {
	errinput : 'errinput',
	errzone : '_err_zone',
	errshow : true,
	errmsg : '',
	errcls : 'no',
	yescls : 'yes',
	errorTip : 'error_tip',
	errorInput : 'error_input',
	validTip : 'valid_tip',
	msgErrorTip : 'msg_error_tip',
	msgValidTip : 'msg_valid_tip',
	require : /[^(^\s*)|(\s*$)]/,
	isEmpty : /[^(^\s*)|(\s*$)]/,
	email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	// mobile :
	// _validator_mobile_regex||/^((\(\d{3}\))|(\d{3}\-))?13[0-9]\d{8}?$|1(5|8)[02789]\d{8}?$/,
	mobile : new RegExp(
			'^((\\(\\d{3}\\))|(\\d{3}\\-))?13[0-9]\\d{8}?$|1(5|8)[02789]\\d{8}?$'
					+ _validator_mobile_regex_str),
	contact : /(^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)|^((\(\d{3}\))|(\d{3}\-))?13[0-9]\d{8}?$|1(5|8)[02789]\d{8}?$/,
	// contact : new
	// RegExp('(^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)|^((\\(\\d{3}\\))|(\\d{3}\\-))?13[0-9]\\d{8}?$|1(5|8)[02789]\\d{8}?$'+_validator_mobile_regex_str),
	url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	idCard : "this.isIdCard(value)",
	currency : /^\d+(\.\d+)?$/,
	number : /^\d+$/,
	zip : /^[0-9]\d{5}$/,
	ip : /^[\d\.]{7,15}$/,
	qq : /^[1-9]\d{4,8}$/,
	positiveInteger : /^[1-9]\d*$/,
	integer : /^[-\+]?\d+$/,
	double : /^[-\+]?\d+(\.\d+)?$/,
	english : /^[A-Za-z]+$/,
	chinese : /^[\u0391-\uFFE5]+$/,
	userName : /^[A-Za-z0-9_]{3,}$/i,
	// unSafe :
	// /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	unSafe : /[<>\?\#\$\*\&;\\\/\[\]\{\}=\(\)\.\^%,]/,
	// safeStr : /[^#\'\"~\.\*\$&;\\\/\|]/,
	isSafe : function(str) {
		return !this.unSafe.test(str);
	},
	getStrlen : function(str) {
		var Charset = jQuery.browser.msie ? document.charset
				: document.characterSet
		if (Charset.toLowerCase() == 'utf-8') {
			return str.replace(/[\u4e00-\u9fa5]/g, "***").length;
		} else {
			return str.replace(/[^\x00-\xff]/g, "**").length;
		}
	},
	safeString : "this.isSafe(value)",
	filter : "this.doFilter(value)",
	limit : "this.checkLimit(this.getStrlen(value))",
	limitB : "this.checkLimit(this.LenB(value))",
	date : "this.isDate(value)",
	repeat : "this.checkRepeat(value,true)",
	unRepeat : "this.checkRepeat(value,false)",
	range : "this.checkRange(value)",
	compare : "this.checkCompare(value)",
	custom : "this.Exec(value)",
	group : "this.mustChecked()",
	ajax : "this.doajax(errindex)",
	isIdCard : function(number) {
		var date, Ai;
		var verify = "10X98765432";
		var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
		var area = [ '', '', '', '', '', '', '', '', '', '', '', '北京', '天津',
				'河北', '山西', '内蒙古', '', '', '', '', '', '辽宁', '吉林', '黑龙江', '',
				'', '', '', '', '', '', '上海', '江苏', '浙江', '安微', '福建', '江西',
				'山东', '', '', '', '河南', '湖北', '湖南', '广东', '广西', '海南', '', '',
				'', '重庆', '四川', '贵州', '云南', '西藏', '', '', '', '', '', '', '陕西',
				'甘肃', '青海', '宁夏', '新疆', '', '', '', '', '', '台湾', '', '', '',
				'', '', '', '', '', '', '香港', '澳门', '', '', '', '', '', '', '',
				'', '国外' ];

		if (number) {
			number = number.toUpperCase();
		}
		var re = number
				.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[X\d])))$/i);
		if (re == null)
			return false;
		if (re[1] >= area.length || area[re[1]] == "")
			return false;
		if (re[2].length == 12) {
			Ai = number.substr(0, 17);
			date = [ re[9], re[10], re[11] ].join("-");
		} else {
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = [ "19" + re[4], re[5], re[6] ].join("-");
		}
		if (!this.isDate(date, "ymd"))
			return false;
		var sum = 0;
		for (var i = 0; i <= 16; i++) {
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai += verify.charAt(sum % 11);
		return (number.length == 15 || number.length == 18 && number == Ai);
	},
	isDate : function(op) {
		var formatString = this['element'].attr('format');
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch (formatString) {
		case "ymd":
			m = op.match(new RegExp(
					"^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
			if (m == null)
				return false;
			day = m[6];
			month = m[5] * 1;
			year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
			break;
		case "dmy":
			m = op.match(new RegExp(
					"^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
			if (m == null)
				return false;
			day = m[1];
			month = m[3] * 1;
			year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
			break;
		default:
			break;
		}
		if (!parseInt(month))
			return false;
		month = month == 0 ? 12 : month;
		var date = new Date(year, month - 1, day);
		return (typeof (date) == "object" && year == date.getFullYear()
				&& month == (date.getMonth() + 1) && day == date.getDate());
		function GetFullYear(y) {
			return ((y < 30 ? "20" : "19") + y) | 0;
		}
	},
	// end isDate
	doFilter : function(value) {
		var filter = this['element'].attr('accept');
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(
				/\s*,\s*/).join("|")), "gi").test(value);
	},
	checkLimit : function(len) {
		var minval = this['element'].attr('min') || Number.MIN_VALUE;
		var maxval = this['element'].attr('max') || Number.MAX_VALUE;
		return (minval <= len && len <= maxval);
	},
	LenB : function(str) {
		return str.replace(/[^\x00-\xff]/g, "**").length;
	},
	checkRepeat : function(value, flag) {
		var to = this['element'].attr('to');
		// (value == jQuery('input[name="' + to + '"]').eq(0).val());
		var isRepeat = (value == jQuery('#' + to).val());
		if (flag == true) {
			return isRepeat;
		} else {
			return !isRepeat;
		}
	},
	checkRange : function(value) {
		value = value | 0;
		var minval = this['element'].attr('min') || Number.MIN_VALUE;
		var maxval = this['element'].attr('max') || Number.MAX_VALUE;
		return (minval <= value && value <= maxval);
	},
	checkCompare : function(value) {
		var compare = this['element'].attr('compare');
		if (isNaN(value))
			return false;
		value = parseInt(value);
		return eval(value + compare);
	},
	Exec : function(value) {
		var reg = this['element'].attr('regexp');
		return new RegExp(reg, "gi").test(value);
	},
	mustChecked : function() {
		var tagName = this['element'].attr('name');
		var f = this['element'].parents('form');
		if (this['element'].attr("type") == 'hidden') {
			this['element'].attr("disabled", "disabled");
		}
		var n = f.find('input[name="' + tagName + '"][checked]').length;
		var count = f.find('input[name="' + tagName + '"]').length;
		var minval = this['element'].attr('min') || 1;
		var maxval = this['element'].attr('max') || count;
		return (minval <= n && n <= maxval);
	},
	doajax : function(value) {
		var element = this['element'];
		var errindex = this['errindex'];
		var url = this['element'].attr('url');
		var msgid = jQuery('#' + element.attr('msgid'));
		var val = this['element'].val();
		var str_errmsg = this['element'].attr('msg');
		var arr_errmsg;
		var errmsg;
		if (str_errmsg.indexOf('|') > -1) {
			arr_errmsg = str_errmsg.split('|');
			errmsg = arr_errmsg[errindex];
		} else {
			errmsg = '';
		}
		var type = this['element'].attr('type');
		var Charset = jQuery.browser.msie ? document.charset
				: document.characterSet;
		var methodtype = (Charset.toLowerCase() == 'utf-8') ? 'post' : 'get';
		var method = this['element'].attr('method') || methodtype;
		var name = this['element'].attr('name');
		if (url == "" || url == undefined) {
			alert('Please specify url');
			return false;
		}
		if (url.indexOf('?') > -1) {
			url = url + "&" + name + "=" + escape(val);
		} else {
			url = url + '?' + name + "=" + escape(val);
		}
		AI.Validator.removeErr(this['element']);
		this['element'].next('.' + AI.Validator.errorTip).remove();
		var obj = $.ajax({
			type : method,
			url : url,
			data : {},
			cache : false,
			async : false,
			success : function(obj) {
				var msg = obj.msg;
				var code = obj.code;
				msg = msg.replace(/(^\s*)|(\s*$)/g, "");
				if (code != '0') {
					errmsg = errmsg == "" ? msg : errmsg;
					(type != 'checkbox' && type != 'radio' && element
							.addClass(AI.Validator.errorInput));
					if (msgid.length > 0) {
						msgid.removeClass(AI.Validator.msgValidTip).addClass(
								AI.Validator.msgErrorTip).html(errmsg);
					} else {
						jQuery(
								"<span class='" + AI.Validator.errorTip
										+ "'></span>").html(errmsg)
								.insertAfter(element);
					}
					return false;
				}
				if (code == '0') {
					if (msgid.length > 0) {
						msgid.removeClass(AI.Validator.msgErrorTip).addClass(
								AI.Validator.msgValidTip).html('');

					} else {
						jQuery(
								"<span class='" + AI.Validator.validTip
										+ "'></span>").insertAfter(element);
					}
					return true;
				}
			}
		}).responseText;
		return obj.code == '0' ? true : false;
	}
}; // element
AI.Validator.showErr = function(element, errindex) { // alert('showErr');
	var str_errmsg = element.attr('msg') || 'unkonwn';
	var arr_errmsg = str_errmsg.split('|');
	var errmsg = arr_errmsg[errindex] ? arr_errmsg[errindex] : arr_errmsg[0];
	var strid = element.attr('msgid');
	var msgid = jQuery('#' + strid)
	var type = element.attr('type');
	var name = element.attr('name');
	(type != 'checkbox' && type != 'radio' && element
			.addClass(this['errorinput']));
	element.next('.' + this['errorTip']).remove();
	if (strid && strid == "errid") {
		// error\info\warn
		this['errshow'] = false;
		var errzone = jQuery('#' + this['errzone']);
		errzone.attr("checkname", element.attr("name"));
		AI.Validator.showErrZone(errmsg, errzone);

	} else if (msgid.length > 0) {
		msgid.removeClass(AI.Validator.msgValidTip).addClass(
				AI.Validator.msgErrorTip).html(errmsg);
	} else {
		AI.Validator.removeRightTip(element); 
		var errzone = jQuery('#' + this['errzone']); 
		var exitsMsg = errzone.html(); 
		if(exitsMsg){ 
			errmsg ="<br>"+errmsg;
		} 
		errzone.append(errmsg);
		/*jQuery(  
		"<div class='alert' ></div>").html(errmsg)
		.insertAfter(errzone);*/
	}
	return false;
}
AI.Validator.removeErr = function(element) {
	element.removeClass(this['errorInput']);
	element.next('.' + this['errorTip']).remove();
}

AI.Validator.removeRightTip = function(element) {
	element.next('.' + this['validTip']).remove();
}
AI.Validator.checkajax = function(element, datatype, errindex) {
	var value = jQuery.trim(element.val());
	this['element'] = element;
	this['errindex'] = errindex;
	AI.Validator.removeErr(element);
	return eval(this[datatype]);
}
AI.Validator.checkDatatype = function(element, datatype) {
	var value = jQuery.trim(element.val());
	this['element'] = element;
	AI.Validator.removeErr(element);
	switch (datatype) {
	case "idCard":
	case "date":
	case "repeat":
	case "unRepeat":
	case "range":
	case "compare":
	case "custom":
	case "group":
	case "limit":
	case "limitB":
	case "safeString":
	case "filter":
		return eval(this[datatype]);
		break;
	default:
		return this[datatype].test(value);
		break;
	}
}
AI.Validator.check = function(obj) {
	var datatype = obj.attr('datatype');
	var inputtype = obj.attr('type');
	var value = jQuery.trim(obj.val());
	if (typeof (datatype) == "undefined")
		return true;
	if (inputtype == 'checkbox' || inputtype == 'radio') {
		if (!obj.parents('form').find(
				'input[name="' + obj.attr("name") + '"][require="true"]')) {
			return true;
		}
	} else if (obj.attr('require') != "true" && value == "") {
		return true;
	}
	if (datatype.indexOf('isEmpty') >= 0) {
		if (value == null || value == '') {
			return true;
		}
	}

	var datatypes = datatype.split('|');
	var isValid = true;
	// placeholder
	if (obj.attr("placeholder") && obj.attr("require") == "true") {
		if (obj.focus().val() == "") {
			isValid = false;
		}
	}

	jQuery.each(datatypes, function(index, type) {
		if (typeof (AI.Validator[type]) == "undefined") {
			isValid = false;
			return false;
		} // ajax validate
		if (type == 'ajax')
			return isValid = AI.Validator.checkajax(obj, type, index);
		if (AI.Validator.checkDatatype(obj, type) == false) { // the
			// form
			// element
			// validate failed
			obj.addClass(AI.Validator.errorInput);
			AI.Validator.showErr(obj, index);
			return isValid = false;
		} else { // validate success
			// AI.Validator.showErr(obj, index);
			obj.removeClass(AI.Validator.errorInput);
			var strmsgid = obj.attr('msgid');
			var msgid = jQuery('#' + strmsgid);
			if (strmsgid == "errid") {
				var err_zone = jQuery('#' + AI.Validator.errzone);
				var chk_name = err_zone.attr("checkname");
				if (chk_name && chk_name == obj.attr("name")) {
					err_zone.empty().html("");
				}
			} else if (msgid.length > 0) {
				msgid.removeClass(AI.Validator.msgErrorTip).addClass(
						AI.Validator.msgValidTip).html('');
			} else {
				obj.next('.' + AI.Validator.errorTip).remove();
				jQuery('<span class="' + AI.Validator.validTip + '"></span>')
						.insertAfter(obj);
			}
		}
	});
	return isValid;
}
/**
 * 外部手机正则
 * 
 * @param {}
 *            _mobilePattern
 * @return {String}
 */
AI.Validator.isMobilePattren = function(_mobilePattern) {
	if (_mobilePattern && _mobilePattern !== 'null') {
		var reg = new RegExp(_mobilePattern);
		if (reg) {
			return reg;
		}
	}

	return '';
}
// @version 1.2 modify by tanzy at 20100910
// @version 1.3 modify by tanzy at 20101111
/**
 * 局部校验
 * 
 * @param {}
 *            elements ｜elements
 * @return {}
 */
AI.Validator.aloneForm = function(elements) {
	elements.blur(function(index) {
		return AI.Validator.check(jQuery(this));
	});
}
/**
 * 全局校验
 * 
 * @param {}
 *            form
 * @param {}
 *            elements
 * @return {Boolean}
 */
AI.Validator.valid = function(form, elements) {
	var isValid = true;
	var errIndex = new Array();
	var n = 0;
	elements.each(function(i) {
		var jqry = jQuery(this);
		if (jqry.attr("placeholder") && jqry.attr("require") == "true") {
			if (jqry.focus().val() == "") {
				isValid = false;
				errIndex[n++] = i;
			}
		}
		if (AI.Validator.check(jqry) == false) {
			isValid = false;
			errIndex[n++] = i;
		}
		;
	});
	if (isValid == false) {
		return false;
	}
	return true;

}

/**
 * Desc: 通用表单校验 <br>
 * 使用AI.Validator.validForm($("#form"))<br>
 * 或AI.Validator.validForm("formId") 或AI.Validator.validForm("fromId",type)的形式<br>
 * 
 * <br>
 * 
 * Date: 2010-09-03 <br>
 * 
 * @param {}
 *            opts | 传入fromId
 * @param {}
 *            type | 校验的类型。表单提交默认一般使用AI.Validator.validForm('formId',1)
 *            type==1:即normal校验方式
 * @return {}
 */
AI.Validator.validForm = function(opts, type, errzone, errmsg) {
	var form = jQuery.AI$OBJ(opts);
	var elements = form.find(":input[require='true']");
	if (type == undefined) {
		AI.Validator.aloneForm(elements);
	} else if (type == 1) {
		AI.Validator.submitBox(form, 'select');
		if (errzone) {
			AI.Validator.errzone = errzone;
		} else {
			AI.Validator.errzone = '_err_zone';
		}
		if (errmsg) {
			AI.Validator.errmsg = errmsg;
		} else {
			AI.Validator.errmsg = '请正确填写表单';
		}
		$("#" + errzone).html('');
		var ret = AI.Validator.valid(form, elements);
		if (!ret && AI.Validator.errshow) {
			
			AI.Validator.showErrZone("请正确填写表单", AI.Validator.errzone);
			$("#" + errzone).show();
		}
		return ret;
	} else {
		return true;
	}
}

/**
 * Desc: 通用加载表单校验 <br>
 * 统一在加载的时候检测所有的表单验证。<br>
 * 提交表单需要另外使用AI.Validator.validForm(formId,1)方法<br>
 * 
 * <br>
 * 
 * Date: 2010-11-11 <br>
 * 
 * @return {}
 */
AI.Validator.blurFormValid = function() {
	jQuery.each(jQuery("form[id]"), function() {
		var jqr = jQuery(this);
		if (!jqr.attr("valid")) {
			jqr.attr("valid", "true"); // 避免重复加载
			AI.Validator.checkBox(jqr, 'select');
			AI.Validator.validForm(jqr);
		}
	});
}

/**
 * Desc: 添加非空校验的*标识 <br>
 * 
 * <br>
 * 
 * Date: 2010-11-17 <br>
 */
AI.Validator.requireSign = function(targetZone) {
	jQuery.each(jQuery(targetZone + " form[id] :input[require='true']"),// [type!='hidden']
	function() {
		var jqr = jQuery(this)
		// if "this" then insert after
		var specSign = jqr.attr("reqsignid");
		var sign = jQuery("<span class='require_sign' >&nbsp;*</span>")
		if (specSign && specSign == "no") {
			// if no do nothing
		} else if (specSign && specSign != "this") {
			// insert reqsignid
			jQuery("#" + specSign).empty().attr("class", "require_sign")
					.append(sign.contents());
		} else if (specSign == "this" || jqr.attr("datatype") !== "group") {
			jqr.nextAll("span").remove(".require_sign");
			sign.insertAfter(jqr);
		}
	});

}

/**
 * 在errzone显示错误
 * 
 * @param {}
 *            msg
 * @param {}
 *            errzoneid
 * @param {}
 *            type
 */
AI.Validator.showErrZone = function(msg, errzoneid, type) {
	if (!errzoneid) {
		errzoneid = AI.Validator.errzone;
	}
	if (!type) {
		type = 'fail';
	}
	var errzone = jQuery.AI$OBJ(errzoneid);
	 
	if (errzone && errzone.length > 0) {
	 	errzone.prepend(
				'<div class="btn_frame"><div class="' + type
						+ '_img"></div><div class="' + type + '_text">' + msg
						+ '</div></div>'); 
	} 

}

/**
 * 
 * Desc: 点击行选中单/复选择框 <br>
 * 
 * <br>
 * Date: 2010-12-09 <br>
 * 
 * @param {}
 *            formObj | fromId或form的jquey对象
 * @param {}
 *            datatype｜选择的类型 支持类型datatype='select'
 * @param {}
 *            boxname ｜单选或复杂框的name
 */
AI.Validator.checkBox = function(formObj, datatype, boxname) {
	var checks = jQuery.AI$OBJ(formObj).find("tr[datatype='" + datatype + "']");
	var expr = "input:checkbox,input:radio";
	if (checks.size() > 0) {
		if (datatype == 'select') {
			checks.each(function() {
				var jqr = jQuery(this);
				var select = jqr.find(expr).first();
				// if (!select.attr("disabled")) {
				jqr.children().bind("click", function() {
					// except input location
					if ($(this).find(":input[type!='hidden']").size() == 0) {
						if (!select.attr("disabled")) {
							if (select.attr("checked")) {
								if (select.attr("type") == "checkbox") {
									select.removeAttr("checked");
								}
							} else {
								select.focus().click();
								select.attr("checked", "checked");
							}
						}

					}

				});
				// }
			});
		}
	}
}

/**
 * 
 * Desc: 单/复选框数据提交 <br>
 * Date: 2010-12-09 <br>
 * 
 * @param {}
 *            formObj | fromId或form的jquey对象
 * @param {}
 *            datatype｜选择的类型 支持类型datatype='select'
 * @param {}
 *            colname ｜自定义的hidden表单数据域或由框架默认处理
 */
AI.Validator.submitBox = function(formObj, datatype, colname) {
	var form = jQuery.AI$OBJ(formObj);
	var expr = "input:checkbox,input:radio";
	var checks = form.find("tr[datatype='" + datatype + "']");
	var data = new Array();
	var datastr = '';
	if (checks.size() > 0) {
		checks.each(function() {
			var jqr = jQuery(this);
			var select = jqr.find(expr).first();
			if ((select.attr("checked") == true)
					&& (select.attr("disabled") == false)) {
				var inputs = jqr.find("input[name]:not(:disabled)");
				var str = "{";
				inputs.each(function() {
					var input = $(this);
					var val = input.val();
					if (val) {
						val = escape(val)
					}
					;
					str = str + "'" + input.attr("name") + "':'" + val + "',"
				});
				str = str.substring(0, str.length - 1) + "}";
				if (str.length > 2) {
					data.push(str);
				}
			}
		});
		datastr = data.toString();
		if (datastr) {
			datastr = escape('[' + datastr + ']');
		}

		if (!colname) {
			// colname = form.attr("id") + 'List';
			colname = 'checkedList';
			form.find("input[type='hidden'][name='" + colname + "']").remove();
			$(
					"<input type='hidden' name='" + colname + "' value=\""
							+ datastr + "\" />").appendTo(form);
		} else {
			$("#" + colname).val(datastr);
		}
	}

}

/**
 * Desc: 通用表单校验，使用$("#id").validForm() <br>
 * 
 * <br>
 * 
 * Date: 2010-09-03 <br>
 * 
 */
jQuery.fn.validForm = function() {
	AI.Validator.validForm(jQuery(this));
}
