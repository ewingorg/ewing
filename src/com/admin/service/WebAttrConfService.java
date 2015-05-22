package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.WebAttrConf;
import com.core.app.constant.IsEff;
import com.core.app.service.CacheModelService;
import com.core.jdbc.BaseDao;

/**
 * 模板资源的属性
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
@Repository("webAttrConfService")
public class WebAttrConfService {

	@Resource
	private CacheModelService cacheModelService;
	@Resource
	private BaseDao baseDao;

	public List<WebAttrConf> getTemplateAttrs(Integer templateId) {
		return baseDao.find("template_id=" + templateId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by sort", WebAttrConf.class);
	}
}
