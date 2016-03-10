package com.ewing.busi.seller.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.dao.SellerShopDao;
import com.ewing.busi.seller.model.SellerShop;
import com.ewing.common.exception.ShopException;
import com.ewing.core.jdbc.DaoException;

/**
 * 商家服务类
 * 
 */
@Repository("sellerShopService")
public class SellerShopService {
    @Resource
    public SellerShopDao sellerShopDao;

    /**
     * 查找用户的商户设置
     * 
     * @param userId
     * @return
     * @throws DaoException
     */
    public SellerShop findSellerShop(Integer userId) {
        return sellerShopDao.findSellerShop(userId);
    }

    /**
     * 检查是否已经创建商店，如果已经创建则返回当前商店ID
     * 
     * @param userId
     * @return
     * @throws ShopException
     */
    public Integer checkAndReturnShopId(Integer userId) throws ShopException {
        SellerShop sellerShop = findSellerShop(userId);
        if (sellerShop != null)
            return sellerShop.getId();
        throw new ShopException("请先创建商店");
    }
}
