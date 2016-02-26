package com.ewing.busi.resource.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.resource.model.WebResource;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 资源DAO
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
@Repository("webResourceDao")
public class WebResourceDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 查找用户的资源
     * 
     * @param userId
     * @param resourceId
     * @return
     */
    public WebResource findOne(Integer userId, Integer resourceId) {
        return baseDao.findOne("id=" + resourceId + " and user_id=" + userId, WebResource.class);
    }

    /**
     * 分页查询资源
     * @param userId
     * @param condition
     * @param order
     * @param pageSize
     * @param page
     * @return
     */
    public PageBean<WebResource> pageQueryResource(Integer userId, String condition, String order,
            Integer pageSize, Integer page) {
        condition = condition + " and user_id=" + userId + " and iseff='" + IsEff.EFFECTIVE + "'";
        return baseDao.pageQuery(condition, order, pageSize, page, WebResource.class);

    }

}
