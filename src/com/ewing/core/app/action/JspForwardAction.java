/**
 * 
 */
package com.ewing.core.app.action;

import org.apache.axis.utils.StringUtils;

import com.ewing.core.app.action.base.BaseAction;

/**
 * @author Administrator
 * 
 */
public class JspForwardAction extends BaseAction {

	public void forward() {
		String jumpPage = request.getParameter("jumpPage");
		String container = request.getParameter("container");
		if (!StringUtils.isEmpty(container)) {
			request.setAttribute("includejsp", jumpPage);
			jumpPage = container + ".jsp"; 

		}
		forward(jumpPage);
	}
}
