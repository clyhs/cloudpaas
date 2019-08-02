/**
 * 
 */
package com.cloudpaas.service.pas.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.cloudpaas.common.mybatis.MultiRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午5:14:44
 */
@Configuration
@MapperScan(basePackages = {"com.cloudpaas.service.pas.mapper"}) 
public class MybatisConfig extends AbstractDataSourceConfig implements EnvironmentAware{
	
	
	private Environment env;
	
	@Primary
    @Bean(name = "dataSource_dn1")
	@ConfigurationProperties(prefix = "spring.datasource.druid.dn1" )
    public DataSource dataSourceDn1() throws Exception {
		
		String prefix = "spring.datasource.druid.dn1.";
        return getDataSource(env,prefix,"dn1");
    }

	@Bean(name = "dataSource_dn2")
	@ConfigurationProperties(prefix = "spring.datasource.druid.dn2" )
    public DataSource dataSourceDn2() throws Exception {
		String prefix = "spring.datasource.druid.dn2.";
        return getDataSource(env,prefix,"dn2");
    }
	
	

	
	@Bean("dynamicDataSource")
    public DataSource dynamicDataSource() throws Exception {
		MultiRoutingDataSource dynamicDataSource = new MultiRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("dn1", dataSourceDn1());
        dataSourceMap.put("dn2", dataSourceDn2());
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceDn1());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }
	
	@Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.cloudpaas.**.model");    // 扫描Model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/*.xml"));    // 扫描映射文件
        return sessionFactory;
    }
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.cloudpaas.service.pas.mapper.**");
		return mapperScannerConfigurer;
	}
	
//	@Bean
//    public PlatformTransactionManager platformTransactionManager() throws Exception {
//		return new DataSourceTransactionManager(dynamicDataSource());//放入数据源
//    }


	/* (non-Javadoc)
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment env) {
		// TODO Auto-generated method stub
		this.env = env;
	}
	

}
