/**
 * 
 */
package com.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.dto.WebCategoryRender;
import com.admin.model.WebCategory;
import com.core.app.constant.IsEff;
import com.core.app.service.CacheModelService;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;

/**
 * 分类逻辑服务类
 * 
 * @author tanson lam
 * @creation 2015年2月20日
 */
@Repository("categoryService")
public class CategoryService {

	@Resource
	private BaseDao baseDao;
	@Resource
	private CacheModelService cacheModelService;

	/**
	 * 根据分组key获取分类列表
	 * 
	 * @param groupKey
	 *            分组key
	 * @return
	 * @throws DaoException
	 */
	public List<WebCategory> getCategoryList(String groupKey)
			throws DaoException {
		return baseDao.find("groupKey='" + groupKey + "' and iseff="
				+ IsEff.EFFECTIVE + " order by rank asc", WebCategory.class);
	}

	public void loadCategoryList(List<String> groupKeyList, Map dataModel)
			throws DaoException {
		WebCategoryRender categoryRender = new WebCategoryRender();
		for (String groupKey : groupKeyList) {
			try {
				List<WebCategory> categoryList = baseDao.find(
						"groupKey='" + groupKey + "' and iseff="
								+ IsEff.EFFECTIVE + " order by rank asc",
						WebCategory.class);
				categoryRender.addCategoryList(groupKey, categoryList);
			} catch (DaoException e) {
				throw new DaoException("failure to find groupKey[" + groupKey
						+ "]'s category list", e);
			}
		}
		dataModel.put("categoryRender", categoryRender);
	}
}
