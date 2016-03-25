package com.ewing.busi.express.vo;

import java.util.List;

import com.ewing.busi.order.dto.LightAddressInfoResp;

/**
 * 快递响应参数
 * 
 * @author Joeson Chan<chenxuegui1234@163.com>
 * @since 2016年2月21日
 */
public class ExpressApiRespDto {

  /**
   * true 成功 ，false 失败
   */
  private Boolean success;

  /**
   * 如果请求失败，失败原因
   */
  private String reason;

  /**
   * 数据集合
   */
  private List<Data> data;

  /**
   * 运单的当前状态：0物流单号暂无结果，3在途，4 揽件，5 疑难，6签收，7退签，8 派件，9 退回
   */
  private Integer status;

  private LightAddressInfoResp address;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public LightAddressInfoResp getAddress() {
    return address;
  }

  public void setAddress(LightAddressInfoResp address) {
    this.address = address;
  }

  public List<Data> getData() {
    return data;
  }



  public void setData(List<Data> data) {
    this.data = data;
  }



  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public class Data {
    private String time;

    private String context;

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }

    public String getContext() {
      return context;
    }

    public void setContext(String context) {
      this.context = context;
    }

    @Override
    public String toString() {
      return "Data [time=" + time + ", context=" + context + "]";
    }

  }

  @Override
  public String toString() {
    return "ExpressRespDto [success=" + success + ", reason=" + reason + ", data=" + data
        + ", status=" + status + "]";
  }



}
