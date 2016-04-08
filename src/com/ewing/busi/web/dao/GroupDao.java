package com.ewing.busi.web.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.web.contant.GroupType;
import com.ewing.busi.web.model.WebTemplateGroupkey;
import com.ewing.core.app.constant.IsEff;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 类/接口注释
 * 
 * @author tansonlam
 * @createDate 2016年3月14日
 * 
 */
@Repository("groupDao")
public class GroupDao {
    @Resource
    private BaseDao baseDao;

    /**
     * 根据分组类型获取分组列表，并封装到{@link #SysParam}对象列表返回。
     */
    public List<WebTemplateGroupkey> getGroupParamList(Integer templatePackageId, GroupType groupType)
            throws DaoException {
        return baseDao.find("groupType='" + groupType.getCode() + "' and iseff=" + IsEff.EFFECTIVE
                + " and template_package_id=" + templatePackageId + " order by id",
                WebTemplateGroupkey.class);

    }
}
