package com.core.jdbc;

import org.junit.Test;

import com.admin.model.SysMenu;
import com.core.factory.SpringCtx;
import com.core.jdbc.util.PageBean;

/**
 * 
 * 
 * @author tanson lam
 * @creation 2015年1月30日
 */
public class BaseDaoTest {
	@Test
	public void executePageQuery() throws Exception {
		try {
			String sql = "select * from sys_menu";
			BaseDao baseDao = (BaseDao) SpringCtx.getByBeanName("baseDao");
			PageBean page = baseDao.executePageQuery(sql, 2, 0, SysMenu.class);
			System.out.println(page.getTotalCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
