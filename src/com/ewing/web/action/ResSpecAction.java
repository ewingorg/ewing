package com.ewing.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.resource.dto.ResSpecComparatorUtil;
import com.ewing.busi.resource.dto.WebResourceSpecGroup;
import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.busi.resource.model.WebResourceSpec;
import com.ewing.busi.resource.service.WebResourcePriceService;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.busi.resource.service.WebResourceSpecService;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.google.gson.reflect.TypeToken;

public class ResSpecAction extends BaseAction {

	private static Logger logger = Logger.getLogger(MainAction.class);
	private static final String LIST_PAGE = "/admin/res/spec/resspeclist.html";

	@Resource
	private WebResourceSpecService webResourceSpecService;
	@Resource
	private WebResourceService webResourceService;
	@Resource
	private WebResourcePriceService webResourcePriceService;

	/**
	 * 查詢列表
	 */
	public void show() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			Integer resourceId = getIntegerParameter("resourceId");
			List<WebResourceSpecGroup> resSpecGroupList = webResourceSpecService
					.getConfigureSpecs(resourceId);
			dataModel.put("resSpecGroupList", resSpecGroupList);
			dataModel.put("resourceId", resourceId);
			render(LIST_PAGE, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 保存
	 */
	public void saveSpec() {
		ResponseData responseData = null;
		try {
			List<WebResourceSpec> resSpecList = gson.fromJson(
					(String) request.getParameter("resSpecList"),
					new TypeToken<List<WebResourceSpec>>() {
					}.getType());

			Integer resourceId = Integer.valueOf(request
					.getParameter("resourceId")); 
			webResourceSpecService.saveSpecList(resourceId, resSpecList);
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);

	}
	

	/**
	 * 保存
	 */
	public void hasChangeSpec() {
		ResponseData responseData = null;
		try {
			List<WebResourceSpec> resSpecList = gson.fromJson(
					(String) request.getParameter("resSpecList"),
					new TypeToken<List<WebResourceSpec>>() {
					}.getType());

			Integer resourceId = Integer.valueOf(request
					.getParameter("resourceId"));
			if (resSpecList.isEmpty())
				throw new Exception("资源规格为空！");
			webResourceSpecService.saveSpecList(resourceId, resSpecList);
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);

	}

}
