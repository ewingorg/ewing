package com.core.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Repository;

import com.admin.model.SysUser;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;

/**
 * 系统用户
 * 
 */
@Repository("sysUserService")
public class SysUserService {
	@Resource
	public BaseDao baseDao;

	/**
	 * 查找系统用户
	 * 
	 * @param userName
	 * @return
	 * @throws DaoException
	 */
	public List<SysUser> findUser(String userName) throws DaoException {
		if (StringUtils.isEmpty(userName))
			throw new IllegalArgumentException("userName should not be null");
		return baseDao.find(" user_name='" + userName + "'", SysUser.class);
	}
	
	public List<SysUser> findUserByRole(int roleId) throws DaoException { 
		return baseDao.find(" role_id="+roleId, SysUser.class);
	}
	
	public List<SysUser> findUserByDepartment(int depId) throws DaoException { 
		return baseDao.find(" dep_id="+depId, SysUser.class);
	}

	public List<SysUser> findAllUser() throws DaoException {
		return baseDao.find("", SysUser.class);
	}

	public SysUser findOne(int userId) throws DaoException {
		if (userId == 0)
			throw new IllegalArgumentException("userId should not be null");
		return baseDao.findOne(userId, SysUser.class);
	}
}
