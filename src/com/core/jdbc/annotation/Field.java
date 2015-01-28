package com.core.jdbc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { java.lang.annotation.ElementType.FIELD,
		java.lang.annotation.ElementType.METHOD })
public @interface Field {
	public abstract String fieldName();

	public abstract boolean primaryKey() default false;

	public abstract boolean autoIncrement() default false;
}
