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
	
	@Pointcut("@annotation(com.cloudpaas.cache.anno.CacheClear)")
    public void aspect() {
    }
	
	@Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, CacheClear anno)
            throws Throwable {
		
		MethodSignature signature = (MethodSignature) invocation.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = invocation.getTarget().getClass();
		
		return invocation.proceed();
	}
	
	private String getKey(CacheWrite anno,Class<?> target,Method method,Class<?>[] parameterTypes,
			String[] argNames, 
            Object[] arguments) throws InstantiationException,IllegalAccessException{
		
		return null;
	}

}
