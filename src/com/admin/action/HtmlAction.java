/**
 * 
 */
package com.admin.action;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.template.FreeMarkerTool;

/**
 * 模板重定向展示类
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */
public class HtmlAction extends BaseAction {
	private static Logger logger = Logger.getLogger(HtmlAction.class);

	public void redirect() {
		String page = request.getParameter("page");
		render(page, new HashMap());
	}

	public void response() throws IOException {
		String htmlResult = "";
		try {
			String page = request.getParameter("page");
			htmlResult = FreeMarkerTool.getSingleton().getTemplateResult(page,
					null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		response.getWriter().write(htmlResult);
	}

}
