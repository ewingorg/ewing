package com.ewing.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.web.model.WebBlock;
import com.ewing.busi.web.service.GroupService;
import com.ewing.common.constant.GroupType;
import com.ewing.common.constant.SysParamCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.ewing.core.jdbc.util.PageBean;

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
					bulidOrderBySql(), pageSize, page, WebBlock.class);
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
				WebBlock webCategory = findOne(Integer.valueOf(id),
						WebBlock.class);
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
			WebBlock webCategory = new WebBlock();
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
				WebBlock webCategory = baseModelService.findOne(
						Integer.valueOf(id), WebBlock.class);
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
