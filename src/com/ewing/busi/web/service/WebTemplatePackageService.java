package com.ewing.busi.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysParam;
import com.ewing.busi.web.dao.WebTemplatePackageDao;
import com.ewing.busi.web.model.WebTemplateGroupkey;
import com.ewing.busi.web.model.WebTemplatePackage;

/**
 * 模板包服务类
 * 
 * @author tansonlam
 * @createDate 2016年3月14日
 * 
 */
@Repository("webTemplatePackageService")
public class WebTemplatePackageService {
    @Resource
    private WebTemplatePackageDao webTemplatePackageDao;

    /**
     * 查询可以选择模板包
     * 
     * @return
     */
    public List<SysParam> querySelectTemplatePack() {
        List<WebTemplatePackage> templateList = webTemplatePackageDao.querySelectTemplatePack();
        List<SysParam> sysParamList = new ArrayList<SysParam>();
        for (WebTemplatePackage g : templateList) {
            SysParam p = new SysParam();
            p.setParamValue(g.getId().toString());
            p.setParamName(g.getName());
            sysParamList.add(p);
        }

        return sysParamList;
    }
}
