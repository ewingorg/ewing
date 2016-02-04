package com.ewing.busi.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.web.model.WebGroup;
import com.ewing.busi.web.model.WebTemplate;
import com.ewing.constant.GroupType;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.app.service.CacheModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.ewing.util.SqlUtil;

/**
 * @author tanson lam
 * 
 * @creation 2015年1月10日
 */
@Repository("groupService")
public class GroupService {

	@Resource
	private BaseDao baseDao;
	/**
	 * 翻译模板的分组名称
	 * 
	 * @param webTemplate
	 * @return
	 */
	public WebTemplate translateGroupName(WebTemplate webTemplate) {
		List<WebTemplate> templateList = new ArrayList<WebTemplate>();
		templateList.add(webTemplate);
		List<WebTemplate> transList = translateGroupName(templateList, true);
		return transList.get(0);
	}

	/**
	 * 翻译模板的分组名称
	 * 
	 * @param templateList
	 * @param isDetail
	 * @return
	 */
	public List<WebTemplate> translateGroupName(List<WebTemplate> templateList,
			Boolean isDetail) {
		Set<String> groupKeySet = new HashSet<String>();
		for (WebTemplate webTemplate : templateList) {
			if (webTemplate.getGroupKeys() == null)
				continue;
			String[] groupKeys = webTemplate.getGroupKeys().split(",");
			for (String key : groupKeys) {
				groupKeySet.add(key);
			}
		}
		if (groupKeySet.isEmpty())
			return templateList;
		
		Map<String, WebGroup> groupKeyMap = findWebGroupByName(groupKeySet
				.toArray(new String[groupKeySet.size()]));
		StringBuffer translate = new StringBuffer();

		for (WebTemplate webTemplate : templateList) {
			if (webTemplate.getGroupKeys() == null)
				continue;
			String[] groupKeys = webTemplate.getGroupKeys().split(",");
			for (String key : groupKeys) {
				translate.append(groupKeyMap.get(key).getName());
				if (isDetail)
					translate.append("(" + key + ")");
				translate.append(",");
			}
			webTemplate.setGroupKeysTranslate(translate.toString().substring(0,
					translate.toString().length() - 1));
			translate.delete(0, translate.toString().length());
		}
		return templateList;
	}
	/**
	 * 根据分组KEY查询分组列表
	 * 
	 * @param groupKey
	 * @return
	 */
	public Map<String, WebGroup> findWebGroupByName(String[] groupKey) {
		Map<String, WebGroup> map = new HashMap<String, WebGroup>();
		List<WebGroup> list = baseDao.find(
				"groupKey in (" + SqlUtil.array2InCondition(groupKey)
						+ ") and iseff=" + IsEff.EFFECTIVE + " order by id",
				WebGroup.class);
		for (WebGroup webGroup : list) {
			map.put(webGroup.getGroupKey(), webGroup);
		}
		return map;
	}

	/**
	 * 根据分组类型获取分组列表，并封装到{@link #SysParam}对象列表返回。
	 */
	public List<SysParam> getGroupParamList(GroupType groupType)
			throws DaoException {
		List<WebGroup> grouplist = baseDao.find(
				"groupType='" + groupType.getCode() + "' and iseff="
						+ IsEff.EFFECTIVE + " order by id", WebGroup.class);
		List<SysParam> sysParamList = new ArrayList<SysParam>();
		for (WebGroup g : grouplist) {
			SysParam p = new SysParam();
			p.setParamValue(g.getGroupKey());
			p.setParamName(g.getName());
			sysParamList.add(p);
		}

		return sysParamList;
	}

	/**
	 * 获取所有分组列表，并封装到{@link #SysParam}对象列表返回。
	 */
	public List<SysParam> getGroupParamList() throws DaoException {
		List<WebGroup> grouplist = baseDao.find("iseff=" + IsEff.EFFECTIVE
				+ " order by id", WebGroup.class);
		List<SysParam> sysParamList = new ArrayList<SysParam>();
		for (WebGroup g : grouplist) {
			SysParam p = new SysParam();
			p.setParamValue(g.getGroupKey());
			p.setParamName(g.getName());
			sysParamList.add(p);
		}

		return sysParamList;
	}

}
