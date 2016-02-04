package com.ewing.busi.resource.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.category.model.WebCategory;
import com.ewing.busi.category.service.ResCategoryService;
import com.ewing.busi.resource.dao.WebResourceDao;
import com.ewing.busi.resource.dto.WebResourceDto;
import com.ewing.busi.resource.model.WebResource;
import com.ewing.busi.web.service.WebAttrConfService;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 资源服务类
 * 
 * @author tanson lam
 * @creation 2015年5月20日
 */
@Repository("webResourceService")
public class WebResourceService {
    @Resource
    private BaseDao baseDao;
    @Resource
    private WebResourceDao webResourceDao;
    @Resource
    private WebAttrConfService webAttrConfService;
    @Resource
    private ResCategoryService resCategoryService;
    @Resource
    private BaseModelService baseModelService;

    public boolean editResource(WebResource resource) {
        baseDao.update(resource);  
        return true;
    }

    /**
     * 查找单个资源信息
     * 
     * @param resourceId
     * @return
     */
    public WebResource findById(Integer resourceId) {
        return baseDao.findOne(resourceId, WebResource.class);
    }

    /**
     * 查找用户的资源
     * 
     * @param userId
     * @param resourceId
     * @return
     */
    public WebResource findOne(Integer userId, Integer resourceId) {
        return webResourceDao.findOne(userId, resourceId);
    }

    public PageBean pageQueryResource(Integer userId, String condition, String order,
            Integer pageSize, Integer page) {
        PageBean pageBean = webResourceDao.pageQueryResource(userId, condition, order, pageSize,
                page);
        List<WebResource> list = (List<WebResource>) pageBean.getResult();

        List<WebResourceDto> dtoList = new ArrayList<WebResourceDto>();
        Map<Integer, WebCategory> categoryMap = resCategoryService.findAllCategory(userId);
        for (WebResource webResource : list) {
            WebResourceDto dto = new WebResourceDto();
            try {
                BeanUtils.copyProperties(dto, webResource);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            WebCategory webCategory = categoryMap.get(webResource.getCategoryId());
            if (webCategory != null)
                dto.setCategoryName(webCategory.getName());
            dtoList.add(dto);
        }
        pageBean.setResult(dtoList);
        return pageBean;
    }

    /**
     * 保存
     * 
     * @param resource
     * @param attrList
     * @return
     */ 
    public boolean saveResource(WebResource resource ) {
        baseDao.save(resource); 
        return true;
    }
}
