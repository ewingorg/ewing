package com.ewing.busi.order.dao;

import org.junit.Test;

import com.ewing.busi.order.dto.OrderInfoDto;
import com.ewing.core.factory.SpringCtx;
import com.ewing.core.jdbc.util.PageBean;

public class OrderDaoTest {

    @Test
    public void findDetailList() throws Exception {
        OrderDao orderDao = (OrderDao) SpringCtx.getByBeanName("orderDao");
       // PageBean<OrderInfoDto> list = orderDao.pageQueryOrder(10, null, null, 1, 2);
       // System.out.println(list);
    }
}
