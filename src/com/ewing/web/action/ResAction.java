package com.ewing.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.category.model.WebCategory;
import com.ewing.busi.category.service.ResCategoryService;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.resource.model.WebResourceAttr;
import com.ewing.busi.resource.service.WebResourceScreenService;
import com.ewing.busi.resource.service.WebResourceService;
import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.web.service.TemplateService;
import com.ewing.constant.AttrConstant;
import com.ewing.constant.SysParamCode;
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
public class ResAction extends BaseAction {

    private static Logger logger = Logger.getLogger(ResAction.class);
    private static final String LIST_PAGE = "/admin/res/reslist.html";
    private static final String EDIT_FORM = "/admin/res/resform.html";
    @Resource
    private TemplateService templateService;
    @Resource
    private WebResourceService webResourceService;
    @Resource
    private WebResourceScreenService webResourceScreenService;
    @Resource
    private ResCategoryService resCategoryService;

    /**
     * 查詢列表
     */
    public void show() {
        try {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            String pageStr = request.getParameter("page");
            String pageSizeStr = request.getParameter("pageSize");
            Integer page = StringUtils.isEmpty(pageStr) ? null : Integer.valueOf(pageStr);
            Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null : Integer
                    .valueOf(pageSizeStr);
            String condition = bulidConditionSql();
            PageBean pageBean = webResourceService.pageQueryResource(getLoginUserId(), condition,
                    bulidOrderBySql(), pageSize, page);
            pageBean.setPageUrl(getPaginationUrl("/Admin-Res-show.action"));
            dataModel.put("pageBean", pageBean);

            dataModel.put("iseffCode", sysParamService.getSysParam(SysParamCode.ISEFF));
            dataModel.put("resHotType", sysParamService.getSysParam(SysParamCode.RES_HOT_TYPE));
            dataModel.put("resOnlineType",
                    sysParamService.getSysParam(SysParamCode.RES_ONLINE_TYPE));

            if (request.getParameter("_QUERY_n_eq_category_id") != null) {
                Integer queryCategory = getIntegerParameter("_QUERY_n_eq_category_id");
                WebCategory webCategory = resCategoryService.findOne(getLoginUserId(),
                        queryCategory);
                if (webCategory != null) {
                    dataModel.put("categoryName", webCategory.getName());
                }
            }
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
                WebResource webResource = webResourceService.findOne(getLoginUserId(),
                        Integer.valueOf(id));
                dataModel.put("webResource", webResource);
                dataModel.put("resourceId", webResource.getId());
                if ( webResource.getCategoryId() != null) { 
                    WebCategory webCategory = resCategoryService.findOne(getLoginUserId(),
                            webResource.getCategoryId());
                    if (webCategory != null) {
                        dataModel.put("categoryName", webCategory.getName());
                    }
                }
            }
            List<SysParam> templateType = templateService.getResTemplates();
            dataModel.put("resHotType", sysParamService.getSysParam(SysParamCode.RES_HOT_TYPE));
            dataModel.put("templateType", templateType);
            dataModel.put("iseffCode", sysParamService.getSysParam(SysParamCode.ISEFF));
            dataModel.put("resOnlineType",
                    sysParamService.getSysParam(SysParamCode.RES_ONLINE_TYPE));
            dataModel.put("needType", sysParamService.getSysParam(SysParamCode.NEED_TYPE));
           
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
            WebResource webResource = new WebResource();
            this.buildPageData(webResource);
            webResource.setUserId(getLoginUserId());
          

            if (!StringUtils.isEmpty(id)) {
                webResource.setId(Integer.valueOf(id));
                webResourceService.editResource(webResource);
            } else {
                webResourceService.saveResource(webResource);
            }
            responseData = ResponseUtils.success("保存成功！");
            responseData.setResult(webResource);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData = ResponseUtils.fail("保存失败！");
        }
        this.outResult(responseData);
    }

    /**
     * 保存资源详细描述信息
     */
    public void saveResLongDesc() {
        ResponseData responseData = null;
        try {
            Integer resourceId = getIntegerParameter("resourceId");
            String longDesc = getUTFParameter("longDesc");
            WebResource webResource = webResourceService.findById(resourceId);
            if (StringUtils.isEmpty(longDesc) || webResource == null) {
                responseData = ResponseUtils.fail("保存失败！");
            } else {
                webResource.setLongDesc(longDesc);
                baseModelService.update(webResource);
                responseData = ResponseUtils.success("保存成功！");
            }
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
                WebResource webResource = webResourceService.findOne(getLoginUserId(),
                        Integer.valueOf(id));
                if (webResource != null)
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
