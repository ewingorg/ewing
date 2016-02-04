package com.ewing.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ewing.busi.resource.dto.ResPriceDto;
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

/**
 * 
 * 
 * @author tanson lam
 * @creation 2016年1月14日
 */
public class ResPriceAction extends BaseAction {
    private static Logger logger = Logger.getLogger(MainAction.class);
    private static final String LIST_PAGE = "/admin/res/price/respricelist.html";

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
            List<ResPriceDto> priceList = webResourcePriceService.getConfigurePrices(resourceId);
            dataModel.put("resSpecGroupList", resSpecGroupList);
            dataModel.put("priceList", priceList);
            dataModel.put("resourceId", resourceId);
            render(LIST_PAGE, dataModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 保存
     */
    public void savePrice() {
        ResponseData responseData = null;
        try {

            List<WebResourcePrice> resPriceList = gson.fromJson(
                    (String) request.getParameter("resPriceList"),
                    new TypeToken<List<WebResourcePrice>>() {
                    }.getType());
            Integer resourceId = Integer.valueOf(request.getParameter("resourceId"));

            if (resPriceList.isEmpty())
                throw new Exception("资源价格为空！");

            webResourcePriceService.savePriceList(resourceId, resPriceList);
            responseData = ResponseUtils.success("保存成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData = ResponseUtils.fail("保存失败！");
        }
        this.outResult(responseData);

    }
}
