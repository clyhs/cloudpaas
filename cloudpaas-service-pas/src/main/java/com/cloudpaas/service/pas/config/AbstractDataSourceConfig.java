/**
 * 
 */
package com.cloudpaas.service.pas.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午4:42:45
 */
public abstract class AbstractDataSourceConfig {
	
	protected DataSource getDataSource(Environment env,String prefix,String dataSourceName) {
        Properties prop = build(env,prefix);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName(dataSourceName);
        
        ds.setXaProperties(prop);
        
        return ds;
    }

	protected DataSource getDataSourceWithDruid(Environment env,String prefix,String dataSourceName) throws Exception{
		Properties prop = buildWithDruid(env,prefix);
		DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
		
		return dataSource;
	}
	/**
     * 主要针对DruidXADataSource数据库链接池
     * @param env
     * @param prefix
     * @return
     */
    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        
        
        
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        
        
        
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
 
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
 
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));
        return prop;
    }
    
    /**
     * 主要针对druid数据库链接池
     * @param env
     * @param prefix
     * @return
     */
    protected Properties buildWithDruid(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        
        
        
        prop.put("initialSize", env.getProperty(prefix + "initialSize"));
        
        prop.put("maxActive", env.getProperty(prefix + "maxActive"));
        prop.put("minIdle", env.getProperty(prefix + "minIdle"));
        prop.put("maxWait", env.getProperty(prefix + "maxWait"));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements"));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize"));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize"));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout"));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow"));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn"));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle"));
        prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "timeBetweenEvictionRunsMillis"));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis"));
        prop.put("filters", env.getProperty(prefix + "filters"));
        return prop;
    }
}
