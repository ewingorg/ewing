package com.ewing.core.jasperreports;

import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrintServlet extends HttpServlet {

	private Log log = LogFactory.getLog(PrintServlet.class);

	private static final long serialVersionUID = 667038301234472475L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageKey=request.getParameter("imageKey");
		JasperPrint jasperPrint = (JasperPrint) request.getSession()
				.getAttribute(
						imageKey+ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);

		if (jasperPrint == null) {
			throw new ServletException("could not find jasperPrint ");
		}

		response.setCharacterEncoding("gb2312");

		response.setContentType("application/octet-stream");

		ServletOutputStream ouputStream = response.getOutputStream();

		ObjectOutputStream oos = new ObjectOutputStream(ouputStream);

		try {
			oos.writeObject(jasperPrint);

			oos.flush();
			oos.close();
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			log.error("", e);
			throw new ServletException( e.getMessage());
		}
	}
}
