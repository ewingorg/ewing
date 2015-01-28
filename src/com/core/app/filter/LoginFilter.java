package com.core.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.core.app.bean.UserInfo;
import com.core.app.control.SessionControl;
import com.core.app.control.SessionException;
import com.core.app.service.SysLogTraceService;
import com.core.factory.SpringCtx;
import com.core.tool.trace.SysLogTraceThread;
import com.core.tool.trace.SysTrace;

public class LoginFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoginFilter.class);
	private FilterConfig filterConfig = null;
	private String redirectURL = null;

	private SysLogTraceService sysLogTraceService = (SysLogTraceService) SpringCtx
			.getByBeanName("sysLogTraceService");;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResposne, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResposne;
		String contextPath = request.getContextPath();
		String loginPage = contextPath + "/" + redirectURL;
		String reqUrl = request.getRequestURL().toString();

		if (reqUrl.contains(redirectURL) || reqUrl.endsWith(redirectURL)) {
		} else {
		/*	if (reqUrl.endsWith(".jsp") || reqUrl.endsWith(".action")) {
				UserInfo userInfo = null;
				try {
					userInfo = SessionControl.getUserInfo(request);
					if (reqUrl.indexOf(contextPath) > -1) {
						String menuUrl = reqUrl.substring(reqUrl
								.indexOf(contextPath)
								+ contextPath.length());
						SysLogTraceThread.getInstance().addToQueue(
								new SysTrace(userInfo.getId(), menuUrl));
					}
				} catch (SessionException e) {
					logger.error("fail to get user session!", e);
				}
				if (userInfo == null) {
					response.sendRedirect(loginPage + "?outsession=0");
					return;
				}
			}*/
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
	}

}
