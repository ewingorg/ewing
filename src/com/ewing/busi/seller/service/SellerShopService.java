package com.ewing.busi.seller.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.dao.SellerShopDao;
import com.ewing.busi.seller.model.SellerShop;
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
    public SellerShop findSellerShop(Integer userId) throws DaoException {
        return sellerShopDao.findSellerShop(userId);
    }
}
