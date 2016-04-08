package com.ewing.busi.order.service;

import org.junit.Test;

import com.ewing.busi.order.dto.ExpressRespDto;
import com.ewing.core.factory.SpringCtx;

public class OrderServiceTest {
    @Test
    public void testQueryCargoInfo() {
        OrderService orderService = (OrderService) SpringCtx.getByBeanName("orderService");
     /*   ExpressRespDto expressRespDto = orderService.queryCargoInfo(10, 1);
        System.out.println(expressRespDto);*/
    }
}
