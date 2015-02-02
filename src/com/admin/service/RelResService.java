package com.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.dto.RelResourceDto;
import com.admin.model.WebRelResource;
import com.admin.model.WebResource;
import com.core.app.service.BaseModelService;
import com.core.jdbc.DaoException;
import com.core.jdbc.util.PageBean;
import com.util.SqlUtil;

/**
 * 分类和资源关系服务类
 * 
 * @author tanson lam
 * @creation 2015年1月29日
 */
@Repository("relResService")
public class RelResService {
	@Resource
	public BaseModelService baseModelService;

	/**
	 * 查询某一分类的关联资源
	 * 
	 * @param categoryId
	 * @param pageSize
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	public PageBean listRelResourceByCategory(Integer categoryId,
			Integer pageSize, Integer page) throws DaoException {
		String sql = "SELECT "
				+ " r.`category_id` AS categoryId, c.name  AS categoryName ,"
				+ " r.`resource_id` AS resourceId, s.`name` AS resourceName,"
				+ " s.`iseff` AS resourceIseff,s.`short_desc` as resourceShortdesc,"
				+ " r.`create_time` AS createTime, r.`last_update` AS lastUpdate"
				+ " FROM web_rel_resource r ,web_resource s , web_category c "
				+ " WHERE "
				+ " r.`category_id` = c.`id` AND r.`resource_id`=s.`id` AND "
				+ " r.`category_id`=" + categoryId;
		return baseModelService.executePageQuery(sql, pageSize, page,
				RelResourceDto.class);
	}

	/**
	 * 查询某一分类的还没有关联的资源
	 * 
	 * @param categoryId
	 * @param pageSize
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	public PageBean listNotRelResourceByCategory(Integer categoryId,
			Integer pageSize, Integer page) throws DaoException {
		String sql = "SELECT r.* FROM web_resource r WHERE NOT EXISTS"
				+ " (SELECT id FROM web_rel_resource c WHERE c.`resource_id` = r.`id` AND c.`category_id`="
				+ categoryId + ")";
		return baseModelService.executePageQuery(sql, pageSize, page,
				WebResource.class);
	}

	/**
	 * 绑定分类和资源关系
	 * 
	 * @param categoryId
	 *            分类id
	 * @param resourceIds
	 *            多个资源id
	 * @param template
	 *            页面模板
	 * @param iseff
	 *            是否有效
	 * @throws DaoException
	 */
	public void bindRelResource(Integer categoryId, Integer[] resourceIds,
			String template, String iseff) throws DaoException {
		for (Integer resourceId : resourceIds) {
			WebRelResource relResource = new WebRelResource();
			relResource.setCategoryId(categoryId);
			relResource.setIseff(iseff);
			relResource.setResourceId(resourceId);
			relResource.setTempalte(template);
			baseModelService.save(relResource);
		}
	}

	/**
	 * 删除分类和资源关系
	 * 
	 * @param categoryId
	 *            分类id
	 * @param resourceIds
	 *            多个资源id
	 * @throws DaoException
	 */
	public void unBindRelResource(Integer categoryId, Integer[] resourceIds)
			throws DaoException {
		String sql = "delete from web_rel_resource where category_id= "
				+ categoryId + " and  resource_id in ("
				+ SqlUtil.array2InCondition(resourceIds) + ")";
		baseModelService.executeSql(sql);
	}

}
