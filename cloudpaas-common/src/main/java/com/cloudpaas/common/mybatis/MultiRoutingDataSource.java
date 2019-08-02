/**
 * 
 */
package com.cloudpaas.common.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 上午11:47:36
 * 
 * 配置多数据源类
 */
public class MultiRoutingDataSource extends AbstractRoutingDataSource {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DataSourceContextHolder.getDataSource();
	}

}
