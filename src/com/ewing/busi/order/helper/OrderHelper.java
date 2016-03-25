package com.ewing.busi.order.helper;

import com.ewing.busi.order.dto.LightAddressInfoResp;
import com.ewing.busi.order.model.OrderInfo;

/**
 * 订单辅助类
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年3月1日
 */
public class OrderHelper {

    public static LightAddressInfoResp toAddress(OrderInfo order) {
        if (null == order) {
            return null;
        }

        LightAddressInfoResp addr = new LightAddressInfoResp();
        addr.setReceiver(order.getReceiver());
        addr.setProvince(order.getProvince());
        addr.setCity(order.getCity());
        addr.setRegion(order.getRegion());
        addr.setAddress(order.getAddress());
        addr.setPostCode(order.getPostCode());
        addr.setPhone(order.getPhone());

        return addr;
    }
}
