/**
 * 
 */
package com.cloudpaas.cache.aop;

import java.lang.reflect.Method;
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


import com.cloudpaas.cache.anno.CacheWrite;
import com.cloudpaas.cache.keygen.DefaultKeyGenerator;




/**
 * @author 大鱼
 *
 * @date 2019年8月22日 下午2:49:30
 */
@Component
@Aspect
public class CacheWirteAspect {
	private static Logger logger = LoggerFactory.getLogger(CacheWirteAspect.class);
	
	@Autowired
	RedisTemplate<String ,Object> redisTemplate ;
	
	@Pointcut("@annotation(com.cloudpaas.cache.anno.CacheWrite)")
    public void aspect() {
    }
	
	@Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, CacheWrite anno)
            throws Throwable {
		
		MethodSignature signature = (MethodSignature) invocation.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = invocation.getTarget().getClass();
        String packageName =invocation.getTarget().getClass().getPackage().getName();
        String className = invocation.getTarget().getClass().getSimpleName();
        
        logger.info("--------"+packageName);
        
        logger.info("--------"+className);
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
        	logger.info("----------key:{}-------",key);
        	//反回结果类型
        	Type returnType = method.getGenericReturnType();
        	
        	result = invocation.proceed();
        	
        }catch (Exception e) {
            logger.error("获取缓存失败：" + key, e);
        } finally {
            if (result == null) {
                result = invocation.proceed();
                if (StringUtils.isNotBlank(key)) {
                	//将值写入缓存
                	//redisTemplate.opsForValue().set(key, result, anno.expire());
                }
            }
        }
		
		return result;
	}
	
	private String getKey(CacheWrite anno,Class<?> target,Method method,Class<?>[] parameterTypes,
			String[] argNames, 
            Object[] arguments) throws InstantiationException,IllegalAccessException{
		String key;
        String generatorClsName = anno.keyGenerator();
		DefaultKeyGenerator kg = new DefaultKeyGenerator();
		key = kg.getKey(anno,target,method, parameterTypes, argNames,arguments);
		return key;
	}
}
