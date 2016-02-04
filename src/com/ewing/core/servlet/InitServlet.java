package com.ewing.core.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ewing.core.factory.SysParamFactory;

public class InitServlet extends HttpServlet {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InitServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		try {
			SysParamFactory.WEB_CONTEXT_PATH = super.getServletContext()
					.getContextPath();
			SysParamFactory.WEB_REAL_PATH = super.getServletContext()
					.getRealPath("/"); 
			logger.info("init successfully");
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

}