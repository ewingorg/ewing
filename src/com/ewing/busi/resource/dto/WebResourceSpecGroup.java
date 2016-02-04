package com.ewing.busi.resource.dto;

import java.util.ArrayList;
import java.util.List;

import com.ewing.busi.resource.model.WebResourceParam;
import com.ewing.busi.resource.model.WebResourceSpec;

/**
 * 规格组
 * 
 * @author tanson lam
 * @creation 2016年1月5日
 */
public class WebResourceSpecGroup {
	/**
	 * 规格父分组
	 */
	private WebResourceSpec rootWebResourceSpec;
	/**
	 * 该父规格组下的子规格集合
	 */
	private List<WebResourceSpec> childParamlist = new ArrayList<WebResourceSpec>();
	/**
	 * 是否相同的规格组
	 * @param g
	 * @return
	 */
	public Boolean isSameGroup(WebResourceSpecGroup g) {
		if (g == null || g.rootWebResourceSpec == null)
			return false;
		if (this.rootWebResourceSpec.getSpec().equals(
				g.getRootWebResourceSpec().getSpec())
				&& this.childParamlist.size() == g.childParamlist.size()) {
			return true;

		}
		return false;
	}

	public WebResourceSpec getRootWebResourceSpec() {
		return rootWebResourceSpec;
	}

	public void setRootWebResourceSpec(WebResourceSpec rootWebResourceSpec) {
		this.rootWebResourceSpec = rootWebResourceSpec;
	}

	public List<WebResourceSpec> getChildParamlist() {
		return childParamlist;
	}

	public void setChildParamlist(List<WebResourceSpec> childParamlist) {
		this.childParamlist = childParamlist;
	}

	public void addChildWebResourceSpec(WebResourceSpec childWebResourceSpec) {
		childParamlist.add(childWebResourceSpec);
	}

}
