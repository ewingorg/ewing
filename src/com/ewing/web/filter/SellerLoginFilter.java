package com.ewing.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.bean.UserInfo;
import com.ewing.core.app.control.SessionControl;
import com.ewing.core.app.control.SessionException;

public class SellerLoginFilter implements Filter {
    private static Logger logger = Logger.getLogger(SellerLoginFilter.class);
    private FilterConfig filterConfig = null;
    private String toLogin = null;
    private List<String> ignoreUrl;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResposne,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResposne;
        String contextPath = request.getContextPath();
        String loginPage = contextPath + "/" + toLogin;
        String reqUrl = request.getRequestURL().toString(); 
        if (!isIgnoreUrl(reqUrl)) {
            UserInfo userInfo = null;
            try {
                userInfo = SessionControl.getUserInfo(request);
            } catch (SessionException e) {
                // logger.error("fail to get user session!", e);
            }
            if (userInfo == null) { 
                    response.sendRedirect(loginPage + "?outsession=0");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isIgnoreUrl(String reqUrl) {
        for (String url : ignoreUrl) {
            if (reqUrl.contains(url) || reqUrl.endsWith(url)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        ignoreUrl = new ArrayList<String>();
        toLogin = filterConfig.getInitParameter("toLogin");
        Enumeration<String> paramNames = filterConfig.getInitParameterNames();
        String paramName;
        while (paramNames.hasMoreElements()) {
            paramName = paramNames.nextElement();
            ignoreUrl.add(filterConfig.getInitParameter(paramName));
        }

    }
}
