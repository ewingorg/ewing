package com.ewing.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.system.service.SysOrgService;
import com.ewing.core.app.action.base.ActionException;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.bean.OrgStructTreeObject;
import com.ewing.core.app.constant.OrgQueryType;
import com.ewing.core.json.JsonUtil;

public class SysOrgAction extends BaseAction {
	private static Logger logger = Logger.getLogger(SysOrgAction.class);
	@Resource
	private SysOrgService sysOrgService;

	/**
	 * 查找组织架构的树
	 * 
	 * @throws ActionException
	 */
	public void queryOrgStructTree() throws ActionException {
		try {
			String queryType = request.getParameter("queryType");
			
			OrgStructTreeObject treeObject = sysOrgService.queryOrgStructTree(OrgQueryType.getOrgQueryType(queryType));
			String json = JsonUtil.tranBean2String(treeObject.getChildren())
					.toString();
			if (!json.startsWith("[") && !json.endsWith("]")) {
				json += "[" + json + "]";
			}
			logger.debug(json);
			response.getWriter().write(json);

		} catch (Exception e) {
			logger.error(e, e);
			throw new ActionException(e);
		}
	}

}
