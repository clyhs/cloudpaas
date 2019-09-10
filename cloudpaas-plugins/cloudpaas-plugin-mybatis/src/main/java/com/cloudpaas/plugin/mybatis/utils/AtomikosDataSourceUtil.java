/**
 * 
 */
package com.cloudpaas.plugin.mybatis.utils;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.cloudpaas.plugin.mybatis.prop.DataSourceProperty;

/**
 * @author 大鱼
 *
 * @date 2019年8月8日 下午3:12:36
 * 
 * datasource构建类
 */
public class AtomikosDataSourceUtil {
	
	private static Logger log = LoggerFactory.getLogger(AtomikosDataSourceUtil.class);
	
    private static String dataSourceClassName = "com.alibaba.druid.pool.xa.DruidXADataSource";
	
	public static DataSource getDataSource(Environment env,String prefix,String dataSourceName) {
        Properties prop = build(env,prefix);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName(dataSourceClassName);
        ds.setUniqueResourceName(dataSourceName);
        ds.setXaProperties(prop);
        return ds;
    }
	
	public static DataSource getDataSource(DataSourceProperty dataDourceProperty) throws Exception{
		DataSource dataSource = DruidDataSourceFactory.createDataSource(build(dataDourceProperty));
		return dataSource;
	}
	
	public static DataSource getAtomikosDataSource(DataSourceProperty dataDourceProperty){
		Properties prop = null;
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		//com.ibm.db2.jcc.DB2XADataSource
		if(dataDourceProperty.getDriverClassName().equals("com.ibm.db2.jcc.DB2Driver")){
			ds.setXaDataSourceClassName("com.ibm.db2.jcc.DB2XADataSource");
			prop = buildDB2(dataDourceProperty);
		}else{
			prop = build(dataDourceProperty);
			ds.setXaDataSourceClassName(dataSourceClassName);
		}
        ds.setUniqueResourceName(dataDourceProperty.getKey());
        ds.setXaProperties(prop);
		
		return ds;
	}

	/**
	 * 主要针对DruidXADataSource数据库链接池
	 * 
	 * @param dataDourceProperty
	 * @return
	 */
	private static Properties build(DataSourceProperty dataDourceProperty) {
        Properties prop = new Properties();
        prop.put("url", dataDourceProperty.getUrl());
        prop.put("username", dataDourceProperty.getUsername());
        prop.put("password", dataDourceProperty.getPassword());
        prop.put("driverClassName", dataDourceProperty.getDriverClassName());
        prop.put("initialSize", dataDourceProperty.getInitialSize()+"");
        prop.put("maxActive", dataDourceProperty.getMaxActive()+"");
        prop.put("minIdle", dataDourceProperty.getMinIdle()+"");
        prop.put("maxWait", dataDourceProperty.getMaxWait()+"");
        prop.put("poolPreparedStatements", dataDourceProperty.getPoolPreparedStatements()+"");
        prop.put("maxPoolPreparedStatementPerConnectionSize",dataDourceProperty.getMaxPoolPreparedStatementPerConnectionSize()+"");
        prop.put("validationQuery", dataDourceProperty.getValidationQuery());
        prop.put("validationQueryTimeout", dataDourceProperty.getValidationQueryTimeout()+"");
        prop.put("testOnBorrow", dataDourceProperty.getTestOnBorrow()+"");
        prop.put("testOnReturn", dataDourceProperty.getTestOnReturn()+"");
        prop.put("testWhileIdle", dataDourceProperty.getTestWhileIdle()+"");
        
        prop.put("defaultAutoCommit", dataDourceProperty.getDefaultAutoCommit()+"");
        prop.put("removeAbandoned", dataDourceProperty.getRemoveAbandoned()+"");
        
//        prop.put("minPoolSize", dataDourceProperty.getMinPoolSize()+"");
//        prop.put("maxPoolSize", dataDourceProperty.getMaxPoolSize()+"");
//        prop.put("borrowConnectionTimeout", dataDourceProperty.getBorrowConnectionTimeout()+"");
        
        prop.put("timeBetweenEvictionRunsMillis", dataDourceProperty.getTimeBetweenEvictionRunsMillis()+"");
        prop.put("minEvictableIdleTimeMillis", dataDourceProperty.getMinEvictableIdleTimeMillis()+"");
        prop.put("filters", dataDourceProperty.getFilters());
        return prop;
    }
	
	
	private static Properties buildDB2(DataSourceProperty dataDourceProperty) {
        Properties prop = new Properties();
        
        Integer dataIndex =dataDourceProperty.getUrl().lastIndexOf("/");
 	   	String dataname = dataDourceProperty.getUrl().substring(dataIndex+1);
 	   	String prefix = dataDourceProperty.getUrl().substring(0, dataIndex);
 	   	Integer db2Index = prefix.lastIndexOf("/");
 	   	String server = prefix.substring(db2Index+1);
 	    String str[] = server.split(":");
        prop.put("databaseName", dataname);
        prop.put("serverName", str[0]);
        prop.put("portNumber", str[1]);
        prop.put("user", dataDourceProperty.getUsername());
        prop.put("password", dataDourceProperty.getPassword());
        prop.put("driverType", "4");
//        prop.put("minPoolSize", dataDourceProperty.getMinPoolSize()+"");
//        prop.put("maxPoolSize", dataDourceProperty.getMaxPoolSize()+"");
//        prop.put("checkValidConnectionSql", dataDourceProperty.getValidationQuery());
//        prop.put("blockingTimeoutMillis", "10000");
        
        //prop.put("driverClassName", dataDourceProperty.getDriverClassName());

        return prop;
    }
   
   /**
    * 主要针对DruidXADataSource数据库链接池
    * @param env
    * @param prefix
    * @return
    */
   private static Properties build(Environment env, String prefix) {
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
       
       prop.put("defaultAutoCommit", env.getProperty(prefix + "defaultAutoCommit"));
       prop.put("removeAbandoned", env.getProperty(prefix + "removeAbandoned"));
//       prop.put("minPoolSize", env.getProperty(prefix + "minPoolSize"));
//       prop.put("maxPoolSize",  env.getProperty(prefix + "maxPoolSize"));
//       prop.put("borrowConnectionTimeout",  env.getProperty(prefix + "borrowConnectionTimeout"));
       prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "timeBetweenEvictionRunsMillis"));
       prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis"));
       prop.put("filters", env.getProperty(prefix + "filters"));
       return prop;
   }
   

   
   public static void main(String[] args ){
	   String url = "jdbc:db2://192.168.0.17:50000/cpas03";
	   Integer dataIndex = url.lastIndexOf("/");
	   String dataname = url.substring(dataIndex+1);
	   System.out.println(dataname);
	   
	   String prefix = url.substring(0, dataIndex);
	   System.out.println(prefix);
	   
	   Integer db2Index = prefix.lastIndexOf("/");
	   
	   String server = prefix.substring(db2Index+1);
	   
	   System.out.println(server);
   }
   
   
   
}
