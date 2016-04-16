package com.ewing.busi.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.order.contant.OrderStatus;
import com.ewing.busi.order.dto.OrderDetailDto;
import com.ewing.busi.order.model.OrderDetail;
import com.ewing.busi.order.model.OrderInfo;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.util.SqlUtil;

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
     * 
     * @param orderDetailId
     * @return
     */
    public OrderDetail findDetail(Integer userId, Integer orderDetailId) {
        return baseDao.findOne("id=" + orderDetailId + " and user_id=" + userId + " and iseff='"
                + IsEff.EFFECTIVE + "'", OrderDetail.class);
    }

    /**
     * 查找订单
     * 
     * @param userId
     * @param orderId
     * @return
     */
    public List<OrderDetailDto> findDetailDtoList(Integer[] orderIds) {
        String sql = "select d.*,r.name as resource_name,r.image_url as resource_image_url from order_detail d "
                + " inner join web_resource r on d.resource_id=r.id where d.order_id in "
                + "("
                + SqlUtil.array2InCondition(orderIds)
                + ")"
                + " and d.iseff='"
                + IsEff.EFFECTIVE
                + "' and r.iseff='" + IsEff.EFFECTIVE + "' order by d.id,d.order_id";
        return baseDao.noMappedObjectQuery(sql, OrderDetailDto.class);
    }

    /**
     * 查询订单所有的详情订单
     * 
     * @param orderId
     * @return
     */
    public List<OrderDetailDto> findDetailDtoList(Integer orderId) {
        String sql = "select d.*,r.name as resource_name,r.image_url as resource_image_url from order_detail d "
                + " inner join web_resource r on d.resource_id=r.id where d.order_id = "
                + orderId
                + " and d.iseff='"
                + IsEff.EFFECTIVE
                + "' and r.iseff='"
                + IsEff.EFFECTIVE
                + "' order by d.id,d.order_id";
        return baseDao.noMappedObjectQuery(sql, OrderDetailDto.class);
    }

    /**
     * 查询订单所有的详情订单
     * 
     * @param orderId
     * @return
     */
    public List<OrderDetail> findDetailList(Integer orderId) {
        String sql = "d.order_id = " + orderId + " and d.iseff='" + IsEff.EFFECTIVE
                + "' and r.iseff='" + IsEff.EFFECTIVE + "' order by d.id,d.order_id";
        return baseDao.find(sql, OrderDetail.class);
    }

    /**
     * 更新超时的订单的状态为关闭
     * 
     * @param orderId
     * @return
     */
    public Integer updateTimeOutOrder2Close(Integer orderId) {
        String updateOrder = "update order_detail set status='" + OrderStatus.CLOSE.getStatus()
                + "' where order_id=" + orderId + " and status='" + OrderStatus.WAIT_PAY.getStatus()
                + "'";
        return baseDao.executeSql(updateOrder);
    }
    
    /**
     * 更新没有用户确定收货的订单的状态为关闭
     * 
     * @param orderId
     * @return
     */
    public Integer updateNoConfirmOrder2Close(Integer orderId) {
        String updateOrder = "update order_detail set status='" + OrderStatus.CLOSE.getStatus()
                + "' where order_id=" + orderId + " and status='" + OrderStatus.WAIT_RECEIVE.getStatus()
                + "'";
        return baseDao.executeSql(updateOrder);
    }
}
