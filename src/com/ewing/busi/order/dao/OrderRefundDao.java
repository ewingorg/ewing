package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.model.OrderRefund;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.SqlUtil;

/**
 * 订单退款DAO
 * 
 * @author tansonlam
 * @createDate 2016年4月5日
 * 
 */
@Repository("orderRefundDao")
public class OrderRefundDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 查找退款
     * 
     * @param orderDetailId
     * @return
     */
    public List<OrderRefund> findRefundList(Integer userId, Integer[] orderDetailIds) {
        String sql = "user_id=" + userId + " and order_detail_id in ("
                + SqlUtil.array2InCondition(orderDetailIds) + ") and iseff='" + IsEff.EFFECTIVE
                + "'";
        return baseDao.find(sql, OrderRefund.class);
    }

    /**
     * 查找退款
     * 
     * @param orderDetailId
     * @return
     */
    public OrderRefund findRefund(Integer userId, Integer orderDetailId) {
        String sql = "user_id=" + userId + " and order_detail_id = " + orderDetailId
                + " and iseff='" + IsEff.EFFECTIVE + "'";
        return baseDao.findOne(sql, OrderRefund.class);
    }
}
