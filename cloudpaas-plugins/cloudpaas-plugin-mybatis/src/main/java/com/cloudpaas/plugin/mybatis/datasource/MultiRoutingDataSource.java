/**
 * 
 */
package com.cloudpaas.plugin.mybatis.datasource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

import javax.sql.XADataSource;

import org.apache.commons.lang3.StringUtils;
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

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.alibaba.druid.support.json.JSONUtils;
import com.cloudpaas.common.exception.MultiDataSourceException;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 上午11:47:36
 * 
 * 配置多数据源类
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
		log.info(key);
		if (!keySet.contains(key)) {
			log.info(String.format("can not found datasource by key: '%s',this session may use default datasource",
					key));
			throw new MultiDataSourceException("无法找到 "+key+" 节点的数据源！");
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
			Field sourceMapField = AbstractRoutingDataSource.class.getDeclaredField("targetDataSources");
			sourceMapField.setAccessible(true);
			Map<Object, Object> sourceMap = (Map<Object,Object>) sourceMapField.get(this);
			log.info(JSONUtils.toJSONString(sourceMap));
			//log.info(JSONUtils.toJSONString(keySet));
			//this.keySet = sourceMap.keySet();
			//sourceMapField.setAccessible(false);
		} catch (/*NoSuchFieldException | IllegalAccessException*/ Exception e) {
			log.error(e.getMessage());
		}

	}
//	
//	public void removeDataSource(String datakey) {
//        if (StringUtils.isBlank(datakey))
//            return;
//        try {
//            Field targetDataSources = AbstractRoutingDataSource.class.getDeclaredField("targetDataSources");
//            Field resolvedDataSources = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
//            targetDataSources.setAccessible(true);
//            resolvedDataSources.setAccessible(true);
//            Map<Object, Object> dataSources = (Map<Object, Object>) targetDataSources.get(this);
//            if (dataSources.get(datakey) != null) {
//                Map<Object, javax.sql.DataSource> dataSources2 = (Map<Object, javax.sql.DataSource>) resolvedDataSources.get(this);
//                dataSources.remove(datakey);
//                dataSources2.remove(datakey);
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//	
//	public void addDataSource(String datakey) {
//        if (StringUtils.isBlank(datakey))
//            return;
//        try {
//            Field targetDataSources = AbstractRoutingDataSource.class.getDeclaredField("targetDataSources");
//            Field resolvedDataSources = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
//            targetDataSources.setAccessible(true);
//            resolvedDataSources.setAccessible(true);
//            Map<Object, Object> dataSources = (Map<Object, Object>) targetDataSources.get(this);
//            if (dataSources.get(datakey) != null)
//                return;
//            Map<Object, DataSource> dataSources2 = (Map<Object, DataSource>) resolvedDataSources.get(this);
//            //
////            DruidDataSource dds = new DruidDataSource();
////            dds.setUrl("jdbc:mysql://" + dbInfo.getDbaddr() +
////                    ":" + dbInfo.getDbport() + "/" + dbInfo.getDbname() + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=true");
////            dds.setUsername(dbInfo.getUsername());
////            dds.setPassword(dbInfo.getPwd());
////            dataSources.put(userid, dds);
////            dataSources2.put(userid, dds);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

	

}
