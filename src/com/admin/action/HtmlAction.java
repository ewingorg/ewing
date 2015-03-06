/**
 * 
 */
package com.admin.action;

import java.util.HashMap;

import com.core.app.action.base.BaseAction;

/**
 * 模板重定向展示类
 * 
 * @author tanson lam 
 * @creation 2015年1月10日
 */
public class HtmlAction extends BaseAction {
	
	public void redirect() {
		String page = request.getParameter("page");
		render(page, new HashMap());
	}

}
