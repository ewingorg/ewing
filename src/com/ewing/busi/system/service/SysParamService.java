package com.ewing.busi.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysParam;
import com.ewing.common.exception.SysParamException;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository("sysParamService")
public class SysParamService {
    @Resource
    public BaseDao baseDao;

    @Cacheable(cacheName = "cacheManager")
    public List<SysParam> getSysParam(String rootCode) {
        return baseDao.find("root_code='" + rootCode + "' and iseff=" + IsEff.EFFECTIVE
                + " order by seq", SysParam.class);
    }

    @Cacheable(cacheName = "cacheManager")
    public List<SysParam> getAllSysParam() throws DaoException {
        return baseDao.find("", SysParam.class);
    }
    
    /**
     * 根据指定的值获取系统参数
     * @param rootCode
     * @param value
     * @return
     * @throws SysParamException
     */
    public SysParam getByValue(String rootCode, String value) throws SysParamException {
        if (StringUtils.isEmpty(rootCode) || StringUtils.isEmpty(value))
            throw new SysParamException("没有匹配的系统参数");
        List<SysParam> params = getSysParam(rootCode);
        for (SysParam sysParam : params) {
            if (sysParam.getParamValue().equals(value)) {
                return sysParam;
            }
        }
        throw new SysParamException("没有匹配的系统参数");
    }
}
