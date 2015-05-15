package com.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.dto.ResCategoryTreeDto;
import com.admin.model.SysMenu;
import com.admin.model.WebResourceCategory;
import com.core.app.bean.TreeObject;
import com.core.app.bean.UserInfo;
import com.core.app.constant.IsEff;
import com.core.app.service.CacheModelService;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;

/**
 * 资源分类服务类
 * 
 * @author tanson lam
 * @creation 2015年5月13日
 */
@Repository("resCategoryService")
public class ResCategoryService {

	@Resource
	private BaseDao baseDao;
	@Resource
	private CacheModelService cacheModelService;

	/**
	 * 查询分类树结构
	 * 
	 * @param userInfo
	 * @throws DaoException
	 */
	public List<ResCategoryTreeDto> queryCatagoryTree() throws DaoException {
		List<WebResourceCategory> categoryList = baseDao.find(" iseff='"
				+ IsEff.EFFECTIVE + "' order by sort",
				WebResourceCategory.class);
		List<ResCategoryTreeDto> moduleList = new ArrayList<ResCategoryTreeDto>();
		for (WebResourceCategory category : categoryList) {
			boolean expand = false;
			if(category.getParentid()==-1)
				expand = true;
			ResCategoryTreeDto dto = new ResCategoryTreeDto(category.getId(),
					category.getParentid(), category.getName(), expand, "");
			moduleList.add(dto);
		}
		return moduleList;
	}
}
