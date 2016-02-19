package com.ewing.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.category.dto.ResCategoryTreeDto;
import com.ewing.busi.category.model.WebCategory;
import com.ewing.busi.category.service.ResCategoryService;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.system.model.SysParam;
import com.ewing.common.constant.SysParamCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.ewing.core.jdbc.util.PageBean;

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
    private static final String SELECT_FORM = "/admin/rescategory/selectcategory.html";
    @Resource
    private ResCategoryService resCategoryService;

    /**
     * 获取分类的树结构
     */
    public void queryCatagoryTree() {
        ResponseData responseData = null;
        try {
            List<ResCategoryTreeDto> categoryList = resCategoryService
                    .queryCatagoryTree(getLoginUserId());
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
            String categoryId = request.getParameter("categoryId");
            String categoryName = request.getParameter("categoryName");
            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("categoryId", categoryId);
            dataModel.put("categoryName", categoryName);
            render(LIST_PAGE, dataModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 显示选择资源分类的页面
     */
    public void selectCategory() {
        render(SELECT_FORM, null);
    }

    /**
     * 显示编辑表单
     */
    public void showEditForm() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        try {
            String id = request.getParameter("id");
            if (!StringUtils.isEmpty(id)) {
                WebCategory webResource = resCategoryService.findOne(getLoginUserId(),
                        Integer.valueOf(id));
                WebCategory parentNode = resCategoryService.findOne(getLoginUserId(),
                        Integer.valueOf(webResource.getParentid()));
                webResource.setParentName(parentNode.getName());
                dataModel.put("bean", webResource);
            }
            List<SysParam> iseffCode = sysParamService.getSysParam(SysParamCode.ISEFF);
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
                WebCategory parentNode = findOne(Integer.valueOf(parentid), WebCategory.class);
                Integer nextLevel = Integer.valueOf(parentNode.getLevel()) + 1;
                WebCategory newNode = new WebCategory();
                newNode.setParentid(parentNode.getId());
                newNode.setLevel(nextLevel.toString());
                newNode.setParentName(parentNode.getName());
                dataModel.put("bean", newNode);
            }
            List<SysParam> iseffCode = sysParamService.getSysParam(SysParamCode.ISEFF);
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
            WebCategory webCatagory = new WebCategory();
            this.buildPageData(webCatagory);
            webCatagory.setUserId(getLoginUserId());
            if (!StringUtils.isEmpty(id)) {
                webCatagory.setId(Integer.valueOf(id));
                baseModelService.update(webCatagory);
            } else {
                baseModelService.save(webCatagory);
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
                WebCategory webResource = resCategoryService.findOne(getLoginUserId(),
                        Integer.valueOf(id));
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
