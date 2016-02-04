package com.admin.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.admin.dto.WebResourceDto;
import com.admin.model.WebAttrConf;
import com.admin.model.WebCategory;
import com.admin.model.WebResource;
import com.admin.model.WebResourceAttr;
import com.core.app.constant.IsEff;
import com.core.app.service.BaseModelService;
import com.core.jdbc.BaseDao;
import com.core.jdbc.util.PageBean;

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
    private WebAttrConfService webAttrConfService;
    @Resource
    private ResCategoryService resCategoryService;
    @Resource
    private BaseModelService baseModelService;

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

    public PageBean pageQueryResource(Integer userId, String condition, String order,
            Integer pageSize, Integer page) {
        condition = condition + " and user_id=" + userId + " and iseff='" + IsEff.EFFECTIVE + "'";
        PageBean pageBean = baseModelService.pageQuery(condition, order, pageSize, page,
                WebResource.class);
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
     * 获取资源属性值
     * 
     * @param resourceId 资源id
     * @return
     */
    public List<WebResourceAttr> getResourceAttrs(Integer resourceId) {
        return baseDao.find("resource_id=" + resourceId + " and iseff='" + IsEff.EFFECTIVE
                + "' order by rank", WebResourceAttr.class);
    }

    /**
     * 获取资源属性
     * 
     * @param resourceId
     * @param attrKey
     * @return
     */
    public WebResourceAttr getResourceAttr(Integer resourceId, String attrKey) {
        return baseDao.findOne("resource_id=" + resourceId + " and attr_key='" + attrKey
                + "' and iseff='" + IsEff.EFFECTIVE + "' order by rank", WebResourceAttr.class);
    }

    /**
     * 保存
     * 
     * @param resource
     * @param attrList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveResource(WebResource resource, List<WebResourceAttr> attrList) {
        baseDao.save(resource);
        Integer resourceId = resource.getId();
        Integer templateId = resource.getTemplateId();
        List<WebAttrConf> attrConfList = webAttrConfService.getTemplateAttrs(templateId);
        for (WebResourceAttr attr : attrList) {
            for (WebAttrConf webAttrConf : attrConfList) {
                if (webAttrConf.getAttrKey().equals(attr.getAttrKey())) {
                    attr.setAttrName(webAttrConf.getAttrName());
                    attr.setRank(webAttrConf.getSort());
                }
            }
            attr.setIseff(IsEff.EFFECTIVE);
            attr.setResourceId(resourceId);
            baseDao.save(attr);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean editResource(WebResource resource, List<WebResourceAttr> changeAttrList) {
        baseDao.update(resource);
        Integer resourceId = resource.getId();
        Integer templateId = resource.getTemplateId();
        List<WebAttrConf> attrConfList = webAttrConfService.getTemplateAttrs(templateId);
        for (WebResourceAttr attr : changeAttrList) {
            for (WebAttrConf webAttrConf : attrConfList) {
                if (webAttrConf.getAttrKey().equals(attr.getAttrKey())) {
                    attr.setAttrName(webAttrConf.getAttrName());
                    attr.setRank(webAttrConf.getSort());
                }
            }
            attr.setIseff(IsEff.EFFECTIVE);
            attr.setResourceId(resourceId);
            WebResourceAttr oldAttr = getResourceAttr(resourceId, attr.getAttrKey());
            if (oldAttr != null) {
                attr.setAttrValue(attr.getAttrValue());
                baseDao.update(attr);
            } else {
                baseDao.save(attr);
            }

        }
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
}
