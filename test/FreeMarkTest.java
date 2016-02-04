import java.util.HashMap;

import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ewing.core.factory.SpringCtx;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkTest {
	@Test
	public void getName() throws Exception {
		try {
			FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) SpringCtx
					.getByBeanName("freeMarkerConfigurer");
			Configuration configuration = freeMarkerConfigurer
					.getConfiguration();
			Template t = configuration.getTemplate("test.ftl");
			HashMap<String, Object> parammap = new HashMap<String, Object>();
			HashMap<String, String> usermap = new HashMap<String, String>();
			usermap.put("name", "tanson");
			usermap.put("password", "11111");
			parammap.put("user", usermap);
			parammap.put("contextPath", "wwww");
			String result = FreeMarkerTemplateUtils.processTemplateIntoString(
					t, parammap);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
