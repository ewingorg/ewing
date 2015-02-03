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
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.jdbc.util.PageBean;

/**
 * 导航栏展示类
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */
public class NavAction extends BaseAction {

	private static Logger logger = Logger.getLogger(MainAction.class);
	private static final String LIST_PAGE = "/admin/nav/navlist.html";
	private static final String EDIT_FORM = "/admin/nav/navform.html";
	@Resource
	private GroupService groupService;

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
			String condition = "groupType ='" + GroupType.NAV.getCode() + "'"
					+ bulidConditionSql();
			PageBean pageBean = baseModelService.pageQuery(condition,
					bulidOrderBySql(), pageSize, page, WebCategory.class);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupCode = groupService
					.getGroupParamList(GroupType.NAV);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupKeyCode", groupCode);
			pageBean.setPageUrl(getPaginationUrl("/Admin-Nav-show.action"));
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
				WebCategory webCategory = findOne(Integer.valueOf(id),
						WebCategory.class);
				dataModel.put("bean", webCategory);
			}
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupCode = groupService
					.getGroupParamList(GroupType.NAV);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupKeyCode", groupCode);
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
			WebCategory webCategory = new WebCategory();
			this.buildPageData(webCategory);
			webCategory.setParentId(0);
			webCategory.setGroupType(GroupType.NAV.getCode().toString());
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
