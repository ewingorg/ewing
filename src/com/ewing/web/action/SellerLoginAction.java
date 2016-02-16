package com.ewing.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.seller.model.Seller;
import com.ewing.busi.seller.service.SellerService;
import com.ewing.core.app.action.base.ActionException;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.action.base.ResponseData;
import com.ewing.core.app.action.base.ResponseUtils;
import com.ewing.core.app.bean.UserInfo;
import com.ewing.core.app.control.SessionControl;
import com.ewing.core.servlet.CaptchaServlet;

/**
 * 系统登陆
 * 
 */
public class SellerLoginAction extends BaseAction {
    private static Logger logger = Logger.getLogger(SellerLoginAction.class);
    @Resource
    private SellerService sellerService;
    private static final String LOGIN_PAGE = "/admin/login.html";
   
    /**
     * 跳转到用户登陆页
     */
    public void toLogin() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("hl", true);
        render(LOGIN_PAGE, dataModel);
    }

    /**
     * 用户登陆
     * 
     * @throws ActionException
     */
    public void userLogin() throws ActionException {
        ResponseData responseData;
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String checkCode = request.getParameter("checkCode");
            if (!CaptchaServlet.validate(request, checkCode)) {
                responseData = ResponseUtils.fail("登陆失败！请输入正确的验证码");
            } else {
                List<Seller> userList = sellerService.findUser(userName);
                if (userList.isEmpty()) {
                    responseData = ResponseUtils.fail("登陆失败！不存在该用户名称");
                } else {
                    Seller sysUser = userList.get(0);
                    if (sysUser.getPassword().equals(password.trim())) {
                        responseData = ResponseUtils.success("登陆成功！");
                        UserInfo userInfo = new UserInfo();
                        BeanUtils.copyProperties(userInfo, sysUser);
                        SessionControl.setUserInfo(request, userInfo);
                        responseData = ResponseUtils.success("登陆成功！");
                    } else {
                        responseData = ResponseUtils.fail("登陆失败！密码不正确");
                    }

                }
            }

        } catch (Exception e) {
            logger.error(e, e);
            responseData = ResponseUtils.fail("系統錯誤！" + e.getMessage());
        }
        this.outResult(responseData);
    }

    /**
     * 用户退出
     * 
     * @throws ActionException
     */
    public void userLogOut() throws ActionException {
        try {
            SessionControl.removeUserInfo(request);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (Exception e) {
            logger.error(e, e);
        }
    }
}
