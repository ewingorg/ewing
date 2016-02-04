package com.ewing.core.jasperreports;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

public class ViewServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -307958264851867315L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JasperPrint jasperPrint = (JasperPrint) request.getSession()
				.getAttribute(
						ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);

		if (jasperPrint == null) {
			/*
			 * try { jasperPrint = (JasperPrint) JRLoader.loadObject(this
			 * .getServletContext().getRealPath( "/jasperreports/reports/" +
			 * request.getParameter("ReportID") + "Report.jrprint"));
			 * 
			 * } catch (Exception e) { throw new
			 * ServletException("���Ƚ��б����ѯ!"); }
			 */
			throw new ServletException("���Ƚ��б����ѯ!");
		}

		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();

		StringBuffer buffer = new StringBuffer(5000); // ���汨��չʾ����

		buffer
				.append("<html>")
				.append("<head>")
				.append("<title>")
				.append("�����ѯ���")
				.append("</title>")
				.append("<link href=\"")
				.append(request.getContextPath())
				.append(
						"/css/css_1/interfacebase.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />")
				.append(request.getContextPath())
				.append(
						"/css/css_1/navigate.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />")
				.append(request.getContextPath())
				.append(
						"/css/css_1/common.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />")
				.append(request.getContextPath())
				.append(
						"/css/css_1/form.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />")
				.append(request.getContextPath())
				.append(
						"/css/css_1/button.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />")
				.append("<meta name=\"author\" content=\"www.maywide.com\" />")
				.append("<meta name=\"robots\" content=\"none\" />")
				.append("</head>")
				.append(
						"<body text=\"#000000\" link=\"#000000\" alink=\"#000000\" vlink=\"#000000\">")
				.append("<div  class=\"table_div_edit\">")
				.append(
						"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">")
				.append("<tr>")
				.append("<td width=\"50%\">&nbsp;</td>")
				.append("<td align=\"left\">")
				.append("<hr size=\"1\" color=\"#000000\">")
				.append("<div class=\"table_div\">")
				.append(
						"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">")
				.append("<tr>")
				.append("<td>")
				.append(
						"<a href=\""
								+ response
										.encodeURL(request.getContextPath()
												+ "/jasperreports/commons/print?ReportID="
												+ request
														.getParameter("ReportID"))
								+ "\">")
				.append(
						"<img src=\""
								+ request.getContextPath()
								+ "/images/jasperview/print.jpg\" border=\"0\"></a></td>")
				.append("<td>&nbsp;</td>")
				.append(
						"<a href=\""
								+ response
										.encodeURL(request.getContextPath()
												+ "/jasperreports/commons/export?ReportID="
												+ request
														.getParameter("ReportID"))
								+ "\">")
				.append(
						"<img src=\""
								+ request.getContextPath()
								+ "/images/jasperview/export.jpg\" border=\"0\"></a></td>")
				.append("<td>&nbsp;&nbsp;</td>");

		StringBuffer reportBuf = new StringBuffer();
		JRHtmlExporter exporter = new JRHtmlExporter();
		int pageIndex = 0;
		int lastPageIndex = jasperPrint.getPages().size() - 1;

		String pageStr = request.getParameter("page");
		if (pageStr == null) {
			pageIndex = Integer.parseInt(pageStr);
		}

		if (pageIndex > lastPageIndex) {
			pageIndex = lastPageIndex;
		}

		if (pageIndex > 0) {
			buffer.append("");
		}

		try {
			exporter
					.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
					reportBuf);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request
					.getContextPath()
					+ "/jasperreports/commons/image?image=");
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(
					pageIndex));
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
					"");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");

			exporter.exportReport();
		} catch (Exception e) {
			buffer.append(e.toString());
		} finally {
			buffer.append("</body>").append("</html>");
			out.write(buffer.toString());
			out.flush();
			out.close();
		}
	}

}
