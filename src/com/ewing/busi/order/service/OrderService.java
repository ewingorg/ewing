package com.ewing.busi.order.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 订单服务类
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
@Repository("orderService")
public class OrderService {
    @Resource
    private BaseDao baseDao;

    /**
     * 查找订单
     * 
     * @param userId
     * @param orderId
     * @return
     *//*
    public OrderInfo findOne(Integer userId, Integer orderId) {
        return baseDao.findOne("id=" + orderId + " and user_id=" + userId, OrderInfo.class);
    }

    public PageBean pageQueryOrder(Integer userId, String condition, String order,
            Integer pageSize, Integer page) {
      
        return null;
    }*/

}
