package com.cloudpaas.common.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月2日 上午11:48:50
 * 
 * 切换数据源注解类，默认选择dn1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	String name() default DataSource.dn1;
	
	public static String dn1 = "dn1";
}