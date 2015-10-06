package com.admin.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.admin.model.SysMenu;
import com.admin.service.MenuService;
import com.core.app.action.base.BaseAction;
import com.core.app.bean.TreeObject;
import com.core.jdbc.DaoException;

/**
 * 后台管理主頁展示类
 * 
 * @author tanson lam
 * @creation 2015年1月10日
 */

public class MainAction extends BaseAction {
	private static Logger logger = Logger.getLogger(MainAction.class);
	public static final String page = "/admin/main.html";

	@Resource
	private MenuService menuService;

	public void show() throws DaoException, IllegalAccessException,
			InvocationTargetException {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		List<TreeObject> menuList = menuService.queryModuleTree(null);
		SysMenu firstTopmenu = menuService.queryFirstTopMenu();
		TreeObject parentMenu = new TreeObject();
		TreeObject menu = new TreeObject();
		String parentMenuId = request.getParameter("parentMenuId");
		String menuId = request.getParameter("menuId");
		for (TreeObject m : menuList) {
			if (parentMenuId != null
					&& m.getId().equals(Integer.valueOf(parentMenuId)))
				BeanUtils.copyProperties(parentMenu, m);
			if (menuId != null && m.getId().equals(Integer.valueOf(menuId)))
				BeanUtils.copyProperties(menu, m);
		}

		request.getSession().setAttribute("parentMenu", parentMenu);
		request.getSession().setAttribute("curMenu", menu);
		dataModel.put("htmlheadload", true);
		dataModel.put("menuList", menuList);
		dataModel.put("firstTopmenuId",
				firstTopmenu != null ? firstTopmenu.getId() : 0);
		render(page, dataModel);

	}

}
