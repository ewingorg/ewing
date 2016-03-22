package com.ewing.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.seller.model.SellerShop;
import com.ewing.busi.seller.service.SellerShopService;
import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.web.service.WebTemplatePackageService;
import com.ewing.common.constant.SystemProperty;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.ewing.core.app.constant.IsEff;

/**
 * 商铺ACTION
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */
public class ShopAction extends BaseAction {

    private static Logger logger = Logger.getLogger(MainAction.class);
    private static final String EDIT_FORM = "/admin/shop/setting.html";
    @Resource
    private SellerShopService sellerShopService;
    @Resource
    private WebTemplatePackageService webTemplatePackageService;
    /**
     * 查詢
     */
    public void show() {
        try {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            SellerShop sellerShop = sellerShopService.findSellerShop(getLoginUserId());
            List<SysParam> templatePacks = webTemplatePackageService.querySelectTemplatePack();
            dataModel.put("templatePacks", templatePacks);
            dataModel.put("shop", sellerShop);
            dataModel.put("shopUrl",
                    SystemProperty.SHOPDOAMIN + "?shopId=" + sellerShop.getId());
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
        try {
            SellerShop sellerShop = new SellerShop();
            this.buildPageData(sellerShop);
            SellerShop oldSellerShop = sellerShopService.findSellerShop(getLoginUserId());
            sellerShop.setUserId(getLoginUserId());
            sellerShop.setIseff(IsEff.EFFECTIVE);
            if (oldSellerShop != null) {
                sellerShop.setId(oldSellerShop.getId());
                baseModelService.update(sellerShop);
            } else {
                baseModelService.save(sellerShop);
            }
            responseData = ResponseUtils.success("保存成功！");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData = ResponseUtils.fail("保存失败！");
        }
        this.outResult(responseData);
    }

}
