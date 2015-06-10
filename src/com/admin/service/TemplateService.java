/**
 * 
 */
package com.admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.admin.constant.TemplateType;
import com.admin.model.SysParam;
import com.admin.model.WebTemplate;
import com.core.app.constant.IsEff;
import com.core.app.service.CacheModelService;
import com.core.jdbc.BaseDao;
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
	private BaseDao baseDao;

	/**
	 * 根据模板路径返回关联的分组key
	 * 
	 * @param templatePath
	 * @return
	 * @throws DaoException
	 */
	public List<String> getTemplateRelGroupKeys(String templatePath)
			throws DaoException {
		List<WebTemplate> list = baseDao.find(" templatePath ='"
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
		List<WebTemplate> list = baseDao.find(sql, WebTemplate.class);
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据资源模板，并封装到{@link #SysParam}对象列表返回。
	 */
	public List<SysParam> getResTemplates() throws DaoException {
		List<WebTemplate> templates = baseDao.find("template_type='"
				+ TemplateType.RES.getType() + "' and iseff=" + IsEff.EFFECTIVE
				+ " order by id", WebTemplate.class);
		List<SysParam> sysParamList = new ArrayList<SysParam>();
		for (WebTemplate g : templates) {
			SysParam p = new SysParam();
			p.setParamValue(g.getId().toString());
			p.setParamName(g.getName());
			sysParamList.add(p);
		}

		return sysParamList;
	}

}
