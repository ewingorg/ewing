package com.ewing.busi.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.category.model.WebCatagoryParam;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 
 * 分类参数服务类
 * 
 * @author tanson lam
 * @creation 2015年12月15日
 */
@Repository("webCategoryParamService")
public class WebCategoryParamService {

	@Resource
	private BaseDao baseDao;

	public List<WebCatagoryParam> getDefindParams(Integer categoryId) {
		return baseDao.find("category_id=" + categoryId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by rank", WebCatagoryParam.class);
	}
}
