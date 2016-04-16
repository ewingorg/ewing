/**
 * 
 */
package com.ewing.busi.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.model.WebBlock;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.dto.WebCategoryRender;

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

	/**
	 * 根据分组key获取分类列表
	 * 
	 * @param groupKey
	 *            分组key
	 * @return
	 * @throws DaoException
	 */
	public List<WebBlock> getCategoryList(String groupKey)
			throws DaoException {
		return baseDao.find("groupKey='" + groupKey + "' and iseff="
				+ IsEff.EFFECTIVE + " order by rank asc", WebBlock.class);
	}

	public void loadCategoryList(List<String> groupKeyList, Map dataModel)
			throws DaoException {
		WebCategoryRender categoryRender = new WebCategoryRender();
		for (String groupKey : groupKeyList) {
			try {
				List<WebBlock> categoryList = baseDao.find("groupKey='"
						+ groupKey + "' and iseff=" + IsEff.EFFECTIVE
						+ " order by rank asc", WebBlock.class);
				categoryRender.addCategoryList(groupKey, categoryList);
			} catch (Exception e) {
				throw new DaoException("failure to find groupKey[" + groupKey
						+ "]'s category list", e);
			}
		}
		dataModel.put("categoryRender", categoryRender);
	}
}
