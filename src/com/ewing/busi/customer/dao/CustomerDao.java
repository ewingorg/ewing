package com.ewing.busi.customer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.customer.model.Customer;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 客户DAO
 * 
 * @author tansonlam
 * @createDate 2016年2月16日
 * 
 */
@Repository("customerDao")
public class CustomerDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 根据名称查找用户
     * 
     * @param userName
     * @return
     * @throws DaoException
     */
    public List<Customer> findUserByName(String userName) throws DaoException {
        if (StringUtils.isEmpty(userName))
            throw new IllegalArgumentException("userName should not be null");
        return baseDao.find(" name ='" + userName + "' and iseff='" + IsEff.EFFECTIVE + "'",
                Customer.class);
    }

    /**
     * 根据ID查找用户
     * 
     * @param userName
     * @return
     * @throws DaoException
     */
    public Customer findUserById(Integer userId) {
        if (userId == null)
            throw new IllegalArgumentException("userId should not be null");
        return baseDao.findOne(" id ='" + userId + "' and iseff='" + IsEff.EFFECTIVE + "'",
                Customer.class);
    }

}
