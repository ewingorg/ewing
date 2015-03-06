/**
 * 
 */
package com.admin.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.model.WebCategory;

/**
 * 用于业务网站模板页，包裹模板需要的各种分类数据。
 * 
 * @author tanson lam
 * @creation 2015年2月20日
 */
public class WebCategoryRender {

	public Map<String, List<WebCategory>> data = new HashMap<String, List<WebCategory>>();

	public void addCategoryList(String groupKey, List<WebCategory> categoryList) {
		data.put(groupKey, categoryList);
	}

	public Map<String, List<WebCategory>> getData() {
		return data;
	}

	public void setData(Map<String, List<WebCategory>> data) {
		this.data = data;
	}
}
