package com.core.jdbc.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.core.jdbc.annotation.Column;
import com.core.log.LogHelper;

public class BomcRowMapper<T> implements RowMapper {
	private static final Logger log = LogHelper.getLog(BomcRowMapper.class);
	private Class<T> gt;

	public BomcRowMapper(Class<T> gt) {
		this.gt = gt;
	}

	public Class<T> getGenericType() {
		return this.gt;
	}

	public void setGenericType(Class<T> gt) {
		this.gt = gt;
	}

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Object o = null;
		PropertyDescriptor[] ps = (PropertyDescriptor[]) null;
		try {
			ps = Introspector.getBeanInfo(this.gt).getPropertyDescriptors();
			o = this.gt.newInstance();
			for (PropertyDescriptor p : ps) {
				Method method = p.getReadMethod();
				if ((method != null)
						&& (method.isAnnotationPresent(Column.class))) {
					String sMethod = method.getName().replaceFirst("g", "s");
					Method seter = o.getClass().getMethod(sMethod,
							new Class[] { method.getReturnType() });
					Column f = (Column) method.getAnnotation(Column.class);
					String type = method.getReturnType().getName();
					// log.info(f.fieldName());
					if ((type.equals("java.lang.Integer"))
							|| (type.equals("int")))
						seter.invoke(o, new Object[] { Integer.valueOf(rs
								.getInt(f.fieldName())) });
					else if ((type.equals("java.lang.Long"))
							|| (type.equals("long")))
						seter.invoke(o, new Object[] { Long.valueOf(rs
								.getLong(f.fieldName())) });
					else if ((type.equals("java.lang.Boolean"))
							|| (type.equals("boolean")))
						seter.invoke(o, new Object[] { Boolean.valueOf(rs
								.getBoolean(f.fieldName())) });
					else if ((type.equals("java.lang.Short"))
							|| (type.equals("short")))
						seter.invoke(o, new Object[] { Short.valueOf(rs
								.getShort(f.fieldName())) });
					else if ((type.equals("java.lang.Double"))
							|| (type.equals("double")))
						seter.invoke(o, new Object[] { Double.valueOf(rs
								.getDouble(f.fieldName())) });
					else if ((type.equals("java.lang.Float"))
							|| (type.equals("float")))
						seter.invoke(o, new Object[] { Float.valueOf(rs
								.getFloat(f.fieldName())) });
					else if ((type.equals("java.lang.Byte"))
							|| (type.equals("byte")))
						seter.invoke(o, new Object[] { Byte.valueOf(rs
								.getByte(f.fieldName())) });
					else if (type.equals("java.util.Date"))
						seter.invoke(o, new Object[] { rs.getTimestamp(f
								.fieldName()) });
					else
						seter.invoke(o, new Object[] { rs.getString(f
								.fieldName()) });
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return o;
	}
}