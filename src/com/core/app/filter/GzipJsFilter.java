package com.core.app.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipJsFilter implements Filter {
	Map<String, String> headers = new HashMap<String, String>();

	public void destroy() {
	}

	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		if (paramServletRequest instanceof HttpServletRequest)
			doFilter((HttpServletRequest) paramServletRequest,
					(HttpServletResponse) paramServletResponse,
					paramFilterChain);
		else
			paramFilterChain
					.doFilter(paramServletRequest, paramServletResponse);
	}

	public void doFilter(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		paramHttpServletRequest.setCharacterEncoding("UTF-8");
		Iterator localIterator = this.headers.entrySet().iterator();
		while (localIterator.hasNext()) {
			Map.Entry localEntry = (Map.Entry) localIterator.next();
			paramHttpServletResponse.addHeader((String) localEntry.getKey(),
					(String) localEntry.getValue());
		}
		paramFilterChain.doFilter(paramHttpServletRequest,
				paramHttpServletResponse);
	}

	public void init(FilterConfig paramFilterConfig) throws ServletException {
		String str = paramFilterConfig.getInitParameter("headers");
		String[] arrayOfString1 = str.split(",");
		for (int i = 0; i < arrayOfString1.length; ++i) {
			String[] arrayOfString2 = arrayOfString1[i].split("=");
			this.headers
					.put(arrayOfString2[0].trim(), arrayOfString2[1].trim());
		}
	}
}
