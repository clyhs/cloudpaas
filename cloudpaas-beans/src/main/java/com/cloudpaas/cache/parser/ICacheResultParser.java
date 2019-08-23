/**
 * 
 */
package com.cloudpaas.cache.parser;

import java.lang.reflect.Type;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 下午6:11:24
 */
public interface ICacheResultParser {

	public Object parse(String value, Type returnType,Class<?> targetClass);
}
