package com.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.admin.constant.AttrConstant;
import com.admin.constant.GroupType;
import com.admin.constant.SysParamCode;
import com.admin.model.SysParam;
import com.admin.model.WebResource;
import com.admin.model.WebResourceAttr;
import com.admin.model.WebResourceScreenshot;
import com.admin.service.TemplateService;
import com.admin.service.WebResourceScreenService;
import com.admin.service.WebResourceService;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.jdbc.util.PageBean;

/**
 * 网站资源展示类
 * 
 * @author tanson lam
 * @creation 2015年1月18日
 */
public class ResAction extends BaseAction {

	private static Logger logger = Logger.getLogger(ResAction.class);
	private static final String LIST_PAGE = "/admin/res/reslist.html";
	private static final String EDIT_FORM = "/admin/res/resform.html";
	@Resource
	private TemplateService templateService;
	@Resource
	private WebResourceService webResourceService;
	@Resource
	private WebResourceScreenService webResourceScreenService;
	/**
	 * 查詢列表
	 */
	public void show() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			Integer page = StringUtils.isEmpty(pageStr) ? null : Integer
					.valueOf(pageStr);
			Integer pageSize = StringUtils.isEmpty(pageSizeStr)
					? null
					: Integer.valueOf(pageSizeStr);
			String condition = bulidConditionSql();
			PageBean pageBean = baseModelService.pageQuery(condition,
					bulidOrderBySql(), pageSize, page, WebResource.class);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			pageBean.setPageUrl(getPaginationUrl("/Admin-Res-show.action"));
			dataModel.put("pageBean", pageBean);

			render(LIST_PAGE, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 显示编辑表单
	 */
	public void showEditForm() {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			if (!StringUtils.isEmpty(id)) {
				WebResource webResource = findOne(Integer.valueOf(id),
						WebResource.class); 
				dataModel.put("bean", webResource); 
			}
			List<SysParam> templateType = templateService.getResTemplates();
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF); 
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("templateType", templateType);
			dataModel.put("iseffCode", iseffCode);
			render(EDIT_FORM, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 保存导航栏
	 */
	public void save() {
		ResponseData responseData = null;
		try {
			String id = request.getParameter("id");
			WebResource webResource = new WebResource();
			this.buildPageData(webResource);

			List<WebResourceAttr> attrList = new ArrayList<WebResourceAttr>();

			Map paramMap = request.getParameterMap();
			for (Iterator itor = paramMap.keySet().iterator(); itor.hasNext();) {
				Object key = itor.next();
				Object object = paramMap.get(key);

				if (key instanceof String
						&& key.toString().startsWith(
								AttrConstant.ATTRKEY_PREFIX) && object != null) {
					if (object.getClass().isArray()) {
						Object[] sValue = (Object[]) object;
						String value = sValue[0].toString();
						if (value.trim().isEmpty())
							continue;
						WebResourceAttr attr = new WebResourceAttr();
						attr.setAttrKey(key.toString().replace(
								AttrConstant.ATTRKEY_PREFIX, ""));
						attr.setAttrValue(value);
						attrList.add(attr);
					}
				}
			}

			if (!StringUtils.isEmpty(id)) {
				webResource.setId(Integer.valueOf(id));
				webResourceService.editResource(webResource, attrList);
			} else {
				webResourceService.saveResource(webResource, attrList);
			}
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);
	}

	/**
	 * 删除导航栏
	 */
	public void delete() {
		ResponseData responseData = null;
		try {
			String selectItems = request.getParameter("selectItems");
			if (selectItems.isEmpty()) {
				responseData = ResponseUtils.fail("没有选中的数据！");
				this.outResult(responseData);
				return;
			}
			String[] selectArr = selectItems.split(",");
			for (String id : selectArr) {
				WebResource webResource = baseModelService.findOne(
						Integer.valueOf(id), WebResource.class);
				if (webResource != null)
					baseModelService.delete(webResource);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}
}
