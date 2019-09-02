package com.cloudpaas.plugin.mybatis.datasource;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 切换数据源aop
 * 
 * @author 大鱼
 *
 * @date 2019年8月7日 下午6:46:50
 */

@Component
@Aspect
@Order(-10) // 使该切面在事务之前执行
public class DataSourceSwitchInterceptor {

	private static Logger log = LoggerFactory.getLogger(DataSourceSwitchInterceptor.class);

	/**
	 * 扫描所有含有@MultiDataSource$DataSource注解的类
	 */
	//@Pointcut("@within(com.cloudpaas.common.mybatis.DataSource)")
	@Pointcut("execution(public * com.cloudpaas..biz.*.*(..))")
	public void switchDataSource() {
		log.info("进入aop切换");
	}

	/**
	 * 使用around方式监控
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("switchDataSource()")
	public Object switchByMethod(ProceedingJoinPoint point) throws Throwable {
		Method method = getMethodByPoint(point); // 获取执行方法
		Parameter[] params = method.getParameters(); // 获取执行参数
		Parameter parameter;
		String source = null;
		boolean isDynamic = false;
		for (int i = params.length - 1; i >= 0; i--) { // 扫描是否有参数带有@DataSource注解
			parameter = params[i];
			if (parameter.getAnnotation(DataSource.class) != null && point.getArgs()[i] instanceof String) {
				source = (String) point.getArgs()[i]; // key值即该参数的值，要求该参数必须为String类型
				isDynamic = true;
				break;
			}
		}
		if (!isDynamic) { // 不存在参数带有Datasource注解
			DataSource dataSource = method.getAnnotation(DataSource.class); // 获取方法的@DataSource注解
			if (null == dataSource || !StringUtils.hasLength(dataSource.name())) { // 方法不含有注解
				dataSource = method.getDeclaringClass().getAnnotation(DataSource.class); // 获取类级别的@DataSource注解
			}
			if (null != dataSource) {
				source = dataSource.name(); // 设置key值
			}
		}
		log.debug("切换到datasource:"+source);
		return persistBySource(source, point); // 继续执行该方法
	}

	private Object persistBySource(String source, ProceedingJoinPoint point) throws Throwable {
		try {
			DataSourceContextHolder.setDataSource(source); // 切换数据源
			return point.proceed(); // 执行
		} finally {
			DataSourceContextHolder.clearDataSource(); // 清空key值
		}
	}

	private Method getMethodByPoint(ProceedingJoinPoint point) {
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		return methodSignature.getMethod();
	}
}