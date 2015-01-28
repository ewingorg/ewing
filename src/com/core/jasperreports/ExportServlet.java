package com.core.jasperreports;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gven king
 * @date 2007/3/16
 */

public class ExportServlet extends HttpServlet {

	private Log log = LogFactory.getLog(ExportServlet.class);

	private static final long serialVersionUID = 1626331706517382929L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		String imageKey = request.getParameter("imageKey");
		JasperPrint jasperPrint = (JasperPrint) request
				.getSession()
				.getAttribute(
						imageKey
								+ ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
		SecureRandom sr = new SecureRandom();
		if (jasperPrint == null) {
			ServletOutputStream os = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment;filename="
					+ "report" + sr.nextInt(100) + ".xls");
			response.setCharacterEncoding("GBK");
			response.setContentType("application/x-msdownload");
			os.flush();
			os.close();
		} else {

			request.getSession().setAttribute(
					ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, null);
			response.setHeader("Content-Disposition", "attachment;filename="
					+ "report" + sr.nextInt(100) + ".xls");
			// response.setHeader("Content-Disposition", "filename=" +
			// "report.xls");

			response.setCharacterEncoding("GBK");

			// response.setContentType("application/vnd.ms-excel");

			response.setContentType("application/x-msdownload");

			ServletOutputStream os = response.getOutputStream();

			JRXlsExporter xlsExporter = new JRXlsExporter();

			xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);

			xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);

			xlsExporter
					.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.FALSE);

			xlsExporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.FALSE);

			try {
				xlsExporter.exportReport();

				os.flush();
				os.close();
			} catch (Exception e) {
				log.error("", e);
				throw new ServletException("������ݳ���:" + e.getMessage());
			}
		}
	}

}
