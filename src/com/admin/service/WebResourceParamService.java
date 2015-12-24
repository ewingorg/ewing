package com.admin.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.admin.dto.ResParamGroupComparatorUtil;
import com.admin.dto.WebResourceParamGroup;
import com.admin.model.WebCatagoryParam;
import com.admin.model.WebResource;
import com.admin.model.WebResourceParam;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;

/**
 * 
 * 
 * @author tanson lam
 * @creation 2015年12月16日
 */

@Repository("webResourceParamService")
public class WebResourceParamService {
	@Resource
	private BaseDao baseDao;

	@Resource
	private WebResourceService webResourceService;
	@Resource
	private WebCategoryParamService webCategoryParamService;

	/**
	 * 查询资源参数列表
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<WebResourceParamGroup> getResParamList(Integer resourceId) {
		List<WebResourceParam> resParamlist = baseDao.find("resource_id="
				+ resourceId + " and iseff='" + IsEff.EFFECTIVE
				+ "' order by rank", WebResourceParam.class);
		WebResource webResource = webResourceService.findById(resourceId);
		List<WebCatagoryParam> categoryParamList = webCategoryParamService
				.getDefindParams(webResource.getCategoryId());
		List<WebResourceParamGroup> groupList = new ArrayList<WebResourceParamGroup>();
		// 构造由分类参数组成的默认列表
		for (WebCatagoryParam webCatagoryParam : categoryParamList) {
			if (StringUtils.isEmpty(webCatagoryParam.getRootParamName()))
				addCatagroyParam2Group(webCatagoryParam, categoryParamList,
						groupList);
		}

		// 填充已经配置的资源参数
		for (WebResourceParam resParam : resParamlist) {
			if (StringUtils.isEmpty(resParam.getRootParamName()))
				addRootResParam2Group(resParam, resParamlist, groupList);
		}
		// 排序
		ResParamGroupComparatorUtil.sortResGroupList(groupList);
		return groupList;
	}

	private void addRootResParam2Group(WebResourceParam rootResourceParam,
			List<WebResourceParam> resParamlist,
			List<WebResourceParamGroup> groupList) {
		WebResourceParamGroup group = null;
		for (WebResourceParamGroup webResourceParamGroup : groupList) {
			if (webResourceParamGroup.getRootResourceParam().getParamName()
					.equals(rootResourceParam.getParamName())) {

				webResourceParamGroup.getRootResourceParam().setParamName(
						rootResourceParam.getParamName());
				webResourceParamGroup.getRootResourceParam().setRank(
						rootResourceParam.getRank());

				group = webResourceParamGroup;
				List<WebResourceParam> childParamList = group
						.getChildParamlist();
				childParamList = mergeParamList(rootResourceParam,
						childParamList, resParamlist);
				break;
			}
		}

		if (group == null) {
			group = new WebResourceParamGroup();
			group.setRootResourceParam(rootResourceParam);
			groupList.add(group);
			group.setChildParamlist(resParamlist);
		}
	}

	private List<WebResourceParam> mergeParamList(
			WebResourceParam rootResourceParam,
			List<WebResourceParam> defaultParamList,
			List<WebResourceParam> resParamlist) {
		List<WebResourceParam> resultlist1 = new ArrayList<WebResourceParam>();
		List<WebResourceParam> resultlist2 = new ArrayList<WebResourceParam>();
		for (WebResourceParam defaultParam : defaultParamList) {

			for (WebResourceParam resourceParam : resParamlist) {
				if (defaultParam.getParamName().equals(
						resourceParam.getParamName())
						&& defaultParam.getRootParamName().equals(
								resourceParam.getRootParamName())) {
					try {
						BeanUtils.copyProperties(defaultParam, resourceParam);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			resultlist1.add(defaultParam);
		}

		for (WebResourceParam resourceParam : resParamlist) {
			if (StringUtils.isEmpty(resourceParam.getRootParamName())
					|| !rootResourceParam.getParamName().equals(
							resourceParam.getRootParamName()))
				continue;
			boolean exist = false;
			for (WebResourceParam defaultParam : resParamlist) {
				if (defaultParam.getParamName().equals(
						resourceParam.getParamName())
						&& defaultParam.getRootParamName().equals(
								resourceParam.getRootParamName())) {
					exist = true;
				}
				if (!exist) {
					resultlist2.add(resourceParam);
				}
			}
		}
		resultlist2.addAll(resultlist1);
		return resultlist2;
	}

	/**
	 * 构造由分类参数组成的默认列表
	 * 
	 * @param rootCategoryParam
	 * @param categoryParamList
	 * @param groupList
	 * 
	 */
	private void addCatagroyParam2Group(WebCatagoryParam rootCategoryParam,
			List<WebCatagoryParam> categoryParamList,
			List<WebResourceParamGroup> groupList) {
		for (WebResourceParamGroup webResourceParamGroup : groupList) {
			if (webResourceParamGroup.getRootResourceParam().getParamName()
					.equals(rootCategoryParam.getParamName())) {
				return;
			}
		}

		WebResourceParamGroup group = new WebResourceParamGroup();
		WebResourceParam rootResourceParam = new WebResourceParam();
		rootResourceParam.setParamName(rootCategoryParam.getParamName());
		rootResourceParam.setRank(rootCategoryParam.getRank());
		group.setRootResourceParam(rootResourceParam);
		groupList.add(group);
		List<WebResourceParam> resParamlist = new ArrayList<WebResourceParam>();
		for (WebCatagoryParam param : categoryParamList) {
			if (!StringUtils.isEmpty(param.getRootParamName())
					&& param.getRootParamName().equals(
							rootCategoryParam.getParamName())) {
				WebResourceParam webResourceParam = new WebResourceParam();
				webResourceParam.setParamName(param.getParamName());
				webResourceParam.setRootParamName(param.getRootParamName());
				webResourceParam.setRank(param.getRank());
				resParamlist.add(webResourceParam);
			}
		}
		group.setChildParamlist(resParamlist);

	}

	/**
	 * 保存资源参数组
	 * 
	 * @throws Exception
	 */
	public void saveParamList(Integer resourceId,
			List<WebResourceParam> resparamList) throws Exception {
		WebResource webResource = webResourceService.findById(resourceId);
		if (webResource == null)
			throw new Exception("没有找到匹配的资源信息");
		// 删除旧的参数记录
		baseDao.executeSql("delete from web_resource_param where resource_id="
				+ resourceId);

		for (WebResourceParam param : resparamList) {
			if (StringUtils.isEmpty(param.getParamName()))
				continue;
			param.setCategoryId(webResource.getCategoryId());
			param.setResourceId(resourceId);
			param.setIseff(IsEff.EFFECTIVE);
			baseDao.save(param);
		}
	}
}