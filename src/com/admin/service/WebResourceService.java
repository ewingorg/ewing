package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.admin.model.WebAttrConf;
import com.admin.model.WebResource;
import com.admin.model.WebResourceAttr;
import com.core.app.constant.IsEff;
import com.core.app.service.CacheModelService;
import com.core.jdbc.BaseDao;

/**
 * 资源服务类
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
@Repository("webResourceService")
public class WebResourceService { 
	@Resource
	private BaseDao baseDao;
	@Resource
	private WebAttrConfService webAttrConfService;

	/**
	 * 获取资源属性值
	 * 
	 * @param resourceId
	 *            资源id
	 * @return
	 */
	public List<WebResourceAttr> getResourceAttrs(Integer resourceId) {
		return baseDao.find("resource_id=" + resourceId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by rank", WebResourceAttr.class);
	}

	/**
	 * 获取资源属性
	 * 
	 * @param resourceId
	 * @param attrKey
	 * @return
	 */
	public WebResourceAttr getResourceAttr(Integer resourceId, String attrKey) {
		return baseDao.findOne("resource_id=" + resourceId + " and attr_key='"
				+ attrKey + "' and iseff='" + IsEff.EFFECTIVE
				+ "' order by rank", WebResourceAttr.class);
	}

	/**
	 * 保存
	 * 
	 * @param resource
	 * @param attrList
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean saveResource(WebResource resource,
			List<WebResourceAttr> attrList) {
		baseDao.save(resource);
		Integer resourceId = resource.getId();
		Integer templateId = resource.getTemplateId();
		List<WebAttrConf> attrConfList = webAttrConfService
				.getTemplateAttrs(templateId);
		for (WebResourceAttr attr : attrList) {
			for (WebAttrConf webAttrConf : attrConfList) {
				if (webAttrConf.getAttrKey().equals(attr.getAttrKey())) {
					attr.setAttrName(webAttrConf.getAttrName());
					attr.setRank(webAttrConf.getSort());
				}
			}
			attr.setIseff(IsEff.EFFECTIVE);
			attr.setResourceId(resourceId);
			baseDao.save(attr);
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean editResource(WebResource resource,
			List<WebResourceAttr> changeAttrList) {
		baseDao.update(resource);
		Integer resourceId = resource.getId();
		Integer templateId = resource.getTemplateId();
		List<WebAttrConf> attrConfList = webAttrConfService
				.getTemplateAttrs(templateId);
		for (WebResourceAttr attr : changeAttrList) {
			for (WebAttrConf webAttrConf : attrConfList) {
				if (webAttrConf.getAttrKey().equals(attr.getAttrKey())) {
					attr.setAttrName(webAttrConf.getAttrName());
					attr.setRank(webAttrConf.getSort());
				}
			}
			attr.setIseff(IsEff.EFFECTIVE);
			attr.setResourceId(resourceId);
			WebResourceAttr oldAttr = getResourceAttr(resourceId,
					attr.getAttrKey());
			if (oldAttr != null) {
				attr.setAttrValue(attr.getAttrValue());
				baseDao.update(attr);
			} else {
				baseDao.save(attr);
			}

		}
		return true;
	}
}
