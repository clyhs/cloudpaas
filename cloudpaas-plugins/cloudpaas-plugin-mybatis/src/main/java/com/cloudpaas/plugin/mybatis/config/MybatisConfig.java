/**
 * 
 */
package com.cloudpaas.plugin.mybatis.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.utils.JSONUtil;
import com.cloudpaas.plugin.mybatis.datasource.MultiRoutingDataSource;
import com.cloudpaas.plugin.mybatis.prop.DataSourceProperty;
import com.cloudpaas.plugin.mybatis.prop.MultiDataSourceProperties;
import com.cloudpaas.plugin.mybatis.prop.MybatisProperties;
import com.cloudpaas.plugin.mybatis.utils.AtomikosDataSourceUtil;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;



/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午2:19:32
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)  
public class MybatisConfig{
	
	private static Logger log = LoggerFactory.getLogger(MybatisConfig.class);
	
	@Autowired
	private MybatisProperties mybatisProperties;
	
	@Autowired
	private MultiDataSourceProperties dataSourceProperties;

    /**
     * 手动配置数据源dn2
     * @param env
     * @return
     * @throws Exception
     */
//	@Primary
//    @Bean(name = "dataSource_dn1")
//	@ConfigurationProperties(prefix = "spring.datasource.druid[0]" )
//    public DataSource dataSourceDn1(Environment env) throws Exception {
//		String prefix = "spring.datasource.druid[0].";
//        return DataSourceUtil.getDataSource(env,prefix,"dn1");
//    }
	/**
	 * 手动配置数据源dn2 
	 * @param env
	 * @return
	 * @throws Exception
	 */
//	@Bean(name = "dataSource_dn2")
//	@ConfigurationProperties(prefix = "spring.datasource.druid[1]" )
//    public DataSource dataSourceDn2(Environment env) throws Exception {
//		String prefix = "spring.datasource.druid[1].";
//        return getDataSource(env,prefix,"dn2");
//    }
	

	
	@Bean("dynamicDataSource")
    public DataSource dynamicDataSource(/*@Qualifier("dataSource_dn1")DataSource dataSource_dn1,
    		@Qualifier("dataSource_dn2")DataSource dataSource_dn2*/) throws Exception {
		
		log.info("-----------开始初始化数据源-------------");
        //log.info(dataSourceProperties.getDruid().size()+"");
		Map<Object, Object> dataSourceMap = new HashMap<>();
		
		MultiRoutingDataSource dynamicDataSource = new MultiRoutingDataSource();
		DataSource dataSource = null;
		DataSource defaultDataSource = null;
		if(null!=dataSourceProperties.getDruid() && dataSourceProperties.getDruid().size()>0){
			for(DataSourceProperty dsp:dataSourceProperties.getDruid()){
				if(null!=dsp){
					log.debug("datasource "+" key "+dsp.getKey()+":"+JSONUtil.toJson(dsp));
					if(dataSourceProperties.getType().equals("com.alibaba.druid.pool.xa.DruidXADataSource")){
						log.debug("");
						dataSource = AtomikosDataSourceUtil.getAtomikosDataSource(dsp);
					}else{
						dataSource = AtomikosDataSourceUtil.getDataSource(dsp);
					}
					
					dataSourceMap.put(dsp.getKey(), dataSource);
					if(dsp.getKey().equals(CommonConstants.DEFAULT_DATASOURCE_KEY)){
						defaultDataSource = dataSource;
					}
				}
			}
		}else{
			log.error("spring.datasource.druid[] is null,please set datasource.druid");
		}
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setKeySet(dataSourceMap.keySet());
        dynamicDataSource.afterPropertiesSet();
        log.info("-----------完成初始化数据源-------------");
        return dynamicDataSource;
    }
	
	@Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setTypeAliasesPackage("com.cloudpaas.**.model");    // 扫描Model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        log.info(mybatisProperties.getMapperLocations());
        sessionFactory.setMapperLocations(resolver.getResources(mybatisProperties.getMapperLocations()));    // 扫描映射文件
        sessionFactory.setConfigLocation(resolver.getResource(mybatisProperties.getConfigLocations()));
        return sessionFactory;
    }
	
	@Bean
	public static MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.cloudpaas.**.mapper");
		
		return mapperScannerConfigurer;
	}
	

	


}
