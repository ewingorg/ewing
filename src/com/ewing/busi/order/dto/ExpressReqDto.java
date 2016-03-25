package com.ewing.busi.order.dto;

public class ExpressReqDto {
  
  /**
   * 订单id
   */
  private Integer orderId;
  /**
   * 订单号
   */
  public String expressNum; 
  
  /**
   * 快递公司代号
   */
  public String expressCom;

  
  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public String getExpressNum() {
    return expressNum;
  }

  public void setExpressNum(String expressNum) {
    this.expressNum = expressNum;
  }

  public String getExpressCom() {
    return expressCom;
  }

  public void setExpressCom(String expressCom) {
    this.expressCom = expressCom;
  }
  
}
