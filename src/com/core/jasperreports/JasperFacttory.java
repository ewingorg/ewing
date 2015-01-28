package com.core.jasperreports;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseLine;
import net.sf.jasperreports.engine.base.JRBasePrintText;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.core.factory.SpringCtx;
import com.core.jdbc.BaseDao;

/**
 * @author Administrator
 *
 */
public class JasperFacttory {
	
	private String getValueFromParam(Map paramMap, String specifidField) {
		Iterator itor = paramMap.keySet().iterator();
		while (itor.hasNext()) {
			String key = itor.next().toString();
			if (key.equals(specifidField)) {
				Object[] sValue = (Object[]) paramMap.get(key);
				String value = sValue[0].toString();
				return value;
			}
		}
		return null;
	}

	public JasperPrint dynamicGenerate(String sql, String title,
			String[] headers, String[] alias, Map paramMap) throws Exception {

		Connection connection = getConnection();

		JasperDesign jasperDesign = new JasperDesign();
		JRDesignStyle normalStyle = setReportSytle(jasperDesign);
		JRDesignQuery query = new JRDesignQuery();
		query.setText(sql);
		jasperDesign.setQuery(query);
		// addReportFiled(jasperDesign);

		// Title
		JRDesignBand band = new JRDesignBand();
		band.setHeight(100); 
		JRDesignStaticText staticText = new JRDesignStaticText();

		staticText.setX(280);
		staticText.setY(0);
		staticText.setWidth(300);
		staticText.setHeight(40);
		staticText.setBold(true);
		staticText.setFontSize(24);
		staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER); // 设置文本的对齐方式
		staticText.setStyle(normalStyle);
		staticText.setText(title);
		JRDesignStaticText staticText2 = new JRDesignStaticText();

