package com.core.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.admin.model.SysDepartment;
import com.admin.model.SysUser;
import com.core.app.bean.OrgStructTreeObject;
import com.core.app.constant.OrgQueryType;
import com.core.jdbc.BaseDao;
import com.core.jdbc.DaoException;

@Repository("sysOrgService")
public class SysOrgService {
	@Resource
	public BaseDao baseDao;

	/**
	 * 获取组织架构树
	 * @return
	 * @throws DaoException
	 */
	public OrgStructTreeObject queryOrgStructTree(OrgQueryType type) throws DaoException {
		String parentId = "0";
		OrgStructTreeObject treeObject = new OrgStructTreeObject();
		treeObject.setId(Integer.valueOf(parentId));
		queryDepartmentTree(treeObject, treeObject.getId());
		if(OrgQueryType.PERSONAL.equals(type)){
			for (OrgStructTreeObject org : treeObject.getChildren()) {
				if (org.getType() != null && "0".equals(org.getType())) {
					int depId = Integer.valueOf(org.getId());
					List<SysUser> userList = queryDepUnderUser(depId);
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
		}
		return treeObject;
	}

	private void queryDepartmentTree(OrgStructTreeObject parentTree,
			int parentId) throws DaoException {
		List<SysDepartment> depList = querySysDepartmentTreeData("parentid="
				+ parentId + " order by id");
		if (depList.isEmpty()) {
			parentTree.setLeaf(true);
			return;
		}
		parentTree.setLeaf(false);
		List<OrgStructTreeObject> childList = new ArrayList<OrgStructTreeObject>();
		for (int i = 0; i < depList.size(); i++) {
			SysDepartment sysDepartment = (SysDepartment) depList.get(i);
			int depId = sysDepartment.getId();
			OrgStructTreeObject treeObject = depCopy2TreeObject(sysDepartment);
			queryDepartmentTree(treeObject, depId);
			childList.add(treeObject);
		}
		parentTree.setChildren(childList);
	}

	private OrgStructTreeObject depCopy2TreeObject(SysDepartment sysDepartment) {
		OrgStructTreeObject treeObject = new OrgStructTreeObject();
		treeObject.setId(sysDepartment.getId());
		treeObject.setText(sysDepartment.getName());
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

	private List<SysDepartment> querySysDepartmentTreeData(String sql)
			throws DaoException {
		return baseDao.find(sql, SysDepartment.class);
	}

	private List<SysUser> queryDepUnderUser(int depId) throws DaoException {
		return baseDao.find("dep_id=" + depId, SysUser.class);
	}
}
