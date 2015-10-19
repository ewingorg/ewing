/**
 * 
 */
package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.WebResourceScreenshot;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;
/**
 * 
 * 
 * @author tanson lam
 * @creation 2015年10月15日
 */
@Repository("webResourceScreenService")
public class WebResourceScreenService {
	@Resource
	private BaseDao baseDao;

	public List<WebResourceScreenshot> getResScreenshot(Integer resourceId) {
		return baseDao.find("resource_id=" + resourceId + " and iseff='"
				+ IsEff.EFFECTIVE + "' order by rank",
				WebResourceScreenshot.class);
	}
}
