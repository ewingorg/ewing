package com.ewing.busi.web.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.model.WebTemplatePackage;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 模板包DAO
 * 
 * @author tansonlam
 * @createDate 2016年3月14日
 * 
 */
@Repository("webTemplatePackageDao")
public class WebTemplatePackageDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 查询可以选择的模板包
     * 
     * @return
     */
    public List<WebTemplatePackage> querySelectTemplatePack() {
        return baseDao.find("iseff ='" + IsEff.EFFECTIVE + "'", WebTemplatePackage.class);
    }
     
}
