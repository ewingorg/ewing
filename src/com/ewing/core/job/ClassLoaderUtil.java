package com.ewing.core.job;

/**
 * 类/接口注释
 * 
 * @author tanson lam
 * @createDate 2015年3月17日
 * 
 */
public class ClassLoaderUtil {

    public static Class loadClass(String clazzName) throws ClassNotFoundException {
        return Class.forName(clazzName);
    }

}
