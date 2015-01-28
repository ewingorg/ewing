/**
 * 
 */
package com.core.log;

import java.io.FileNotFoundException;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.util.ResourceUtils;

/**
 * @author Administrator
 * 
 */
public class LogHelper {
	private static Logger logger = Logger.getLogger(LogHelper.class);

	public static void init() {
		long begin = System.currentTimeMillis();
		try {
			DOMConfigurator.configure(ResourceUtils
					.getURL("classpath:config/properties/log4j.xml"));
			long logEnd = System.currentTimeMillis();
			logger.info("[初始化]日志目录设置成功,耗时[" + (logEnd - begin) + "]毫秒.");
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (FactoryConfigurationError e) {
			logger.error(e, e);
		}
	}

	public static Logger getLog(Class clazz) {
		return Logger.getLogger(clazz);
	}

	public static void main(String[] args) {
		logger.info("111");
	}

}
