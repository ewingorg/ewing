package com.ewing.busi.seller.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.dao.SellerDao;
import com.ewing.busi.seller.model.Seller;
import com.ewing.core.jdbc.DaoException;

/**
 * 商家服务类
 * 
 */
@Repository("sellerService")
public class SellerService {
    @Resource
    public SellerDao sellerDao;

    /**
     * 查找商家用户
     * 
     * @param userName
     * @return
     * @throws DaoException
     */
    public List<Seller> findUser(String userName) throws DaoException {
        if (StringUtils.isEmpty(userName))
            throw new IllegalArgumentException("userName should not be null");
        return sellerDao.findUser(userName);
    }

}
