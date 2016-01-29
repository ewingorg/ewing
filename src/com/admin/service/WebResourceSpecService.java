package com.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.admin.dto.ResSpecComparatorUtil;
import com.admin.dto.WebResourceSpecGroup;
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
	@Resource
	private WebResourcePriceService webResourcePriceService;

	/**
	 * 获取配置的资源規格,以规格组的结构返回
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<WebResourceSpecGroup> getConfigureSpecs(Integer resourceId) {
		List<WebResourceSpecGroup> groupList = new ArrayList<WebResourceSpecGroup>();
		List<WebResourceSpec> specList = baseDao.find("resource_id="
				+ resourceId + " and iseff='" + IsEff.EFFECTIVE
				+ "' order by rank", WebResourceSpec.class);
		for (WebResourceSpec spec : specList) {
			if (!StringUtils.isEmpty(spec.getRootSpec()))
				continue;
			WebResourceSpecGroup group = new WebResourceSpecGroup();
			group.setRootWebResourceSpec(spec);
			for (WebResourceSpec childSpec : specList) {
				if (!StringUtils.isEmpty(childSpec.getRootSpec())
						&& childSpec.getRootSpec().equals(spec.getSpec())) {
					group.addChildWebResourceSpec(childSpec);
				}
			}
			groupList.add(group);
		}
		ResSpecComparatorUtil.sortResGroupList(groupList);
		return groupList;
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
		if (resourceId == null || resSpecList == null)
			return;
		WebResource webResource = webResourceService.findById(resourceId);
		if (webResource == null)
			throw new Exception("没有找到匹配的资源信息");
		List<WebResourceSpec> removeList = new ArrayList<WebResourceSpec>();
		List<WebResourceSpec> oldSpecList = baseDao.find("resource_id="
				+ resourceId + " and iseff='" + IsEff.EFFECTIVE
				+ "' order by rank", WebResourceSpec.class);
		//保存规格配置
		for (WebResourceSpec spec : resSpecList) {
		
			spec.setResourceId(resourceId);
			spec.setIseff(IsEff.EFFECTIVE); 
			WebResourceSpec oldSpec = findBySpec(resourceId, spec.getSpec());
			if (spec.isSameSpec(oldSpec)) {
				oldSpec.setRank(spec.getRank());
				baseDao.update(oldSpec);
			} else {
				spec.setIseff(IsEff.EFFECTIVE);
				baseDao.save(spec);
			}

		}
		//检查被删除的规格
		for (WebResourceSpec oldSpec : oldSpecList) {
			boolean exist = false;
			for (WebResourceSpec spec : resSpecList) {
				if (oldSpec.isSameSpec(spec)) {
					exist = true;
					break;
				} 
			}
			if (!exist)
				removeList.add(oldSpec);
		}

		// 删除失效的规格配置
		for (WebResourceSpec spec : removeList) {
			baseDao.delete(spec);
		}
		// 由于变更了规则，删除失效的价格设置
		webResourcePriceService.removePriceForChangeSpec(resourceId);
	}

	/**
	 * 根据名称查找规格信息
	 * 
	 * @param resourceId
	 * @param specName
	 * @return
	 */
	public WebResourceSpec findBySpec(Integer resourceId, String specName) {
		return baseDao.findOne("resource_id=" + resourceId + " and spec='"
				+ specName + "' and iseff='" + IsEff.EFFECTIVE+"'",
				WebResourceSpec.class);
	}

	/**
	 * 比较新的规则配置是否与旧的配置相同
	 * 
	 * @param resourceId
	 * @param resSpecList
	 * @return
	 */
	public boolean isSameSpec(Integer resourceId,
			List<WebResourceSpec> resSpecList) {
		List<WebResourceSpec> oldSpecList = baseDao.find("resource_id="
				+ resourceId + " and iseff='" + IsEff.EFFECTIVE
				+ "' order by rank", WebResourceSpec.class);
		if (resSpecList.size() != oldSpecList.size())
			return false;
		for (WebResourceSpec newSpec : resSpecList) {
			boolean hasSame = false;
			for (WebResourceSpec oldSpec : oldSpecList) {
				if (oldSpec.isSameSpec(newSpec)) {
					hasSame = true;
					break;
				}
			}
			if (!hasSame)
				return false;
		}
		return true;
	}

}
