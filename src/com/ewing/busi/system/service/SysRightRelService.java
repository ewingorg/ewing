package com.ewing.busi.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ewing.busi.system.model.SysRightRel;
import com.ewing.core.app.bean.UserInfo;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 权限关联设置
 * 
 */
@Repository("sysRightRelService")
public class SysRightRelService {
	/**
	 * 权限关联类型
	 * 
	 */
	public enum RelType {
		User(0), role(1), Department(2);
		private int type;

		private RelType(int type) {
			this.type = type;
		}

		public int getType() {
			return this.type;
		}
	}

	@Resource
	public BaseDao baseDao;

	/**
	 * 获取用户下所有的权限菜单
	 * 
	 * @param userInfo
	 * @return
	 * @throws DaoException
	 */
	public List<Integer> getUserAllRelMenu(UserInfo userInfo)
			throws DaoException {
		List<Integer> allRelList = new ArrayList<Integer>();
		List<Integer> userRelList = getUserRelMenu(userInfo);
		List<Integer> roleRelList = getRoleRelMenu(userInfo);
		List<Integer> depRelList = getDepRelMenu(userInfo);
		allRelList.addAll(userRelList);
		allRelList.addAll(roleRelList);
		allRelList.addAll(depRelList);
		userInfo.setRelMenus(allRelList);
		return allRelList;
	}

	/**
	 * 用户是否有该菜单的访问权限
	 * @param userInfo
	 * @param menuId
	 * @return
	 */
	public boolean containRelMenu(UserInfo userInfo, Integer menuId) { 
		 for (Integer id : userInfo.getRelMenus()) {
			if (id.equals(menuId))
				return true;
		}
		return false; 
		//return true;
	}

	/**
	 * 获取用户的权限菜单
	 * 
	 * @param userInfo
	 * @return
	 * @throws DaoException
	 */
	public List<Integer> getUserRelMenu(UserInfo userInfo) throws DaoException {
		List<Integer> memuList = new ArrayList<Integer>();
		int userId = userInfo.getId();
		List<SysRightRel> userRelList = baseDao.find(
				"type='" + RelType.User.getType() + "' and rel_id=" + userId,
				SysRightRel.class);
		for (SysRightRel sysRightRel : userRelList) {
			memuList.add(sysRightRel.getMenuId());
		}
		return memuList;
	}

	/**
	 * 获取用户的角色菜单
	 * 
	 * @param userInfo
	 * @return
	 * @throws DaoException
	 */
	public List<Integer> getRoleRelMenu(UserInfo userInfo) throws DaoException {
		List<Integer> memuList = new ArrayList<Integer>();
		int roleId = userInfo.getRoleId();
		List<SysRightRel> roleRelList = baseDao.find(
				"type='" + RelType.role.getType() + "' and rel_id=" + roleId,
				SysRightRel.class);
		for (SysRightRel sysRightRel : roleRelList) {
			memuList.add(sysRightRel.getMenuId());
		}
		return memuList;
	}

	/**
	 * 获取用户的部门权限菜单
	 * 
	 * @param userInfo
	 * @return
	 * @throws DaoException
	 */
	public List<Integer> getDepRelMenu(UserInfo userInfo) throws DaoException {
		List<Integer> memuList = new ArrayList<Integer>();
		int depId = userInfo.getDepId();
		List<SysRightRel> depRelList = baseDao.find("type='"
				+ RelType.Department.getType() + "' and rel_id=" + depId,
				SysRightRel.class);
		for (SysRightRel sysRightRel : depRelList) {
			memuList.add(sysRightRel.getMenuId());
		}
		return memuList;
	}

	/**
	 * 权限设置
	 * 
	 * @param type
	 * @param relId
	 * @param menuIds
	 * @throws DaoException
	 */
	@Transactional
	public void rightRelSetting(String type, String relId, Integer[] menuIds)
			throws DaoException {
		baseDao.executeSql("delete from sys_right_rel where type='" + type
				+ "' and rel_id=" + relId);
		for (Integer menuId : menuIds) {
			SysRightRel sysRightRel = new SysRightRel();
			sysRightRel.setType(type);
			sysRightRel.setMenuId(menuId);
			sysRightRel.setRelId(relId);
			baseDao.save(sysRightRel);
		}

	}

	/**
	 * 查询关联的菜单
	 * 
	 * @param type
	 * @param relId
	 * @param menuIds
	 * @throws DaoException
	 */
	@Transactional
	public List<Integer> queryRelMenu(String type, int relId)
			throws DaoException {
		List<Integer> menuList = new ArrayList<Integer>();
		List<SysRightRel> relList = baseDao.find("type='" + type
				+ "' and rel_id=" + relId, SysRightRel.class);
		for (SysRightRel sysRightRel : relList) {
			menuList.add(sysRightRel.getMenuId());
		}
		return menuList;

	}
}