		staticText2.setX(0);
		staticText2.setY(50);
		staticText2.setWidth(350);
		staticText2.setHeight(30);
		staticText2.setFontSize(15);
		//staticText2.setItalic(true);
		staticText2.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_LEFT); // 设置文本的对齐方式
		staticText2.setStyle(normalStyle);
		if (getValueFromParam(paramMap, JasperParam.STATISTIC_TIME_FIELD) != null) {
			staticText2.setText("统计时间： "
					+ getValueFromParam(paramMap,
							JasperParam.STATISTIC_TIME_FIELD));
		}
		band.addElement(staticText);
	 	band.addElement(staticText2);
		/*
		 * JRDesignLine line = new JRDesignLine(); line.setX(0); line.setY(19);
		 * line.setWidth(500); line.setHeight(1);
		 * line.setForecolor(Color.BLACK); band.addElement(line);
		 */
		jasperDesign.setTitle(band);

		// Page header
		band = new JRDesignBand();
		// band.setHeight(20);
		band.setHeight(0);
		jasperDesign.setPageHeader(band);
		jasperDesign.setPageWidth(800);
		// Column header
		band = new JRDesignBand();
		band.setHeight(30);

		// 开始添加列字段
		int X = 80;
		int columnHeaderfontSize = 15;
		int fontSize = 12;
		int textHeight = 30;
		int textWidth = 80;
		int detailHeight = 30;
		JRDesignBand detail = new JRDesignBand();
		detail.setHeight(detailHeight);
		for (int i = 0; i < headers.length; i++) {
			JRDesignStaticText jrstaticText = new JRDesignStaticText();
			jrstaticText.setText(headers[i]);
			jrstaticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
			jrstaticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
			jrstaticText.setFontSize(columnHeaderfontSize); 
			jrstaticText.setHeight(textHeight);
			jrstaticText.setWidth(textWidth); 
			jrstaticText.setBorderColor(Color.BLACK);
			jrstaticText.setBold(true);
			if (i == 0) {
				X = 0;
			} else {
				X = 80;
			}
			jrstaticText.setX(X * i);
			jrstaticText.setPdfFontName("STSong-Light");
			jrstaticText.setPdfEmbedded(true);
			jrstaticText.setPdfEncoding("UniGB-UCS2-H");
			jrstaticText
					.setTextAlignment(JRBasePrintText.HORIZONTAL_ALIGN_CENTER);
			jrstaticText.setLeftBorder(JRBaseLine.PEN_1_POINT);
			jrstaticText.setTopBorder(JRBaseLine.PEN_1_POINT);
			jrstaticText.setRightBorder(JRBaseLine.PEN_1_POINT);
			jrstaticText.setBottomBorder(JRBaseLine.PEN_1_POINT);
			jrstaticText.setForecolor(Color.black);
			// jrstaticText.setStretchType((byte)0);
			band.addElement(jrstaticText);

			// 定义字段，注册字段
			JRDesignField field = new JRDesignField();
			field.setName(alias[i]);
			field.setValueClass(String.class);
			jasperDesign.addField(field);

			// add text fields for displaying fields
			JRDesignTextField textField = new JRDesignTextField();
			JRDesignExpression expression = new JRDesignExpression();
			expression.setText("$F{" + alias[i] + "}");
			expression.setValueClass(String.class);
			textField.setExpression(expression);
			textField.setFontSize(fontSize);
			textField.setHeight(textHeight);
			textField.setWidth(textWidth);
			textField.setX(X * i);
			textField.setPdfFontName("STSong-Light");
			textField.setPdfEmbedded(true);
			textField.setPdfEncoding("UniGB-UCS2-H");
			textField.setTextAlignment(JRBasePrintText.HORIZONTAL_ALIGN_CENTER);
			textField.setLeftBorder(JRBaseLine.PEN_1_POINT);
			textField.setTopBorder(JRBaseLine.PEN_1_POINT);
			textField.setRightBorder(JRBaseLine.PEN_1_POINT);
			textField.setBottomBorder(JRBaseLine.PEN_1_POINT);
			textField.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
			textField.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_BOTTOM);
			textField.setBorderColor(Color.BLACK);
			// textField.setForecolor(new Color(0x99,0xFF,0xFF)); 
			textField.setStretchWithOverflow(true);
			detail.addElement(textField);
		}

		jasperDesign.setColumnHeader(band);

		// detail
		jasperDesign.setDetail(detail);

		// Column footer
		band = new JRDesignBand();
		band.setHeight(10);
		jasperDesign.setColumnFooter(band);
		// Page footer
		band = new JRDesignBand();
		band.setHeight(80);

		staticText = new JRDesignStaticText();
		staticText.setX(0);
		staticText.setY(0);
		staticText.setWidth(300);
		staticText.setHeight(30);
		staticText.setPdfFontName("STSong-Light");
		staticText.setPdfEmbedded(true);
		staticText.setPdfEncoding("UniGB-UCS2-H");
		//staticText.setItalic(true);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String tt = "制表日期：" + sdf.format(new Date());
		System.out.println(tt);
		staticText.setText(tt);
		//band.addElement(staticText);

		JRDesignTextField textField = new JRDesignTextField();
		textField.setX(450);
		textField.setY(0);
		textField.setWidth(100);
		textField.setHeight(30);
		textField.setPdfFontName("STSong-Light");
		textField.setPdfEmbedded(true);
		textField.setPdfEncoding("UniGB-UCS2-H");

		JRDesignExpression expression = new JRDesignExpression();
		expression = new JRDesignExpression();
		expression.setValueClass(java.lang.Integer.class);
		expression.setText("$V{PAGE_NUMBER}");

		textField.setExpression(expression);
		// band.addElement(textField);
		jasperDesign.setPageFooter(band);

		System.out.println(expression.getText());
		// Summary
		band = new JRDesignBand();
		band.setHeight(0);

		/* **************************************************************** */
		/* Here My doubt */
		/*
		 * JRDesignChart chart1 = new
		 * JRDesignChart(null,JRChart.CHART_TYPE_LINE); chart1.setHeight(100);
		 * chart1.setBorderColor(Color.red); chart1.setForecolor(Color.BLACK);
		 * chart1.setWidth(200);
		 */

		// band.addElement(chart1);
		jasperDesign.setSummary(band);

		System.out.println("After Connection");

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		Map parameters = new HashMap();
		System.out.println("After Parameter");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, connection);
		System.out.println("After Print");
		JasperExportManager.exportReportToHtmlFile(jasperPrint,
				"D://test2.html");

		System.out.println("After Export");
		return jasperPrint;

	}

	private JRDesignStyle setReportSytle(JasperDesign jasperDesign)
			throws JRException {
		jasperDesign.setName("SampleReport");
		jasperDesign.setPageWidth(595);
		jasperDesign.setPageHeight(842);
		jasperDesign.setColumnWidth(535);
		jasperDesign.setColumnSpacing(0);

		jasperDesign.setLeftMargin(30);
		jasperDesign.setRightMargin(30);
		jasperDesign.setTopMargin(20);
		jasperDesign.setBottomMargin(20);
		// whenNoDataType="NoPages"
		jasperDesign.setWhenNoDataType(JRReport.WHEN_NO_DATA_TYPE_BLANK_PAGE);
		// isTitleNewPage="false"
		jasperDesign.setTitleNewPage(false);
		// isSummaryNewPage="false"
		jasperDesign.setSummaryNewPage(false);
		// jasperDesign.setOrientation(JRReport.ORIENTATION_PORTRAIT);
		// jasperDesign.setPrintOrder(JRReport.PRINT_ORDER_VERTICAL);

		JRDesignStyle normalStyle = new JRDesignStyle();
		normalStyle.setName("Arial_Normal");
		normalStyle.setDefault(true);
		normalStyle.setFontName("Arial");
		normalStyle.setFontSize(15);
		normalStyle.setPdfFontName("STSong-Light");
		normalStyle.setPdfEncoding("UniGB-UCS2-H");
		normalStyle.setPdfEmbedded(true);
		jasperDesign.addStyle(normalStyle);

		JRDesignStyle boldStyle = new JRDesignStyle();
		boldStyle.setName("Arial_Bold");
		boldStyle.setFontName("Arial");
		boldStyle.setFontSize(12);
		boldStyle.setBold(true);
		boldStyle.setPdfFontName("STSong-Light");
		boldStyle.setPdfEncoding("UniGB-UCS2-H");
		boldStyle.setPdfEmbedded(true);
		jasperDesign.addStyle(boldStyle);

		JRDesignStyle italicStyle = new JRDesignStyle();
		italicStyle.setName("Arial_Italic");
		italicStyle.setFontName("Arial");
		italicStyle.setFontSize(12);
		italicStyle.setItalic(true);
		italicStyle.setPdfFontName("STSong-Light");
		italicStyle.setPdfEncoding("UniGB-UCS2-H");
		italicStyle.setPdfEmbedded(true);
		jasperDesign.addStyle(italicStyle);
		return normalStyle;
	}

	private Connection getConnection() throws SQLException {
		BaseDao baseDao = (BaseDao) SpringCtx.getByBeanName("baseDao");
		Connection connection = baseDao.getConnection();
		return connection;
	}

	public static void main(String args[]) throws Exception {
		JasperFacttory obj = new JasperFacttory();
		Map map = new HashMap();
		map.put(JasperParam.STATISTIC_TIME_FIELD, "2012-10-10 到 2012-11-01");
		obj.dynamicGenerate("select date_format(create_time,'%Y-%m-%d') as create_date,count(*) as total from order_info where 1=1 group by date_format(create_time,'%Y-%m-%d')", "签单日统计报表",
				new String[] { "签单日期","签单总数" }, new String[] { "create_date", "total" },map);
	}

}