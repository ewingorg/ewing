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

import nl.justobjects.pushlet.util.Sys;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

public class UrlContainerFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoginFilter.class);
	private FilterConfig filterConfig = null;
	private String redirectURL = null;

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
		String reqUrl = request.getRequestURL().toString();
		String menuUrl = reqUrl.substring(reqUrl.indexOf(contextPath)
				+ contextPath.length());
		String container = request.getParameter("container");
		/*
		 * if (menuUrl.endsWith(".jsp")) { String actionUrl =
		 * "/Public_JspForward_forward.action?jumpPage=" + menuUrl;
		 * System.out.println(actionUrl);
		 * request.getRequestDispatcher(actionUrl).forward(request, response); }
		 * else
		 */if (!StringUtils.isEmpty(container)) {
			request.setAttribute("includejsp", menuUrl);
			String actionUrl = "/" + container;
			request.getRequestDispatcher(actionUrl).forward(request, response);

		} 
		filterChain.doFilter(request, response);
	}

	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
	}

}
