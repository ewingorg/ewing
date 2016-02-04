package com.ewing.busi.resource.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResourcePrice;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 资源价格DAO
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
@Repository("webResourcePriceDao")
public class WebResourcePriceDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 获取资源价钱
     * 
     * @param resourceId
     * @return
     */
    public List<WebResourcePrice> getResourcePrice(Integer resourceId) {
        return baseDao.find("resource_id=" + resourceId + " and iseff='" + IsEff.EFFECTIVE
                + "' order by rank", WebResourcePrice.class);
    }

}
