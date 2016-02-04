package com.ewing.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.category.model.WebCatagoryParam;
import com.ewing.busi.category.service.WebCategoryParamService;
import com.ewing.busi.resource.dto.WebResourceParamGroup;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.model.WebResourceParam;
import com.ewing.busi.resource.service.WebResourceParamService;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.busi.system.model.SysParam;
import com.ewing.constant.SysParamCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.google.gson.reflect.TypeToken;

/**
 * 网站资源分組展示类
 * 
 * @author tanson lam
 * 
 */
public class ResParamAction extends BaseAction {

	private static Logger logger = Logger.getLogger(MainAction.class);
	private static final String LIST_PAGE = "/admin/res/param/resparamlist.html";
	private static final String EDIT_FORM = "/admin/res/param/resparamedit.html";
	@Resource
	private WebCategoryParamService webCategoryParamService;

	@Resource
	private WebResourceParamService webResourceParamService;
	@Resource
	private WebResourceService webResourceService;

	/**
	 * 查詢列表
	 */
	public void show() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			Integer resourceId = getIntegerParameter("resourceId");
			List<WebResourceParamGroup> resGroupList = webResourceParamService
					.getResParamList(resourceId);
			dataModel.put("resParamGroupList", resGroupList);
			dataModel.put("resourceId", resourceId);
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
			Integer id = getIntegerParameter("id");
			Integer categoryId = getIntegerParameter("categoryId");
			List<WebCatagoryParam> categoryParams = webCategoryParamService
					.getDefindParams(categoryId);
			if (id != null) {
				WebResourceParam webResourceParam = findOne(
						Integer.valueOf(id), WebResourceParam.class);
				dataModel.put("resParamBean", webResourceParam);
			}
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("categoryParams", categoryParams);
			render(EDIT_FORM, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 删除
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
				WebResourceParam webResourceParam = baseModelService.findOne(
						Integer.valueOf(id), WebResourceParam.class);
				if (webResourceParam != null)
					baseModelService.delete(webResourceParam);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}

	/**
	 * 保存
	 */
	public void saveParamList() {
		ResponseData responseData = null;
		try {
			System.out.println(request.getParameter("resparamList"));
			List<WebResourceParam> resparamList = gson.fromJson(
					(String) request.getParameter("resparamList"),
					new TypeToken<List<WebResourceParam>>() {
					}.getType());
			Integer resourceId = Integer.valueOf(request
					.getParameter("resourceId")); 
			webResourceParamService.saveParamList(resourceId, resparamList);
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！"+e.getMessage());
		}
		this.outResult(responseData);

	}

}
