/**
 * 
 */
package com.cloudpaas.cache.aop;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.cache.anno.CacheWrite;
import com.cloudpaas.cache.keygen.DefaultKeyGenerator;
import com.cloudpaas.cache.parser.DefaultCacheResultParser;
import com.cloudpaas.cache.service.IRedisOService;
import com.cloudpaas.cache.service.IRedisSService;

/**
 * 加入缓存注解CacheWrite的切面类
 * 
 * @author 大鱼
 *
 * @date 2019年8月22日 下午2:49:30
 */
@Component
@Aspect
public class CacheWirteAspect {
	
	private static Logger logger = LoggerFactory.getLogger(CacheWirteAspect.class);
	
//	@Autowired
//	RedisTemplate<String ,String> redisTemplate ;
	@Autowired
	IRedisSService redisService;
	
	@Pointcut("execution(* com.cloudpaas.admin.ui..*.*(..)) && @annotation(com.cloudpaas.cache.anno.CacheWrite)")
    public void aspect() {
    }
	
	@Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, CacheWrite anno)
            throws Throwable {
		
		MethodSignature signature = (MethodSignature) invocation.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = invocation.getTarget().getClass();
        
//        ParameterizedType pt = (ParameterizedType)targetClass.getGenericSuperclass();
//        logger.info(pt.getActualTypeArguments()[0]+"");

        Object result = null;
        //参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        //参数名称
        String[] argNames = signature.getParameterNames();
        //参数的值集合
        Object[] arguments = invocation.getArgs();
        String key = "";
        String value = "";
        try {
        	key = getKey(anno,targetClass,method,parameterTypes, argNames,arguments);
        	logger.info("生成缓存的key为:{}",key);
        	//反回结果类型
        	Type returnType = method.getGenericReturnType();
        	logger.info(method.getGenericReturnType().getClass()+"");
        	value = getCache(key);
        	//result = invocation.proceed();
        	result = getResult( anno, result, value, returnType,targetClass);
        }catch (Exception e) {
            logger.error("获取缓存失败：" + key, e);
        } finally {
            if (result == null) {
            	logger.info("获取数据，并添加到缓存中！");
                result = invocation.proceed();
                if (StringUtils.isNotBlank(key)) {
                	//将值写入缓存
                	setCache(result, key, anno);
                }
            }
        }
		return result;
	}
	/**
	 * 设置缓存
	 * @param value
	 * @param key
	 * @param anno
	 */
	private void setCache(Object value,String key,CacheWrite anno){
		String realValue = null;
		if (value instanceof String) {
            //realValue = value.toString();
			realValue = JSON.toJSONString(value);
        } else {
            realValue = JSON.toJSONString(value, false);
        }
		redisService.set(key, realValue, anno.expire());
	}
	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	private String getCache(String key){
		return redisService.get(key);
	}
	
	/**
	 * 生成KEY
	 * @param anno
	 * @param target
	 * @param method
	 * @param parameterTypes
	 * @param argNames
	 * @param arguments
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private String getKey(CacheWrite anno,Class<?> target,Method method,Class<?>[] parameterTypes,
			String[] argNames, 
            Object[] arguments) throws InstantiationException,IllegalAccessException{
		String key;
        String generatorClsName = anno.keyGenerator();
		DefaultKeyGenerator kg = new DefaultKeyGenerator();
		key = kg.getKey(anno,target,method, parameterTypes, argNames,arguments);
		return key;
	}
	/**
	 * 解析结果
	 * @param anno
	 * @param result
	 * @param value
	 * @param returnType
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private Object getResult(CacheWrite anno, Object result, String value,
            Type returnType,Class<?> targetClass) throws InstantiationException,IllegalAccessException {
		if(value!=null){
			logger.debug("从缓存获取数据进行解析");
		}
		DefaultCacheResultParser parser = new DefaultCacheResultParser();
		result = parser.parse(value, returnType,targetClass);
		return result;
	}
}
