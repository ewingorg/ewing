package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResourceSpec;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 资源规格DAO
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
@Repository("webResourceSpecDao")
public class WebResourceSpecDao {

    @Resource
    private BaseDao baseDao;

    public List<WebResourceSpec> getResourceSpec(Integer resourceId) {
        return baseDao.find("resource_id=" + resourceId + " and iseff='" + IsEff.EFFECTIVE
                + "' order by rank", WebResourceSpec.class);
    }

    /**
     * 根据名称查找规格信息
     * 
     * @param resourceId
     * @param specName
     * @return
     */
    public WebResourceSpec findBySpec(Integer resourceId, String specName) {
        return baseDao.findOne("resource_id=" + resourceId + " and spec='" + specName
                + "' and iseff='" + IsEff.EFFECTIVE + "'", WebResourceSpec.class);
    }
}
