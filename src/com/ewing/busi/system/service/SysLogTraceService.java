package com.ewing.busi.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysLogTrace;
import com.ewing.busi.system.model.SysMenu;
import com.ewing.core.app.service.CacheModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

@Repository("sysLogTraceService")
public class SysLogTraceService {
	private static Logger logger = Logger.getLogger(SysLogTraceService.class);
	@Resource
	private BaseDao baseDao;
	@Resource
	private CacheModelService cacheModelService;

	public void logTrace(int userId, String menuPath) {
		try {
			if (userId == 0)
				return;
			if (menuPath == null || menuPath.isEmpty())
				return;
			List<SysMenu> menuList = cacheModelService.find("url='" + menuPath
					+ "'", SysMenu.class);
			if (menuList.isEmpty()) {
				return;
			}
			SysMenu menu = menuList.get(0);
			SysLogTrace vo = new SysLogTrace();
			vo.setLoginTime(new java.sql.Timestamp(new java.util.Date()
					.getTime()));
			vo.setModuleName(menu.getName());
			vo.setUserId(userId);
			baseDao.save(vo);
		} catch (DaoException e) {
			logger.error("error in sys log trace", e);
		}
	}
}
