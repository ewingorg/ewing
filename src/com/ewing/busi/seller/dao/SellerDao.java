package com.ewing.busi.seller.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.seller.model.Seller;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 商家服务类
 * 
 */
@Repository("sellerDao")
public class SellerDao {
    @Resource
    public BaseDao baseDao;

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
        return baseDao.find(" user_name='" + userName + "' and iseff='" + IsEff.EFFECTIVE + "'",
                Seller.class);
    }

}
