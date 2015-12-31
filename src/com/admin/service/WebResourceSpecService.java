package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.WebResource;
import com.admin.model.WebResourceSpec;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;

/**
 * 
 * 资源规格服务类
 * 
 * @author tanson lam
 * @creation 2015年12月15日
 */
@Repository("webResourceSpecService")
public class WebResourceSpecService {

	@Resource
	private BaseDao baseDao;
	@Resource
	private WebResourceService webResourceService;

	/**
	 * 获取配置的资源規格
	 * @param resourceId
	 * @return
	 */
	public List<WebResourceSpec> getConfigureSpecs(Integer resourceId) {
		return baseDao.find("resource_id=" + resourceId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by rank", WebResourceSpec.class);
	}

	/**
	 * 批量保存资源规格
	 * 
	 * @param resourceId
	 * @param webResourceSpec
	 * @throws Exception
	 */
	public void saveSpecList(Integer resourceId,
			List<WebResourceSpec> resSpecList) throws Exception {
		WebResource webResource = webResourceService.findById(resourceId);
		if (webResource == null)
			throw new Exception("没有找到匹配的资源信息");
		// 删除旧的参数记录
		baseDao.executeSql("delete from web_resource_spec where resource_id="
				+ resourceId);

		for (WebResourceSpec spec : resSpecList) {
			spec.setResourceId(resourceId);
			spec.setIseff(IsEff.EFFECTIVE);
			baseDao.save(spec);
		}
	}

}
