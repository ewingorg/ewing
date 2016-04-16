package com.ewing.busi.stat.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dao.OrderDao;

@Repository("mainStatService")
public class MainStatService {
    @Resource
    private OrderDao orderDao;

    /**
     * 查询需要商户处理的订单数
     */
    public Integer queryWaitProOrder(Integer userId) {
        return orderDao.countWaitProOrder(userId);
    }

}
