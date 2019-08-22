/**
 * 
 */
package com.cloudpaas.cache.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 上午10:37:37
 */
@Retention(RetentionPolicy.RUNTIME)
//在运行时可以获取
@Target(value = {ElementType.METHOD, ElementType.TYPE})
//作用到类，方法，接口上等
public @interface CacheWrite {
	
	/**
	 * 键值
	 * @return
	 */
	public String key() default "";
	
	/**
	 * 健值前缀
	 * @return
	 */
	public String prefix() default "";
	
	/**
	 * 过期时间
	 * @return
	 */
	public int expire() default 600;
	
	
	/**
	 * 生成器
	 * @return
	 */
	public String keyGenerator() default "";
	
	
	/**
	 * 是否包含包名，类名，方法名，默认启用
	 * @return
	 */
	public String pkg() default "false";

}
