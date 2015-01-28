package com.core.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.SysParam;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository("sysParamService")
public class SysParamService {
	@Resource
	public BaseDao baseDao;

	@Cacheable(cacheName = "cacheManager")
	public List<SysParam> getSysParam(String rootCode) throws DaoException {
		return baseDao.find("root_code='" + rootCode + "' and iseff="+IsEff.EFFECTIVE+" order by seq", SysParam.class);
	}
	
	@Cacheable(cacheName = "cacheManager")
	public List<SysParam> getAllSysParam() throws DaoException {
		return baseDao.find("", SysParam.class);
	}
}
