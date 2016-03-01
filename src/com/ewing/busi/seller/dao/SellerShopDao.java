package com.ewing.busi.seller.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.model.SellerShop;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 商家店铺DAO
 * 
 * @author tansonlam
 * @createDate 2016年3月1日
 * 
 */
@Repository("sellerShopDao")
public class SellerShopDao {
    @Resource
    public BaseDao baseDao;

    /**
     * 查找用户的商户设置
     * 
     * @param userId
     * @return
     * @throws DaoException
     */
    public SellerShop findSellerShop(Integer userId) throws DaoException {
        return baseDao.findOne(" user_id='" + userId + "' and iseff='" + IsEff.EFFECTIVE + "'",
                SellerShop.class);
    }
}
