package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderDetail;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 订单详情
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
@Repository("orderDetailDao")
public class OrderDetailDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 查找订单
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public List<OrderDetail> findOne(Integer orderId) {
        return baseDao.find("order_id=" + orderId + "  and iseff='" + IsEff.EFFECTIVE + "'",
                OrderDetail.class);
    }
}
