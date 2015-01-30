package com.admin.service;

import org.junit.Test;

import com.core.factory.SpringCtx;
import com.core.jdbc.util.PageBean;

public class RelResServiceTest {
	@Test
	public void executePageQuery() throws Exception {
		try { 
			RelResService relResService = (RelResService) SpringCtx.getByBeanName("relResService");
			PageBean page = relResService.listRelResourceByCategory(7, 20, 1);
			System.out.println(page.getTotalCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
