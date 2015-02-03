package com.admin.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.admin.constant.SysParamCode;
import com.admin.model.SysParam;
import com.admin.model.WebRelResource;
import com.admin.service.RelResService;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.app.constant.IsEff;
import com.core.jdbc.util.PageBean;
import com.util.CommonUtil;

/**
 * 分类和资源关系展示类
 * 
 * @author tanson lam
 * @creation 2015年1月29日
 */
public class RelResAction extends BaseAction {

	private static Logger logger = Logger.getLogger(ResAction.class);
	private static final String LIST_PAGE = "/admin/relres/relreslist.html";
	private static final String EDIT_FORM = "/admin/relres/relresform.html";

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
			String condition = bulidConditionSql();
			PageBean pageBean = baseModelService.pageQuery(condition,
					bulidOrderBySql(), pageSize, page, WebRelResource.class);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			dataModel.put("iseffCode", iseffCode);
			pageBean.setPageUrl(getPaginationUrl("/Admin-RelRes-show.action"));
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
				WebRelResource relResource = findOne(Integer.valueOf(id),
						WebRelResource.class);
				dataModel.put("bean", relResource);
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
			WebRelResource relResource = new WebRelResource();
			this.buildPageData(relResource);
			if (!StringUtils.isEmpty(id)) {
				relResource.setId(Integer.valueOf(id));
				baseModelService.update(relResource);
			} else {
				baseModelService.save(relResource);
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
				WebRelResource relResource = baseModelService.findOne(
						Integer.valueOf(id), WebRelResource.class);
				if (relResource != null)
					baseModelService.delete(relResource);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}

	/**
	 * 绑定分类和资源的关系
	 */
	public void bindRelResource() {
		ResponseData responseData = null;
		try {
			String resourceIdsStr = request.getParameter("resourceIds");
			Integer[] resourceIds = null;
			if (!StringUtils.isEmpty(resourceIdsStr)) {
				String[] resourceIdArr = resourceIdsStr.split(",");
				resourceIds = CommonUtil.strArray2InArray(resourceIdArr);
			}
			WebRelResource relResource = new WebRelResource();
			this.buildPageData(relResource);
			relResource.setIseff(IsEff.EFFECTIVE);
			relResService.bindRelResource(relResource.getCategoryId(),
					resourceIds, relResource.getTempalte(),
					relResource.getIseff());
			responseData = ResponseUtils.success("成功绑定分类和资源关系！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("绑定失败！");
		}
		this.outResult(responseData);
	}

	/**
	 * 解开分类和资源的关系
	 */
	public void unBindRelResource() {
		ResponseData responseData = null;
		try {
			String resourceIdsStr = request.getParameter("resourceIds");
			String categoryIdStr = request.getParameter("categoryId");
			Integer[] resourceIds = null;
			Integer categoryId = null;
			if (!StringUtils.isEmpty(resourceIdsStr)) {
				String[] resourceIdArr = resourceIdsStr.split(",");
				resourceIds = CommonUtil.strArray2InArray(resourceIdArr);
			}
			if (!StringUtils.isEmpty(categoryIdStr))
				categoryId = Integer.valueOf(categoryIdStr);
			relResService.unBindRelResource(categoryId, resourceIds);
			responseData = ResponseUtils.success("成功解除分类和资源关系！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("解除失败！");
		}
		this.outResult(responseData);
	}

}
