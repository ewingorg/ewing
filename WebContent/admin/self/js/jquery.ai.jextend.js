/**!
 * AI.Jquery Extend Lib  <br>
 * File Name: AI.JExtend.js <br>
 * Desc: 这个是对Jquery的扩展，一般页面上无需直接使用此方法 <br>
 * 		 使用JS库中AI命名空间中的相关扩展即可 <br>
 * Author: tanzy <br>
 * Copyright (c) 2010 by Asiainfo-Linkage.COM, Inc. All Rights Reserved. <br>
 *
 * Date: 2010-09-10 <br>
 * Reversion: 1.0 <br>
 */

if (!this.AI) {
	var AI = {};
}

/**
 * Desc: 传参中的Jquery对象或ID/#ID方式转换/this <br>
 * @param {} key 
 * @return {}
 */
jQuery.AI$OBJ = function(key) {
	if (key instanceof jQuery) {
		return key;
	} else if (typeof key == "object") {
		return jQuery(key);
	} else if (typeof key == "string") {
		if (key.indexOf("#") === 0) {
			return jQuery(key);
		} else if (key.indexOf("$") === 0) {
			return jQuery(key.substring(1));
		}
		return jQuery("#" + key);
	} else {
		alert("请传入JQuery/DOM对象或者传入该对象的ID或#ID或'$JQuery选择器'");
		return undefined;
	}
}
/**
 * Desc: 对Cookie的相关操作  <br>
 * @param {} key  		 Cookie的key
 * @param {} value		 Cookie的key的值
 * @param {} options	 Cookie的各属性
 * @return {}
 */
jQuery.CK$OP = function(key, value, options) {
	if (arguments.length > 1 && (value === null || typeof value !== "object")) {
		options = jQuery.extend({}, options);

		if (value === null) {
			options.expires = -1;
		}

		if (typeof options.expires === 'number') {
			var days = options.expires, t = options.expires = new Date();
			t.setDate(t.getDate() + days);
		}

		return (document.cookie = [
				encodeURIComponent(key),
				'=',
				options.raw ? String(value) : encodeURIComponent(String(value)),
				options.expires
						? '; expires=' + options.expires.toUTCString()
						: '', // use expires attribute, max-age is not supported by IE
				options.path ? '; path=' + options.path : '',
				options.domain ? '; domain=' + options.domain : '',
				options.secure ? '; secure' : ''].join(''));
	}
	options = value || {};
	var result, decode = options.raw ? function(s) {
		return s;
	} : decodeURIComponent;
	return (result = new RegExp('(?:^|; )' + encodeURIComponent(key)
			+ '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};
