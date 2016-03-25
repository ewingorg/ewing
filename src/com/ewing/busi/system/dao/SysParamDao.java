package com.ewing.busi.system.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysParam;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年3月23日
 * 
 */
@Repository("sysParamDao")
public class SysParamDao {
    @Resource
    private BaseDao baseDao;

    public SysParam findByRootCodeAndParamName(String rootCode, String paramName) {
        StringBuilder query = new StringBuilder();
        query.append("root_code = '").append(rootCode).append("'");
        query.append("and param_name = '").append(paramName).append("'");
        query.append("and iseff = '").append(IsEff.EFFECTIVE).append("'");
        return baseDao.findOne(query.toString(), SysParam.class);
    }

}
