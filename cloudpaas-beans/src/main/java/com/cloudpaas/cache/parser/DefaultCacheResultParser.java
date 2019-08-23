/**
 * 
 */
package com.cloudpaas.cache.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 下午6:12:04
 */
public class DefaultCacheResultParser implements ICacheResultParser {
	
	private static Logger log = LoggerFactory.getLogger(DefaultCacheResultParser.class);

	/* (non-Javadoc)
	 * @see com.cloudpaas.cache.parser.ICacheResultParser#parse(java.lang.String, java.lang.reflect.Type, java.lang.Class[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object parse(String value, Type returnType ,Class<?> targetClass) {
		// TODO Auto-generated method stub
		Object result = null;
        if (returnType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) returnType;
            Type rawType = parameterizedType.getRawType();
            if (((Class) rawType).isAssignableFrom(List.class)) {
                result = JSON.parseArray(value, (Class) parameterizedType.getActualTypeArguments()[0]);
            }
            else{
            	result = JSON.parseObject(value, returnType);
            }
        } else {
        	//处理接口返回泛弄类型，拿不到返回值的类型问题
        	if("T".equals(returnType.toString())){
              ParameterizedType pt = (ParameterizedType)targetClass.getGenericSuperclass();
              returnType =pt.getActualTypeArguments()[0];
        	}
        	
        	result = JSON.parseObject(value, returnType);
         
        } 
        return result;
	}

}
