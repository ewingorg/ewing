package com.ewing.busi.order.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderInfo;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 订单DAO
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
@Repository("orderDao")
public class OrderDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 查找订单
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public OrderInfo findOne(Integer userId, Integer orderId) {
        return baseDao.findOne("id=" + orderId + " and user_id=" + userId + " and iseff='"
                + IsEff.EFFECTIVE + "'", OrderInfo.class);
    }

    /**
     * 分页查询订单
     * 
     * @param userId
     * @param condition
     * @param order
     * @param pageSize
     * @param page
     * @return
     */
    public PageBean<OrderInfo> pageQueryOrder(Integer userId, String condition, String order,
            Integer pageSize, Integer page) {
        condition = condition + " and user_id=" + userId + " and iseff='" + IsEff.EFFECTIVE + "'";
        return baseDao.pageQuery(condition, order, pageSize, page, OrderInfo.class);
    }

}
