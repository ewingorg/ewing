package com.ewing.core.app.bean;

import java.util.ArrayList;
import java.util.List;

import com.ewing.core.json.JsonUtil;

/**
 * @author tanson lin
 * 
 * @create:2012-2-26
 * @description:在EXT构建TREE的公用对象
 */
public class TreeObject {

	private String text;
	private Boolean expanded;
	private Boolean leaf;
	private Integer id;
	private String hrefTarget;
	private String iconCls;
	private String level;
	private Integer parentId;
	private List<TreeObject> children = new ArrayList<TreeObject>();

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<TreeObject> getChildren() {
		return children;
	}

	public void setChildren(List<TreeObject> children) {
		this.children = children;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static void main(String[] args) {
		TreeObject menuObject = new TreeObject();
		menuObject.setId(1111);
		menuObject.setText("系统菜单");

		TreeObject menuObject1 = new TreeObject();
		menuObject1.setId(22222);
		menuObject1.setText("系统菜单1");
		menuObject1.setLeaf(true);
		TreeObject menuObject2 = new TreeObject();
		menuObject2.setId(3333);
		menuObject2.setText("系统菜单2");
		menuObject2.setLeaf(true);
		List list = new ArrayList();
		list.add(menuObject1);
		list.add(menuObject2);
		menuObject.setChildren(list);
		String json = JsonUtil.tranBean2String(menuObject).toString();
		System.out.println("[" + json + "]");
	}
}
