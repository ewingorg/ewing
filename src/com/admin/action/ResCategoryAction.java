package com.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.admin.constant.SysParamCode;
import com.admin.dto.ResCategoryTreeDto;
import com.admin.model.SysParam;
import com.admin.model.WebResource;
import com.admin.model.WebResourceCategory;
import com.admin.service.ResCategoryService;
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
public class ResCategoryAction extends BaseAction {

	private static Logger logger = Logger.getLogger(ResCategoryAction.class);
	private static final String LIST_PAGE = "/admin/rescategory/categorylist.html";
	private static final String EDIT_FORM = "/admin/rescategory/categoryform.html";
	@Resource
	private ResCategoryService resCategoryService;

	public void queryCatagoryTree() {
		ResponseData responseData = null;
		try {
			List<ResCategoryTreeDto> categoryList = resCategoryService
					.queryCatagoryTree();
			responseData = ResponseUtils.success("保存成功！");
			responseData.setResult(categoryList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);
	}

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
			Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null
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
				WebResourceCategory webResource = findOne(Integer.valueOf(id),
						WebResourceCategory.class);
				WebResourceCategory parentNode = findOne(
						Integer.valueOf(webResource.getParentid()),
						WebResourceCategory.class);
				webResource.setParentName(parentNode.getName());
				dataModel.put("bean", webResource);
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
	 * 显示新建表单
	 */
	public void showNewForm() {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		try {
			String parentid = request.getParameter("parentid");
			if (!StringUtils.isEmpty(parentid)) {
				WebResourceCategory parentNode = findOne(
						Integer.valueOf(parentid), WebResourceCategory.class);
				Integer nextLevel = Integer.valueOf(parentNode.getLevel()) + 1;
				WebResourceCategory newNode = new WebResourceCategory();
				newNode.setParentid(parentNode.getId());
				newNode.setLevel(nextLevel.toString());
				newNode.setParentName(parentNode.getName());
				dataModel.put("bean", newNode);
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
	 * 保存导航栏
	 */
	public void save() {
		ResponseData responseData = null;
		try {
			String id = request.getParameter("id");
			WebResourceCategory webResource = new WebResourceCategory();
			this.buildPageData(webResource);
			if (!StringUtils.isEmpty(id)) {
				webResource.setId(Integer.valueOf(id));
				baseModelService.update(webResource);
			} else {
				baseModelService.save(webResource);
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
				WebResourceCategory webResource = baseModelService.findOne(
						Integer.valueOf(id), WebResourceCategory.class);
				if (webResource != null && webResource.getParentid() != -1)
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
