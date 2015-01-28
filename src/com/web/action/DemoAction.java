package com.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.admin.model.SysUser;
import com.core.app.action.base.ActionException;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;

public class DemoAction extends BaseAction {
	private static Logger logger = Logger.getLogger(DemoAction.class);

	public DemoAction() {
		// super(SysUser.class);
	}

	public void sendJson() throws ActionException {
		ResponseData responseData = null;

		try {
			String message = "kill your family~~~~";
			responseData = ResponseUtils.success("查询成功！");
			responseData.setResult(message);
		} catch (Exception e) {
			logger.error(e, e);
			responseData = ResponseUtils.fail("查询失败！");
		}
		outResult(responseData);
	}

	public void forward() {
		String page = "page/demo/attribute.jsp";
		Map<String, Object> dataModel = new HashMap<String, Object>();
		SysUser u1 = new SysUser();
		u1.setUserName("tom");
		u1.setPhone("999");
		SysUser u2 = new SysUser();
		u2.setUserName("tanson");
		u2.setPhone("110");
		List<SysUser> attrList = new ArrayList<SysUser>();
		attrList.add(u2);
		attrList.add(u1);
		dataModel.put("message", "show your attribute");
		dataModel.put("users", attrList);
		dataModel.put("includejsp", "simple2.jsp");
		dataModel.put("location", "simple2.jsp");
		forward(page, dataModel);
	}

	public void render() {
		HashMap<String, Map> dataModel = new HashMap<String, Map>();
		HashMap<String, String> usermap = new HashMap<String, String>();
		usermap.put("name", "tanson");
		usermap.put("password", "11111");
		dataModel.put("user", usermap);
		render("test.ftl", dataModel);
	}

}
