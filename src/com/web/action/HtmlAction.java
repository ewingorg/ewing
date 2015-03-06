/**
 * 
 */
package com.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.admin.service.CategoryService;
import com.admin.service.TemplateService;
import com.core.app.action.base.BaseAction;
import com.core.jdbc.DaoException;

/**
 * 模板重定向展示类
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */
public class HtmlAction extends BaseAction {

	@Resource
	private CategoryService categoryService;
	@Resource
	private TemplateService templateService;
	public void redirect() {
		String page = request.getParameter("page");
		Map<String, Object> dataModel = new HashMap<String, Object>();
		try {
			List<String> groupKeyList = templateService
					.getTemplateRelGroupKeys(page);
			categoryService.loadCategoryList(groupKeyList, dataModel);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		render(page, dataModel);
	}

}
