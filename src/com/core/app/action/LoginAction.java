package com.core.app.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.admin.model.SysUser;
import com.core.app.action.base.ActionException;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.app.bean.UserInfo;
import com.core.app.control.SessionControl;
import com.core.app.service.SysRightRelService;
import com.core.app.service.SysUserService;
import com.core.servlet.CaptchaServlet;

/**
 * 系统登陆
 * 
 */
public class LoginAction extends BaseAction {
	private static Logger logger = Logger.getLogger(LoginAction.class);
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRightRelService sysRightRelService;

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
				List<SysUser> userList = sysUserService.findUser(userName);
				if (userList.isEmpty()) {
					responseData = ResponseUtils.fail("登陆失败！不存在该用户名称");
				} else {
					SysUser sysUser = userList.get(0);
					if (sysUser.getPassword().equals(password.trim())) {
						responseData = ResponseUtils.success("登陆成功！");
						UserInfo userInfo = new UserInfo();
						BeanUtils.copyProperties(userInfo, sysUser);
						SessionControl.setUserInfo(request, userInfo);
						sysRightRelService.getUserAllRelMenu(userInfo);
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
