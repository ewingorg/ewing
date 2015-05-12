package com.core.app.action;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.core.app.action.base.ActionException;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.jasperreports.JasperFacttory;
import com.core.jdbc.BaseDao;

public class JasperReportAction extends BaseAction {
	private Log log = LogFactory.getLog(JasperReportAction.class);

	private static final String REPORT_ID_LABEL = "reportID";

	private static final String REPORT_COMMON_JASPER_FITTER = ".jasper";

	private static final String REPORT_COMMON_PATH = "/jasperreports/";

	private static final String REPORT_COMMON_QUERY_PATH = "/jasperreports/query/";

	private static final String REPORT_COMMON_QUERY_FITTER = ".jsp";

	// private static final String REPORT_FORWARD_VIEW_LABEL = "view";

	private static final String REPORT_FORWARD_EXPORT_LABEL = "excel";
	@Resource
	public BaseDao baseDao;

	public JasperPrint query(JasperReport jasperReport,
			Map<String, Object> parameters) throws Exception {
		Session session = null;
		Connection conn = null;
		try {
			session = baseDao.getConnectionSession();
			conn = session.connection();
			return JasperFillManager.fillReport(jasperReport, parameters, conn);
		} catch (Exception ex) {
			throw ex;
		} finally {
			baseDao.releaseConnectionSession(session);
		}
	}

	public void doQuery() throws ActionException {
		ResponseData responseData = null;
		try {
			StringBuffer reportBuf = new StringBuffer();
			int pageIndex = 1;
			int lastPageIndex = 1;
			JasperPrint jasperPrint = null;
			JRHtmlExporter exporter = new JRHtmlExporter();
			// String reportId = request.getParameter(REPORT_ID_LABEL);
			//
			// if (reportId == null || "".equals(reportId)) {
			// throw new ActionException("reportId should not be null!");
			//
			// }
			String pageStr = request.getParameter("page");
			// Generate by template.
			/*
			 * if (pageStr == null) { String reportPath =REPORT_COMMON_PATH +
			 * reportId + REPORT_COMMON_JASPER_FITTER; InputStream in =
			 * JasperReportAction.class .getResourceAsStream(reportPath); if (in
			 * == null) { throw new
			 * ActionException("reportId should not be null!"); } JasperReport
			 * jasperReport = (JasperReport) JRLoader .loadObject(in);
			 * Map<String, Object> parameters = this.getReportParams(request);
			 * System.out.print(parameters.get("permark")); jasperPrint =
			 * query(jasperReport, parameters);
			 * request.getSession().setAttribute(
			 * ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
			 * jasperPrint);
			 * 
			 * } else { pageIndex = Integer.parseInt(pageStr); jasperPrint =
			 * (JasperPrint) request.getSession().getAttribute(
			 * ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
			 * 
			 * if (jasperPrint == null) {
			 * 
			 * } }
			 */
			// dynamic report.
			String sql = request.getParameter("sql");
			String title = getUTFParameter("title");
			String headersStr = getUTFParameter("headers");
			String aliasStr = getUTFParameter("alias");
			String[] headers = headersStr.split(",");
			String[] alias = aliasStr.split(",");
			JasperFacttory jFactory = new JasperFacttory();
			jasperPrint = jFactory.dynamicGenerate(sql, title, headers, alias,
					request.getParameterMap());
			lastPageIndex = jasperPrint.getPages().size();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
					reportBuf);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
					request.getContextPath()
							+ "/jasperreports/commons/image?image=");
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(
					pageIndex - 1));
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
					"");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			long imageKey = System.currentTimeMillis();
			request.getSession()
					.setAttribute(
							imageKey
									+ ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
							jasperPrint);
			exporter.exportReport();
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("reportContent", reportBuf.toString());
			hashMap.put("pageIndex", String.valueOf(pageIndex));
			hashMap.put("lastPageIndex", String.valueOf(lastPageIndex));
			hashMap.put("imageKey", String.valueOf(imageKey));
			// hashMap.put("reportID", reportId);
			responseData = ResponseUtils.success("查询成功！");
			responseData.setResult(hashMap);
		} catch (Exception e) {
			log.error(e, e);
			responseData = ResponseUtils.fail("查询失败！");
		}
		this.outResult(responseData);
	}

	/**
	 * 功能： 获取报表参数
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Object> getReportParams(HttpServletRequest request)
			throws Exception {
		Map<String, Object> reportParams = new HashMap<String, Object>(15);

		Set keys = request.getParameterMap().keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = String.valueOf(it.next());
			if (REPORT_ID_LABEL.equalsIgnoreCase(key)) {
				continue;
			} else {
				// reportParams.put(key, request.getParameter(key));
				reportParams.put(key, java.net.URLDecoder.decode(
						request.getParameter(key), "UTF-8"));
				// reportParams.put(key, new String(request.getParameter(key)
				// .getBytes("ISO-8859-1"), "GBK"));
			}
		}
		return reportParams;
	}

}
