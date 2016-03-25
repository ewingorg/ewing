package com.ewing.common.constant;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年3月25日
 * 
 */
public enum CargoStatus {

    NODATA(0, "物流单号暂无结果"), ONROAD(3, "在途"), FETECH(4, "揽件"), PROBLEM(5, "疑难"), ADREADY_SIGN(6, "签收"), RETURN_SIGN(
            7, "退签"), SENDING(8, "派件"), RETURN(9, "退回");

    public int value;
    public String message;

    private CargoStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static CargoStatus fromValue(Integer value) {
        if (value == null)
            throw new IllegalArgumentException("没有匹配的value[" + value + "]");
        for (CargoStatus cargoStatus : CargoStatus.values()) {
            if (cargoStatus.value == value) {
                return cargoStatus;
            }
        }
        throw new IllegalArgumentException("没有匹配的value[" + value + "]");
    }
}
