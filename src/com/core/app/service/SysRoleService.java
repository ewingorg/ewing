package com.core.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.SysRole;
import com.admin.model.SysUser;
import com.core.app.bean.OrgStructTreeObject;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;
import com.util.SqlUtil;

@Repository("sysRoleService")
public class SysRoleService {
	@Resource
	public BaseDao baseDao;

	/**
	 * 获取组织架构树
	 * 
	 * @return
	 * @throws DaoException
	 */
	public OrgStructTreeObject queryRoleStructTree(Integer[] roleIds)
			throws DaoException {
		OrgStructTreeObject treeObject = new OrgStructTreeObject();
		queryRoleTree(treeObject, roleIds);
		for (OrgStructTreeObject org : treeObject.getChildren()) {
			if (org.getType() != null && "0".equals(org.getType())) {
				int roleId = Integer.valueOf(org.getId().toString().replace("1000", ""));
				List<SysUser> userList = queryRoleUnderUser(roleId);
				if (userList.isEmpty())
					continue;
				for (SysUser sysUser : userList) {
					OrgStructTreeObject userTreeObject = userCopy2TreeObject(sysUser);
					org.setLeaf(false);
					userTreeObject.setLeaf(true);
					org.getChildren().add(userTreeObject);
				}
			}
		}
		return treeObject;
	}

	private void queryRoleTree(OrgStructTreeObject parentTree, Integer[] roleIds)
			throws DaoException {
		List<SysRole> roleList = querySysRoleTreeData(roleIds);
		if (roleList.isEmpty()) {
			parentTree.setLeaf(true);
			return;
		}
		parentTree.setLeaf(false);
		List<OrgStructTreeObject> childList = new ArrayList<OrgStructTreeObject>();
		for (int i = 0; i < roleList.size(); i++) {
			SysRole sysRole = (SysRole) roleList.get(i); 
			OrgStructTreeObject treeObject = roleCopy2TreeObject(sysRole); 
			childList.add(treeObject);
		}
		parentTree.setChildren(childList);
	}

	private OrgStructTreeObject roleCopy2TreeObject(SysRole sysRole) {
		OrgStructTreeObject treeObject = new OrgStructTreeObject();
		treeObject.setId(Integer.valueOf("1000"+String.valueOf(sysRole.getId())));
		treeObject.setText(sysRole.getName());
		treeObject.setType("0");
		treeObject.getAttributes().put("type", "0");
		return treeObject;
	}

	private OrgStructTreeObject userCopy2TreeObject(SysUser sysUser) {
		OrgStructTreeObject treeObject = new OrgStructTreeObject();
		treeObject.setId(sysUser.getId());
		treeObject.setText(sysUser.getUserName());
		treeObject.setType("1");
		treeObject.getAttributes().put("type", "1");
		return treeObject;
	}

	private List<SysRole> querySysRoleTreeData(Integer[] roleIds)
			throws DaoException {
		if (roleIds == null || roleIds.length == 0)
			return baseDao.find("", SysRole.class);
		return baseDao.find("id in (" + SqlUtil.array2InCondition(roleIds)+")",
				SysRole.class);
	}

	private List<SysUser> queryRoleUnderUser(int roleId) throws DaoException {
		return baseDao.find("role_id= " + roleId, SysUser.class);
	}
}
