package com.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.constant.GroupType;
import com.admin.model.SysParam;
import com.admin.model.WebGroup;
import com.core.app.constant.IsEff;
import com.core.app.service.CacheModelService;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;

/**
 * @author tanson lam
 * 
 * @creation 2015年1月10日
 */
@Repository("groupService")
public class GroupService {

	@Resource
	private BaseDao baseDao;
	@Resource
	private CacheModelService cacheModelService;

	/**
	 * 根据分组类型获取分组列表，并封装到{@link #SysParam}对象列表返回。
	 */
	public List<SysParam> getGroupParamList(GroupType groupType)
			throws DaoException {
		List<WebGroup> grouplist = baseDao.find("groupType='" + groupType.getCode()
				+ "' and iseff=" + IsEff.EFFECTIVE + " order by id",
				WebGroup.class);
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
