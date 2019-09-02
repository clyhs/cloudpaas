/**
 * 
 */
package com.cloudpaas.plugin.redis.genkey;

import java.lang.reflect.Method;

import com.cloudpaas.plugin.redis.anno.CacheWrite;


/**
 * @author 大鱼
 *
 * @date 2019年8月22日 下午3:10:53
 */
public abstract class AbstractKeyGenerator implements IKeyGenerator {

	public static final String LINE = "_";
	
	public static final String PRE_LINE = ":";
	
	/**
	 * 获得生成的KEY
	 * @param key
	 * @param parameterTypes
	 * @param arguments
	 * @return
	 */
	public String getKey(CacheWrite anno,Class<?> target,Method method, Class<?>[] parameterTypes, 
			String[] argNames,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer("");
		String key = buildKey(anno, target,method, parameterTypes, argNames,arguments);
		sb.append(key);
		return sb.toString();
	}
	
	public abstract String buildKey(CacheWrite anno,Class<?> target,Method method,
            Class<?>[] parameterTypes, String[] argNames,Object[] arguments);
}
