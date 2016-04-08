package com.ewing.busi.order.contant;

/**
 * 订单状态
 * 
 * @author tansonlam
 * @createDate 2016年2月19日
 * 
 */
public enum OrderStatus {
    // 订单状态 0:待付款 1:待发货 2:待收货 3:退款中 4:已完成 5:已关闭
    WAIT_PAY("0", "待付款"), WAIT_SEND("1", "待发货"), WAIT_RECEIVE("2", "待收货"), REFUNDING("3", "退款中"), DONE(
            "4", "已完成"), CLOSE("5", "已关闭");

    private String status;
    private String message;

    private OrderStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromStatus(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.status.equals(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("no found match fromStatus for value[" + status + "]");

    }
}
