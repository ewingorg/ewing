package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResourceScreenshot;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 资源截图
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
@Repository("webResourceScreenDao")
public class WebResourceScreenDao {
    
    @Resource
    private BaseDao baseDao;

    public List<WebResourceScreenshot> getResScreenshot(Integer resourceId) {
        return baseDao.find("resource_id=" + resourceId + " and iseff='" + IsEff.EFFECTIVE
                + "' order by rank", WebResourceScreenshot.class);
    }
}
