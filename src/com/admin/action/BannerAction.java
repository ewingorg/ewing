package com.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.admin.constant.GroupType;
import com.admin.constant.SysParamCode;
import com.admin.model.SysParam;
import com.admin.model.WebCategory;
import com.admin.service.GroupService;
import com.admin.service.RelResService;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.jdbc.util.PageBean;

/**
 * 网站栏目展示类
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */
public class BannerAction extends BaseAction {

	private static Logger logger = Logger.getLogger(MainAction.class);
	private static final String LIST_PAGE = "/admin/banner/bannerlist.html";
	private static final String EDIT_FORM = "/admin/banner/bannerform.html";
	private static final String RESMNG_PAGE = "/admin/banner/resmng.html";
	private static final String BINDRESLIST_PAGE = "/admin/banner/bindreslist.html";
	private static final String UNBINDRESLIST_PAGE = "/admin/banner/unbindreslist.html";
	@Resource
	private GroupService groupService;
	@Resource
	private RelResService relResService;

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
			String condition = "groupType ='" + GroupType.BANNER.getCode()
					+ "'" + bulidConditionSql();
			PageBean pageBean = baseModelService.pageQuery(condition,
					bulidOrderBySql(), pageSize, page, WebCategory.class);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupCode = groupService
					.getGroupParamList(GroupType.BANNER);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupKeyCode", groupCode);
			dataModel.put("pageBean", pageBean);
			dataModel.put("pageUrl",
					getPaginationUrl("/Admin-Banner-show.action"));

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
				WebCategory webCategory = findOne(Integer.valueOf(id),
						WebCategory.class);
				dataModel.put("bean", webCategory);
			}
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupCode = groupService
					.getGroupParamList(GroupType.BANNER);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupKeyCode", groupCode);
			render(EDIT_FORM, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 显示分类与资源关系管理列表
	 */
	public void showResList() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			String categoryIdStr = request.getParameter("categoryId");
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			Integer page = StringUtils.isEmpty(pageStr) ? null : Integer
					.valueOf(pageStr);
			Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null
					: Integer.valueOf(pageSizeStr);
			Integer categoryId = StringUtils.isEmpty(categoryIdStr) ? null
					: Integer.valueOf(categoryIdStr);
			PageBean bindPageBean = relResService.listRelResourceByCategory(
					categoryId, null, pageSize, page);
			PageBean unbindPageBean = relResService
					.listNotRelResourceByCategory(categoryId, null, pageSize,
							page);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("categoryId", categoryId);
			bindPageBean
					.setPageUrl(getPaginationUrl("/Admin-Banner-showRelResourceByCategory.action"));
			dataModel.put("bindPageBean", bindPageBean);
			unbindPageBean
					.setPageUrl(getPaginationUrl("/Admin-Banner-showNotRelResourceByCategory.action"));
			dataModel.put("unbindPageBean", unbindPageBean);

			render(RESMNG_PAGE, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 显示绑定资源的列表
	 */
	public void showRelResourceByCategory() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			String categoryIdStr = request.getParameter("categoryId");
			String resourceName = request.getParameter("resourceName");
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			Integer page = StringUtils.isEmpty(pageStr) ? null : Integer
					.valueOf(pageStr);
			Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null
					: Integer.valueOf(pageSizeStr);
			Integer categoryId = StringUtils.isEmpty(categoryIdStr) ? null
					: Integer.valueOf(categoryIdStr);
			PageBean bindPageBean = relResService.listRelResourceByCategory(
					categoryId, resourceName, pageSize, page);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("categoryId", categoryId);
			bindPageBean
					.setPageUrl(getPaginationUrl("/Admin-Banner-showRelResourceByCategory.action"));
			dataModel.put("bindPageBean", bindPageBean);
			render(BINDRESLIST_PAGE, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 显示未绑定资源的列表
	 */
	public void showNotRelResourceByCategory() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			String categoryIdStr = request.getParameter("categoryId");
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			String resourceName = request.getParameter("resourceName");
			Integer page = StringUtils.isEmpty(pageStr) ? null : Integer
					.valueOf(pageStr);
			Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null
					: Integer.valueOf(pageSizeStr);
			Integer categoryId = StringUtils.isEmpty(categoryIdStr) ? null
					: Integer.valueOf(categoryIdStr);
			PageBean unbindPageBean = relResService
					.listNotRelResourceByCategory(categoryId, resourceName,
							pageSize, page);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("categoryId", categoryId);
			unbindPageBean
					.setPageUrl(getPaginationUrl("/Admin-Banner-showNotRelResourceByCategory.action"));
			dataModel.put("unbindPageBean", unbindPageBean);
			render(UNBINDRESLIST_PAGE, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 保存
	 */
	public void save() {
		ResponseData responseData = null;
		try {
			String id = request.getParameter("id");
			WebCategory webCategory = new WebCategory();
			this.buildPageData(webCategory);
			webCategory.setParentId(0);
			webCategory.setGroupType(GroupType.BANNER.getCode().toString());
			if (!StringUtils.isEmpty(id)) {
				webCategory.setId(Integer.valueOf(id));
				baseModelService.update(webCategory);
			} else {
				baseModelService.save(webCategory);
			}
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);
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
				WebCategory webCategory = baseModelService.findOne(
						Integer.valueOf(id), WebCategory.class);
				if (webCategory != null)
					baseModelService.delete(webCategory);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}

}
