package com.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.admin.model.SysMenu;
import com.google.gson.Gson;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

public class JsonTest {
	//@Test
	public void test1() throws JSONException {
		SysMenu menu = new SysMenu();
		menu.setId(1111);
		menu.setCreateTime(new Timestamp((new Date().getTime())));
		String json = JSONUtil.serialize(menu);
		System.out.println(json);
		Gson gson = new Gson();
		SysMenu menu2 = gson.fromJson(json, SysMenu.class);
		System.out.println(menu2);
	}
	
	@Test
	public void test2() throws JSONException {
		List<SysMenu> list = new ArrayList<SysMenu>();
		SysMenu menu = new SysMenu();
		menu.setId(1111);
		menu.setCreateTime(new Timestamp((new Date().getTime())));
		SysMenu menu1 = new SysMenu();
		menu1.setId(1111);
		menu1.setCreateTime(new Timestamp((new Date().getTime())));
		list.add(menu);
		list.add(menu1);
		String json = JSONUtil.serialize(list);
		System.out.println(json);
		Gson gson = new Gson();
		List<SysMenu> list2 = (List)gson.fromJson(json, List.class);
		System.out.println(list2);
	}
 
}
