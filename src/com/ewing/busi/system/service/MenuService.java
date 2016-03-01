/**
 * 
 */
package com.ewing.busi.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.busi.system.model.SysMenu;
import com.ewing.core.app.bean.TreeObject;
import com.ewing.core.app.bean.UserInfo;
import com.ewing.core.app.service.CacheModelService;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * @author tanson lam
 * 
 */
@Repository("menuService")
public class MenuService {
	@Resource
	private BaseDao baseDao;
	@Resource
	private CacheModelService cacheModelService;

	/**
	 * 查询一级模块菜单
	 * 
	 * @param userInfo
	 * @throws DaoException
	 */
	public List<TreeObject> queryModuleTree(UserInfo userInfo)
			throws DaoException {
		List<SysMenu> menuList = cacheModelService.find(
				" iseff='1' order by parentid,sort", SysMenu.class);
		List<TreeObject> moduleList = new ArrayList<TreeObject>();
		for (int i = 0; i < menuList.size(); i++) {
			SysMenu sysMenu = (SysMenu) menuList.get(i);
			/*
			 * int menuId = sysMenu.getId(); if
			 * (sysRightRelService.containRelMenu(userInfo, menuId)) {
			 */
			TreeObject treeObject = copy2TreeObject(sysMenu);
			moduleList.add(treeObject);
			// }
		}
		for (TreeObject t1 : moduleList) {
			for (TreeObject t2 : moduleList) {
				if (t2.getParentId().equals(t1.getId())) {
					List<TreeObject> childList = t1.getChildren();
					if (childList == null) {
						childList = new ArrayList<TreeObject>();
						t1.setChildren(childList);
					}
					childList.add(t2);
				}
			}
		}
		return moduleList;
	}
	
	/**
	 * 获取导航菜单第一位菜单设置
	 * @return
	 * @throws DaoException
	 */
	public SysMenu queryFirstTopMenu() throws DaoException {
		List<SysMenu> menuList = cacheModelService.find(
				" level=1 and iseff='1' order by sort", SysMenu.class);
		if (!menuList.isEmpty()) {
			return menuList.get(0);
		}
		return null;
	}

	private TreeObject copy2TreeObject(SysMenu sysMenu) {
		TreeObject treeObject = new TreeObject();
		treeObject.setId(sysMenu.getId());
		treeObject.setText(sysMenu.getName());
		String url = null;
		Integer parentId = sysMenu.getParentid() == 0
				? sysMenu.getId()
				: sysMenu.getParentid();
		if (sysMenu.getUrl() != null) {
			if (sysMenu.getUrl().indexOf("?") != -1)
				url = sysMenu.getUrl() + "&parentMenuId=" + parentId
						+ "&menuId=" + sysMenu.getId();
			else
				url = sysMenu.getUrl() + "?parentMenuId=" + parentId
						+ "&menuId=" + sysMenu.getId();
		}
		treeObject.setHrefTarget(url);
		treeObject.setIconCls(sysMenu.getIcon());
		treeObject.setLevel(sysMenu.getLevel());
		treeObject.setParentId(sysMenu.getParentid());
		return treeObject;
	}
}
