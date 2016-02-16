package com.ewing.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ewing.core.app.action.base.ActionException;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;

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

    public void render() {
        HashMap<String, Map> dataModel = new HashMap<String, Map>();
        HashMap<String, String> usermap = new HashMap<String, String>();
        usermap.put("name", "tanson");
        usermap.put("password", "11111");
        dataModel.put("user", usermap);
        render("test.ftl", dataModel);
    }

}
