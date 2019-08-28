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
 * @date 2019年8月28日 下午4:57:26
 */
@Retention(RetentionPolicy.RUNTIME)//在运行时可以获取  
@Target(value = {ElementType.PARAMETER, ElementType.TYPE})//作用到类，方法，接口上等
public @interface IgnoreField {

}
