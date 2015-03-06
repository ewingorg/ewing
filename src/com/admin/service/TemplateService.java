/**
 * 
 */
package com.admin.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.admin.model.WebTemplate;
import com.core.app.service.CacheModelService;
import com.core.jdbc.DaoException;

/**
 * 模板服务类
 * 
 * @author tanson lam
 * @creation 2015年3月2日
 */
@Repository("templateService")
public class TemplateService {
	@Resource
	private CacheModelService cacheModelService;

	/**
	 * 根据模板路径返回关联的分组key
	 * 
	 * @param templatePath
	 * @return
	 * @throws DaoException
	 */
	public List<String> getTemplateRelGroupKeys(String templatePath)
			throws DaoException {
		List<WebTemplate> list = cacheModelService.find(" templatePath ='"
				+ templatePath + "' and iseff='1' ", WebTemplate.class);
		if (list != null && !list.isEmpty()) {
			WebTemplate template = list.get(0);
			String groupKeys = template.getGroupKeys();
			if (!StringUtils.isEmpty(groupKeys)) {
				String[] groupKeyArr = groupKeys.split(",");
				return Arrays.asList(groupKeyArr);
			}
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * 是否有相同的模板路径
	 * 
	 * @param templateId
	 * @param templatePath
	 * @return
	 * @throws DaoException
	 */
	public Boolean isDuplicateTemplatePath(String templateId,
			String templatePath) throws DaoException {
		String sql = " templatePath ='" + templatePath + "' ";
		if (templateId != null)
			sql += " and id !=" + templateId;
		List<WebTemplate> list = cacheModelService.find(sql, WebTemplate.class);
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

}
