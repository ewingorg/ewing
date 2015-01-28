package com.core.template;

import java.io.IOException;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.core.factory.SpringCtx;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTool {
	private Configuration configuration;
	private final static FreeMarkerTool freeMarkerTool = new FreeMarkerTool();

	public static FreeMarkerTool getSingleton() {
		return freeMarkerTool;
	}

	private FreeMarkerTool() {
		FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) SpringCtx
				.getByBeanName("freeMarkerConfigurer");
		configuration = freeMarkerConfigurer.getConfiguration();
		configuration.setClassicCompatible(true); 
	}

	public String getTemplateResult(String template, Map map)
			throws IOException, TemplateException {
		Template t = configuration.getTemplate(template);
		String result = FreeMarkerTemplateUtils.processTemplateIntoString(t,
				map);
		return result;
	}

}
