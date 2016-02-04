/**
 * 
 */
package com.ewing.web.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.web.model.WebGroup;
import com.ewing.busi.web.model.WebTemplate;
import com.ewing.busi.web.service.GroupService;
import com.ewing.busi.web.service.RelResService;
import com.ewing.busi.web.service.TemplateService;
import com.ewing.constant.SysParamCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 业务网站的模板管理
 * 
 * @author tanson lam
 * @creation 2015年3月1日
 */
public class TemplateAction extends BaseAction {

	private static Logger logger = Logger.getLogger(ResAction.class);
	private static final String LIST_PAGE = "/admin/busitemplate/templatelist.html";
	private static final String EDIT_FORM = "/admin/busitemplate/templateform.html";
	@Resource
	private GroupService groupService;
	@Resource
	private RelResService relResService;
	@Resource
	private TemplateService templateService;

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
			String condition = "isEff ='" + IsEff.EFFECTIVE + "'"
					+ bulidConditionSql();
			PageBean pageBean = baseModelService.pageQuery(condition,
					bulidOrderBySql(), pageSize, page, WebTemplate.class);
			groupService.translateGroupName(
					(List<WebTemplate>) pageBean.getResult(), false);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupCode = groupService.getGroupParamList();
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupKeyCode", groupCode);
			dataModel.put("pageBean", pageBean);
			dataModel.put("pageUrl",
					getPaginationUrl("/Admin-Template-show.action"));

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
				WebTemplate webTemplate = findOne(Integer.valueOf(id),
						WebTemplate.class);
				webTemplate = groupService.translateGroupName(webTemplate);
				dataModel.put("bean", webTemplate);
			}
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupCode = groupService.getGroupParamList();
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupKeyCode", groupCode);
			render(EDIT_FORM, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 保存
	 */
	public void save() {
		ResponseData responseData = null;
		String failMes = "保存失败！";
		try {
			String id = request.getParameter("id");
			String templatePath = request.getParameter("templatePath");
			WebTemplate webTemplate = new WebTemplate();
			this.buildPageData(webTemplate);
			boolean isDuplicate = templateService.isDuplicateTemplatePath(id,
					templatePath);
			if (isDuplicate) {
				failMes += "重复的模板路径";
				throw new Exception(failMes);
			}
			if (!StringUtils.isEmpty(id)) {
				webTemplate.setId(Integer.valueOf(id));
				baseModelService.update(webTemplate);
			} else {
				baseModelService.save(webTemplate);
			}
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail(failMes);
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
				WebTemplate webTemplate = baseModelService.findOne(
						Integer.valueOf(id), WebTemplate.class);
				if (webTemplate != null)
					baseModelService.delete(webTemplate);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}
}
