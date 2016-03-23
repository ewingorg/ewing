package com.ewing.core.jdbc.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;

import com.ewing.core.jdbc.annotation.Column;
import com.ewing.core.jdbc.annotation.Table;

public class GenericsUtil {
    private static final Logger log = Logger.getLogger(GenericsUtil.class);

	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	public static Table getTable(Class clazz) throws Exception {
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (flag) {
			return (Table) clazz.getAnnotation(Table.class);
		}
		throw new Exception("can not find Table Annotation in you class:"
				+ clazz.getPackage() + "." + clazz.getName());
	}

	public static String getSaveSql(Object o) throws Exception {
		PropertyDescriptor[] ps = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();
		String sql = "INSERT INTO " + getTable(o.getClass()).name();
		String fields = " ";
		String values = " ";
		for (PropertyDescriptor p : ps) {
			Method method = p.getReadMethod();
			if (p.getName().equals("des")) {
				System.out.println();
			}
			if ((method == null) || (!method.isAnnotationPresent(Column.class))
					|| (method.invoke(o, new Object[0]) == null))
				continue;
			Column f = (Column) method.getAnnotation(Column.class);
			if (f.autoIncrement())
				continue;
			String type = method.getReturnType().getName();
			fields = fields + "," + f.fieldName();
			if (type.equals("java.lang.String")) {
				values = values + ",'" + method.invoke(o, new Object[0]) + "'";
			} else if (type.equals("java.util.Date")) {
				String ds = DateUtils.format((Date) method.invoke(o,
						new Object[0]), "yyyy-MM-dd HH:mm:ss");
				values = values + ",date_format('" + ds + "', '%Y-%m-%d %T')";
			} else {
				values = values + "," + method.invoke(o, new Object[0]);
			}

		}

		fields = fields.replaceFirst(",", "");
		values = values.replaceFirst(",", "");
		sql = sql + " ( " + fields + " ) values (" + values + ")";
		return sql;
	}

	public static String getUpdateSql(Object o) throws Exception {

		PropertyDescriptor[] ps = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();
		String sql = "UPDATE  " + getTable(o.getClass()).name() + " set ";
		String values = " ";
		String where = " where  1=1 ";
		for (PropertyDescriptor p : ps) {
			Method method = p.getReadMethod();

			if ((method == null) || (!method.isAnnotationPresent(Column.class))
					|| (method.invoke(o, new Object[0]) == null))
				continue;
			Column f = (Column) method.getAnnotation(Column.class);
			String type = method.getReturnType().getName();

			if (type.equals("java.lang.String")) {
				values = values + "," + f.fieldName() + "='"
						+ method.invoke(o, new Object[0]) + "'";
			} else if (type.equals("java.util.Date")) {
				String ds = DateUtils.format((Date) method.invoke(o,
						new Object[0]), "yyyy-MM-dd HH:mm:ss");
				values = values + "," + f.fieldName() + "=date_format('" + ds
						+ "', '%Y-%m-%d %T')";
			} else {
				values = values + "," + f.fieldName() + "="
						+ method.invoke(o, new Object[0]);
			}

		}

		where = getKeySql(o);
		values = values.replaceFirst(",", "");
		sql = sql + values + where;
		return sql;
	}

	public static String getUpdateAllSql(Object o) throws Exception {
		PropertyDescriptor[] ps = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();
		String sql = "UPDATE  " + getTable(o.getClass()).name() + " set ";
		String values = " ";
		String where = " where id= ";
		for (PropertyDescriptor p : ps) {
			Method method = p.getReadMethod();
			if ("getId".equals(method.getName())) {
				where = where + method.invoke(o, new Object[0]);
			} else if ((method != null)
					&& (method.isAnnotationPresent(Column.class))) {
				Column f = (Column) method.getAnnotation(Column.class);
				String type = method.getReturnType().getName();
				if (type.equals("java.lang.String")) {
					if (method.invoke(o, new Object[0]) != null)
						values = values + "," + f.fieldName() + "='"
								+ method.invoke(o, new Object[0]) + "'";
					else
						values = values + "," + f.fieldName() + "=null";
				} else if (type.equals("java.util.Date")) {
					if (method.invoke(o, new Object[0]) != null) {
						String ds = DateUtils.format((Date) method.invoke(o,
								new Object[0]), "yyyy-MM-dd HH:mm:ss");
						values = values + "," + f.fieldName() + "=to_date('"
								+ ds + "', 'yyyy-mm-dd hh24:mi:ss')";
					} else {
						values = values + "," + f.fieldName() + "=null";
					}
				} else
					values = values + "," + f.fieldName() + "="
							+ method.invoke(o, new Object[0]);
			}

		}

		values = values.replaceFirst(",", "");
		sql = sql + values + where;
		return sql;
	}

