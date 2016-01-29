/**
 * 
 */
package com.web.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.admin.service.WebResourceService;
import com.core.app.action.base.BaseAction;

/**
 * 模板重定向展示类
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */
public class ResourceAction extends BaseAction {

	@Resource
	private WebResourceService webResourceService;

	/**
	 * 查詢列表
	 */
	public void show() {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		render("/shop/res/resbox.html", dataModel);

	}
}
