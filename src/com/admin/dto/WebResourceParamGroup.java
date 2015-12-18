package com.admin.dto;

import java.util.ArrayList;
import java.util.List;

import com.admin.model.WebResourceParam;

/**
 * 资源参数组
 * 
 * @author tanson lam
 * @creation 2015年12月16日
 */
public class WebResourceParamGroup {
	/**
	 * 资源父分组
	 */
	private WebResourceParam rootResourceParam;
	/**
	 * 该父参数组下已经配置的资源参数
	 */
	private List<WebResourceParam> childParamlist = new ArrayList<WebResourceParam>();

	public WebResourceParam getRootResourceParam() {
		return rootResourceParam;
	}

	public void setRootResourceParam(WebResourceParam rootResourceParam) {
		this.rootResourceParam = rootResourceParam;
	}

	public List<WebResourceParam> getChildParamlist() {
		return childParamlist;
	}

	public void setChildParamlist(List<WebResourceParam> childParamlist) {
		this.childParamlist = childParamlist;
	}

}
