import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ewing.core.factory.SpringCtx;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class JdbcTest {

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		String url = "jdbc:mysql://localhost:3306/vinsterest?user=root&password=root&characterEncoding=UTF8";
		Connection con = DriverManager.getConnection(url);

	}

	 

}
