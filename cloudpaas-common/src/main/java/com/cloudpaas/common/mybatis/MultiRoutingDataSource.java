/**
 * 
 */
package com.cloudpaas.common.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

import javax.sql.XADataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.alibaba.druid.support.json.JSONUtils;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 上午11:47:36
 * 
 *       配置多数据源类
 */
public class MultiRoutingDataSource extends AbstractRoutingDataSource {

	private static Logger log = LoggerFactory.getLogger(MultiRoutingDataSource.class);

	// 所有数据源的key集合
	private Set<Object> keySet;
	
	

	public Set<Object> getKeySet() {
		return keySet;
	}

	public void setKeySet(Set<Object> keySet) {
		this.keySet = keySet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#
	 * determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		String key = DataSourceContextHolder.getDataSource();
		if (!keySet.contains(key)) {
			log.info(String.format("can not found datasource by key: '%s',this session may use default datasource",
					key));
		}
		return key;
	}

	/**
	 * 在获取key的集合，目的只是为了添加一些告警日志
	 */
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		try {
			//Field sourceMapField = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
			//sourceMapField.setAccessible(true);
			//Map<Object, Object> sourceMap = (Map<Object,Object>) sourceMapField.get(this);
			
			log.info(JSONUtils.toJSONString(keySet));
			//this.keySet = sourceMap.keySet();
			//sourceMapField.setAccessible(false);
		} catch (/*NoSuchFieldException | IllegalAccessException*/ Exception e) {
			log.error(e.getMessage());
		}

	}

	

}
