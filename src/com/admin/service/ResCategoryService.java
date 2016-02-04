package com.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.dto.ResCategoryTreeDto;
import com.admin.model.WebCategory;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;

/**
 * 资源分类服务类
 * 
 * @author tanson lam
 * @creation 2015年5月13日
 */
@Repository("resCategoryService")
public class ResCategoryService {

    @Resource
    private BaseDao baseDao;

    /**
     * 查找用户的資源分类
     * 
     * @param userId
     * @param categoryId
     * @return
     */
    public WebCategory findOne(Integer userId, Integer categoryId) {
        return baseDao.findOne("id=" + categoryId + " and user_id=" + userId, WebCategory.class);
    }

    /**
     * 查询分类树结构
     * 
     * @param userInfo
     * @throws DaoException
     */
    public List<ResCategoryTreeDto> queryCatagoryTree(Integer userId) throws DaoException {
        List<WebCategory> categoryList = baseDao.find("user_id=" + userId + " and iseff='"
                + IsEff.EFFECTIVE + "' order by sort", WebCategory.class);
        List<ResCategoryTreeDto> moduleList = new ArrayList<ResCategoryTreeDto>();
        for (WebCategory category : categoryList) {
            boolean expand = false;
            if (category.getParentid() == -1)
                expand = true;
            ResCategoryTreeDto dto = new ResCategoryTreeDto(category.getId(),
                    category.getParentid(), category.getName(), expand, "");
            moduleList.add(dto);
        }
        return moduleList;
    }

    /**
     * 查找所有的分类
     * 
     * @return
     */
    public Map<Integer, WebCategory> findAllCategory(Integer userId) {
        List<WebCategory> list = baseDao.find("user_id=" + userId + " and iseff='"
                + IsEff.EFFECTIVE + "' order by sort", WebCategory.class);
        Map<Integer, WebCategory> map = new HashMap<Integer, WebCategory>();
        for (WebCategory webCategory : list) {
            map.put(webCategory.getId(), webCategory);
        }
        return map;
    }
}
