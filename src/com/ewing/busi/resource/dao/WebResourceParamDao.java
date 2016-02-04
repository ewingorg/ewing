package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResourceParam;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 资源参数DAO
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
@Repository("webResourceParamDao")
public class WebResourceParamDao {
    @Resource
    private BaseDao baseDao;

    public List<WebResourceParam> getParamByResourceId(Integer resourceId) {
        return baseDao.find("resource_id=" + resourceId + " and iseff='" + IsEff.EFFECTIVE
                + "' order by rank", WebResourceParam.class);
    }

    public void deleteParams(Integer resourceId) {
        baseDao.executeSql("delete from web_resource_param where resource_id=" + resourceId);
    }
}
