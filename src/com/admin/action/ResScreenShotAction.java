package com.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.admin.constant.GroupType;
import com.admin.constant.SysParamCode;
import com.admin.model.SysParam;
import com.admin.model.WebBlock;
import com.admin.model.WebResourceScreenshot;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.jdbc.util.PageBean;
import com.google.gson.reflect.TypeToken;

/**
 * 网站资源分組展示类
 * 
 * @author tanson lam
 * 
 */
public class ResScreenShotAction extends BaseAction {

	private static Logger logger = Logger.getLogger(MainAction.class);
	private static final String LIST_PAGE = "/admin/res/screenshot/screenshotlist2.html";
	private static final String EDIT_FORM = "/admin/res/screenshot/screenshotedit.html";

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
					bulidOrderBySql(), pageSize, page,
					WebResourceScreenshot.class);
			pageBean.setPageUrl(getPaginationUrl("/Admin-ResScreenShot-show.action"));
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("resparamPageBean", pageBean);
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
				WebResourceScreenshot webResourceScreenshot = findOne(
						Integer.valueOf(id), WebResourceScreenshot.class);
				dataModel.put("resparamBean", webResourceScreenshot);
			}
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
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
				WebResourceScreenshot webResourceScreenshot = baseModelService
						.findOne(Integer.valueOf(id),
								WebResourceScreenshot.class);
				if (webResourceScreenshot != null)
					baseModelService.delete(webResourceScreenshot);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}

	
	/**
	 * 保存截图
	 */
	public void save() {
		ResponseData responseData = null;
		try {
			String id = request.getParameter("id");
			String resourceId = request.getParameter("resourceId");
			WebResourceScreenshot webResourceScreenshot = new WebResourceScreenshot();
			this.buildPageData(webResourceScreenshot); 
			if (!StringUtils.isEmpty(id)) {
				webResourceScreenshot.setId(Integer.valueOf(id));
				baseModelService.update(webResourceScreenshot);
			} else {
				baseModelService.save(webResourceScreenshot);
			}
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);
	}
	
	
	/**
	 * 保存截图
	 */
	public void saveScreenshotList() {
		ResponseData responseData = null;
		try {
			List<WebResourceScreenshot> list = gson.fromJson(
					(String) request.getParameter("screenList"),
					new TypeToken<List<WebResourceScreenshot>>() {
					}.getType());
			Integer resourceId = Integer.valueOf(request
					.getParameter("resouceId"));
			if (list.isEmpty())
				throw new Exception("截图为空！");
			for (WebResourceScreenshot webResourceScreenshot : list) {
				webResourceScreenshot.setResourceId(resourceId);
				if (webResourceScreenshot.getId() != null
						&& webResourceScreenshot.getId() > 0) {
					baseModelService.update(webResourceScreenshot);
				} else {
					baseModelService.save(webResourceScreenshot);
				}
			}
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);

	}
}
