package com.ewing.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * 属性工具类
 * 
 * @author tansonlam
 * @createDate 2016年2月2日
 * 
 */
public class PropertyUtil {
    private static Logger logger = Logger.getLogger(PropertyUtil.class);
    private static Properties props = new Properties();
    static {
        try {
            String urlPath = PropertyUtil.class.getResource("/") + File.separator + "config"
                    + File.separator + "properties" + File.separator + "system.properties";
            java.net.URL url = new URL(urlPath);
            InputStream in = new BufferedInputStream(new FileInputStream(url.getPath()));
            props.load(in);
            in.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {

        try {
            String value = props.getProperty(key);
            if (StringUtils.isEmpty(value))
                return null;
            return new String(value.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            logger.error("fail to get property for " + key, e);
            return null;
        }
    }

    public static Enumeration<?> getAllProperties() {
        return props.propertyNames();
    }
}