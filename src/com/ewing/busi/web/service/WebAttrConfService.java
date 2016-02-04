package com.ewing.busi.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.model.WebAttrConf;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.app.service.CacheModelService;
import com.ewing.core.jdbc.BaseDao;

/**
 * 模板资源的属性
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
@Repository("webAttrConfService")
public class WebAttrConfService {
 
	@Resource
	private BaseDao baseDao;

	public List<WebAttrConf> getTemplateAttrs(Integer templateId) {
		return baseDao.find("template_id=" + templateId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by sort", WebAttrConf.class);
	}
}
