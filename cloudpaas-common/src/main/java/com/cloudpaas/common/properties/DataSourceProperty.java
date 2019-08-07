/**
 * 
 */
package com.cloudpaas.common.properties;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年8月5日 下午5:10:24
 */
@Data
public class DataSourceProperty {

	private String  key;
	
	private String  url;
	
	private String  username;
	
	private String  password;
	
	private String  driverClassName;
	
	private Integer minIdle;
	
	private Integer maxActive;
	
	private Integer initialSize;
	
	private Integer timeBetweenEvictionRunsMillis;
	
	private Integer minEvictableIdleTimeMillis;
	
	private String  validationQuery;
	
	private Integer validationQueryTimeout; 
	
	private Boolean testWhileIdle;
	
	private Boolean testOnBorrow;
	
	private Boolean testOnReturn;
	
	private Integer maxWait;
	
	private Boolean poolPreparedStatements;
	
	private Integer maxPoolPreparedStatementPerConnectionSize;
	
	private String  filters;
}
