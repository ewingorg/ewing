package com.ewing.busi.order.dao;

import java.util.List;

import org.junit.Test;

import com.ewing.busi.order.dto.OrderDetailDto;
import com.ewing.core.factory.SpringCtx;

public class OrderDetailDaoTest {
    @Test
    public void findDetailList() throws Exception {
        OrderDetailDao orderDetailDao = (OrderDetailDao) SpringCtx.getByBeanName("orderDetailDao");
        List<OrderDetailDto> list = orderDetailDao.findDetailList(new Integer[] { 1 });
        System.out.println(list);
    }
}
