package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.order.dto.OrderInfoDto;
import com.ewing.busi.order.dto.OrderQueryReq;
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
     * 查找订单
     * @param userId
     * @param orderId
     * @return
     */
    public OrderInfoDto findOneMoreInfo(Integer userId, Integer orderId) {
        String sql = "select o.*,c.name  as customer_name,c.phone as customer_phone,s.user_name as user_name"
                + " from order_info o " + " inner join customer c on o.customer_id=c.id"
                + " inner join seller s on o.user_id=s.id ";
        sql += " and o.user_id=" + userId + " and o.iseff='" + IsEff.EFFECTIVE + "'";
        sql += " and o.id=" + orderId;
        List<OrderInfoDto> list = baseDao.noMappedObjectQuery(sql, OrderInfoDto.class);
        if (list.isEmpty())
            return null;
        return list.get(0);
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
    public PageBean<OrderInfoDto> pageQueryOrder(Integer userId, OrderQueryReq orderQueryReq) {
        String sql = "select o.*,c.name  as customer_name,c.phone as customer_phone,s.user_name as user_name"
                + " from order_info o " + " inner join customer c on o.customer_id=c.id"
                + " inner join seller s on o.user_id=s.id ";
        sql += " and o.user_id=" + userId + " and o.iseff='" + IsEff.EFFECTIVE + "'";
        if (!StringUtils.isEmpty(orderQueryReq.getBizId())) {
            sql += " and o.biz_id ='" + orderQueryReq.getBizId() + "'";
        }
        if (!StringUtils.isEmpty(orderQueryReq.getCargoName())) {
            sql += " and o.cargo_name like '%" + orderQueryReq.getCargoName() + "%'";
        }
        if (!StringUtils.isEmpty(orderQueryReq.getCargoNumber())) {
            sql += " and o.cargo_number = '" + orderQueryReq.getCargoNumber() + "'";
        }
        if (!StringUtils.isEmpty(orderQueryReq.getStatus())) {
            sql += " and o.status = '" + orderQueryReq.getStatus() + "'";
        }
        if (!StringUtils.isEmpty(orderQueryReq.getCustomerName())) {
            sql += " and c.name like '%" + orderQueryReq.getCustomerName() + "%'";
        }

        if (!StringUtils.isEmpty(orderQueryReq.getStartDate())) {
            sql += " and o.create_time >= '" + orderQueryReq.getStartDate() + " 00:00:00'";
        }

        if (!StringUtils.isEmpty(orderQueryReq.getStartDate())) {
            sql += " and o.create_time <= '" + orderQueryReq.getEndDate() + " 23:59:59'";
        }
        sql += " order by o.id desc";

        return baseDao.noMappedObjectPageQuery(sql, OrderInfoDto.class, orderQueryReq.getPage(),
                orderQueryReq.getPageSize());
    }

}
