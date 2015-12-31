package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.WebResource;
import com.admin.model.WebResourcePrice;
import com.admin.model.WebResourceSpec;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;

/**
 * 
 * 资源价格服务类
 * 
 * @author tanson lam
 * @creation 2015年12月15日
 */
@Repository("webResourcePriceService")
public class WebResourcePriceService {

	@Resource
	private BaseDao baseDao;
	@Resource
	private WebResourceService webResourceService;

	/**
	 * 查询配置资源价格
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<WebResourcePrice> getConfigurePrices(Integer resourceId) {
		return baseDao.find("resource_id=" + resourceId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by rank", WebResourcePrice.class);
	}

	/**
	 * 批量保存资源价格
	 * 
	 * @param resourceId
	 * @param resPriceList
	 * @throws Exception
	 */
	public void savePriceList(Integer resourceId,
			List<WebResourcePrice> resPriceList) throws Exception {
		WebResource webResource = webResourceService.findById(resourceId);
		if (webResource == null)
			throw new Exception("没有找到匹配的资源信息");
		// 删除旧的参数记录
		baseDao.executeSql("delete from web_resource_price where resource_id="
				+ resourceId);

		for (WebResourcePrice spec : resPriceList) {
			spec.setResourceId(resourceId);
			spec.setIseff(IsEff.EFFECTIVE);
			baseDao.save(spec);
		}
	}
}
