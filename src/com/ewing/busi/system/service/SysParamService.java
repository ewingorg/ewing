package com.ewing.busi.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysParam;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
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