	public static String getKeySql(Object o) throws Exception {

		PropertyDescriptor[] ps = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();
		String where = " where 1=1";
		for (PropertyDescriptor p : ps) {
			Method method = p.getReadMethod();

			if ((method == null) || (!method.isAnnotationPresent(Column.class))
					|| (method.invoke(o, new Object[0]) == null))
				continue;
			Column f = (Column) method.getAnnotation(Column.class);
			String type = method.getReturnType().getName();
			if (f.primaryKey()) {

				if (type.equals("java.lang.String")) {
					where = where + " and " + f.fieldName() + "='"
							+ method.invoke(o, new Object[0]) + "'";
				} else if (type.equals("java.util.Date")) {
					String ds = DateUtils.format((Date) method.invoke(o,
							new Object[0]), "yyyy-MM-dd HH:mm:ss");
					where = where + " and " + f.fieldName() + "=to_date('" + ds
							+ "', 'yyyy-mm-dd hh24:mi:ss')";
				} else {
					where = where + " and " + f.fieldName() + "="
							+ method.invoke(o, new Object[0]);
				}
			}

		}
		return where;
	}

	public static Boolean hasPrimaryKey(Object o) throws Exception {
		boolean hasPrimaryKey = false;
		PropertyDescriptor[] ps = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();

		for (PropertyDescriptor p : ps) {
			Method method = p.getReadMethod();

			if ((method == null) || (!method.isAnnotationPresent(Column.class))
					|| (method.invoke(o, new Object[0]) == null))
				continue;
			Column f = (Column) method.getAnnotation(Column.class);
			String type = method.getReturnType().getName();
			if (f.primaryKey())
				hasPrimaryKey = true;

		}
		return hasPrimaryKey;
	}

	public static String getWhereSql(Object o) throws Exception {
		PropertyDescriptor[] ps = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();
		String where = "";
		for (PropertyDescriptor p : ps) {
			Method method = p.getReadMethod();
			if ((method == null) || (!method.isAnnotationPresent(Column.class))
					|| (method.invoke(o, new Object[0]) == null))
				continue;
			Column f = (Column) method.getAnnotation(Column.class);
			String type = method.getReturnType().getName();
			if (type.equals("java.lang.String")) {
				where = where + " and " + f.fieldName() + "='"
						+ method.invoke(o, new Object[0]) + "'";
			} else if (type.equals("java.util.Date")) {
				String ds = DateUtils.format((Date) method.invoke(o,
						new Object[0]), "yyyy-MM-dd HH:mm:ss");
				where = where + " and " + f.fieldName() + "=to_date('" + ds
						+ "', 'yyyy-mm-dd hh24:mi:ss')";
			} else {
				where = where + " and " + f.fieldName() + "="
						+ method.invoke(o, new Object[0]);
			}

		}

		return where;
	}

	public static Object procResult(Map map, Class clazz) {
		Object o = null;
		PropertyDescriptor[] ps = (PropertyDescriptor[]) null;
		try {
			ps = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			o = clazz.newInstance();
			for (PropertyDescriptor p : ps) {
				Method method = p.getReadMethod();
				if ((method != null)
						&& (method.isAnnotationPresent(Column.class))) {
					String sMethod = method.getName().replaceFirst("g", "s");
					Method seter = clazz.getMethod(sMethod,
							new Class[] { method.getReturnType() });
					Column f = (Column) method.getAnnotation(Column.class);
					Object value = map.get(f.fieldName());
					String type = method.getReturnType().getName();
					if (value != null)
						if ((type.equals("java.lang.Integer"))
								|| (type.equals("int")))
							seter.invoke(o,
									new Object[] { Integer.valueOf(Integer
											.parseInt(value.toString())) });
						else if ((type.equals("java.lang.Long"))
								|| (type.equals("long")))
							seter.invoke(o, new Object[] { Long.valueOf(Long
									.parseLong(value.toString())) });
						else if ((type.equals("java.lang.Boolean"))
								|| (type.equals("boolean")))
							seter.invoke(o, new Object[] { Boolean
									.valueOf(Boolean.parseBoolean(value
											.toString())) });
						else if ((type.equals("java.lang.Short"))
								|| (type.equals("short")))
							seter.invoke(o, new Object[] { Short.valueOf(Short
									.parseShort(value.toString())) });
						else if ((type.equals("java.lang.Double"))
								|| (type.equals("double")))
							seter.invoke(o, new Object[] { Double
									.valueOf(Double.parseDouble(value
											.toString())) });
						else if ((type.equals("java.lang.Float"))
								|| (type.equals("float")))
							seter.invoke(o, new Object[] { Float.valueOf(Float
									.parseFloat(value.toString())) });
						else if ((type.equals("java.lang.Byte"))
								|| (type.equals("byte")))
							seter.invoke(o, new Object[] { Byte.valueOf(Byte
									.parseByte(value.toString())) });
						else if (type.equals("java.lang.String"))
							seter.invoke(o, new Object[] { value });
						else if (type.equals("java.util.Date"))
							seter.invoke(o, new Object[] { (Date) value });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
		}
		return o;
	}

	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			log.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if ((index >= params.length) || (index < 0)) {
			log.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
					+ "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log
					.warn(clazz.getSimpleName()
							+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}

	public static void main(String[] args) throws Exception {
	}
}