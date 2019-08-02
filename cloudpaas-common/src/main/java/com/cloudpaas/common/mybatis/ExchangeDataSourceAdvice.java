/**
 * 
 */
package com.cloudpaas.common.mybatis;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 上午11:52:04
 * 
 * 数据源切换类，使用aop注解切换
 * 
 * 
 */
public class ExchangeDataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
	
	private static Logger log = LoggerFactory.getLogger(ExchangeDataSourceAdvice.class);

	/* (non-Javadoc)
	 * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		DataSourceContextHolder.clearDataSource();
	}

	/* (non-Javadoc)
	 * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void before(Method method, Object[] arg1, Object arg2) throws Throwable {
		// TODO Auto-generated method stub
		if (method.isAnnotationPresent(DataSource.class))   
        {  
			
            DataSource datasource = method.getAnnotation(DataSource.class);
            
            if(null == datasource || (null != datasource && null == datasource.name()))
            	
            	DataSourceContextHolder.setDataSource(datasource.dn1);
    		else {
    			
    			DataSourceContextHolder.setDataSource(datasource.name());  
    		}
            
            log.debug("正在使用:"+DataSourceContextHolder.getDataSource()+"数据源！");
        }
		

	}

}
