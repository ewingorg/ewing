package com.ewing.core.app.action.base;

/**
 * @author tanson lin
 * 
 * @create:2012-2-23
 * @description: action中向页面返回的封装信息.
 */
public class ResponseData {
	private Boolean success;
	private String retinfo;
	private Integer totalProperty;
	private String page;
	private Object result;

	public ResponseData() {

	}

	public String getRetinfo() {
		return retinfo;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Integer getTotalProperty() {
		return totalProperty;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setRetinfo(String retinfo) {
		this.retinfo = retinfo;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setTotalProperty(Integer totalProperty) {
		this.totalProperty = totalProperty;
	}
}
