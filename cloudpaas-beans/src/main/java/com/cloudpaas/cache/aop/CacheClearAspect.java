/**
 * 
 */
package com.cloudpaas.cache.aop;

import java.lang.reflect.Method;

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

import com.cloudpaas.cache.anno.CacheClear;
import com.cloudpaas.cache.anno.CacheWrite;
import com.cloudpaas.cache.keygen.DefaultKeyClearGenerator;
import com.cloudpaas.cache.service.IRedisService;

/**
 * 
 * 
 * @author 大鱼
 *
 * @date 2019年8月22日 下午7:57:18
 */
@Component
@Aspect
public class CacheClearAspect {
	
	private static Logger logger = LoggerFactory.getLogger(CacheWirteAspect.class);
	
	@Autowired
	RedisTemplate<String ,String> redisTemplate ;
	@Autowired
	DefaultKeyClearGenerator defaultKeyClearGenerator;
	@Autowired
	IRedisService redisSService;
	
	@Pointcut("@annotation(com.cloudpaas.cache.anno.CacheClear)")
    public void aspect() {
    }
	
	@Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, CacheClear anno)
            throws Throwable {
		
		MethodSignature signature = (MethodSignature) invocation.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = invocation.getTarget().getClass();
        //参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        //参数名称
        String[] argNames = signature.getParameterNames();
        //参数的值集合
        Object[] arguments = invocation.getArgs();
        String key = getKey(anno,targetClass,method,parameterTypes,argNames,arguments);
        if(null!=key){
        	logger.info("清除缓存的key为:{}",key);
        	redisSService.delPrefix(key);
        }
        if(null!=anno.keys() && anno.keys().length>0){
        	if(null!=anno.prefix() && ""!=anno.prefix() && anno.prefix().length() > 0){
        		for(String k:anno.keys()){
        			String kv = anno.prefix()+defaultKeyClearGenerator.PRE_LINE+k;
        			redisSService.delPrefix(kv);
        		}
        	}else{
        		for(String k:anno.keys()){
        			String kv = k;
        			redisSService.delPrefix(kv);
        		}
        	}
        }
		return invocation.proceed();
	}
	
	
	private String getKey(CacheClear anno,Class<?> target,Method method,Class<?>[] parameterTypes,
			String[] argNames, 
            Object[] arguments) throws InstantiationException,IllegalAccessException{
		String key = defaultKeyClearGenerator.getKey(anno, target, method, parameterTypes, argNames, arguments);
		return key;
	}

}
