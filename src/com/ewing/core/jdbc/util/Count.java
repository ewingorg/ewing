package com.ewing.core.jdbc.util;

import com.ewing.core.jdbc.annotation.Column;

/**
 * @author tanson lam 2014年9月2日
 * 
 */
public class Count{
	@Column(fieldName = "count")
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
