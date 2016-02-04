/**
 * 
 */
package com.ewing.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ewing.busi.web.model.WebBlock;

/**
 * 用于业务网站模板页，包裹模板需要的各种分类数据。
 * 
 * @author tanson lam
 * @creation 2015年2月20日
 */
public class WebCategoryRender {

	public Map<String, List<WebBlock>> data = new HashMap<String, List<WebBlock>>();

	public void addCategoryList(String groupKey, List<WebBlock> categoryList) {
		data.put(groupKey, categoryList);
	}

	public Map<String, List<WebBlock>> getData() {
		return data;
	}

	public void setData(Map<String, List<WebBlock>> data) {
		this.data = data;
	}
}
