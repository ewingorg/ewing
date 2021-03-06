package com.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.admin.constant.AttrConstant;
import com.admin.model.SysParam;
import com.admin.model.WebAttrConf;
import com.admin.model.WebResourceAttr;
import com.admin.service.WebAttrConfService;
import com.admin.service.WebResourceService;
import com.core.app.action.base.BaseAction;
import com.util.StringUtil;

/**
 * 模板资源的属性
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
public class AttrConfAction extends BaseAction {

	private static Logger logger = Logger.getLogger(AttrConfAction.class);
	private static final String DYNAMIC_ATTR_FORM = "/admin/attr/dynamicform.html";
	@Resource
	private WebAttrConfService webAttrConfService;
	@Resource
	private WebResourceService webResourceService;

	/**
	 * 显示动态的资源属性编辑表单
	 */
	public void showDynamicForm() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			Integer templateId = getIntegerParameter("templateId");
			if (templateId == null)
				return;
			Integer resourceId = getIntegerParameter("resourceId");
			List<WebResourceAttr> resAttrs = null;
			if (resourceId != null) {
				resAttrs = webResourceService.getResourceAttrs(resourceId);
			}
			List<WebAttrConf> attrList = webAttrConfService
					.getTemplateAttrs(templateId);
			for (WebAttrConf webAttrConf : attrList) {
				// 设置属性的值
				if (resAttrs != null && !resAttrs.isEmpty()) {
					for (WebResourceAttr resAttr : resAttrs) {
						if (resAttr.getAttrKey().equals(
								webAttrConf.getAttrKey()))
							webAttrConf.setAttrValue(resAttr.getAttrValue());
					}
				}
				webAttrConf.setAttrKey(AttrConstant.ATTRKEY_PREFIX
						+ webAttrConf.getAttrKey());
				if (StringUtil.isEmpty(webAttrConf.getParamCode()))
					continue;
				String paramCode = webAttrConf.getParamCode();
				List<SysParam> param = sysParamService.getSysParam(paramCode);
				webAttrConf.setParamCodeList(param);

			}

			dataModel.put("attrList", attrList);
			render(DYNAMIC_ATTR_FORM, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
