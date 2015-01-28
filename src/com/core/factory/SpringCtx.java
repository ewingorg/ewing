package com.core.factory;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCtx {
	private static Logger logger = Logger.getLogger(SpringCtx.class);
	private static ClassPathXmlApplicationContext ctx;
	static {
		try {
			ctx = new ClassPathXmlApplicationContext(
					"config/spring/applicationContext.xml");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Spring ctx
	 * 
	 * @return Spring Context
	 */
	public static ClassPathXmlApplicationContext getCtx() {

		return ctx;
	}

	public static Object getClassBean(Class entityClass) {
		ApplicationContext ctx = SpringCtx.getCtx();
		String beanName = entityClass.getSimpleName();
		if (!StringUtils.isEmpty(beanName) && beanName.length() > 0) {
			String firstChar = beanName.substring(0,1).toLowerCase();
			String followChar = beanName.length() > 1 ? beanName.substring(1,
					beanName.length() ) : "";
			beanName = firstChar + followChar;
		}
		return ctx.getBean(beanName);
	}
	
	public static Object getByBeanName(String beanName) { 
		return ctx.getBean(beanName);
	}
}
