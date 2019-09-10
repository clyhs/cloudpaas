package com.cloudpaas.plugin.mybatis.datasource;

import com.alibaba.druid.support.json.JSONUtils;
import com.cloudpaas.common.constants.CommonConstants;

import com.cloudpaas.common.utils.JSONUtil;
import com.cloudpaas.plugin.mybatis.prop.DataSourceProperty;
import com.cloudpaas.plugin.mybatis.utils.AtomikosDataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 注册多数据源，可以代替MybatisConfig的dynamicDataSource方法 
 * 
 * @author 大鱼
 *
 * @date 2019年8月6日 下午7:01:38
 */
public class MultiDataSourceRegister implements EnvironmentAware, ImportBeanDefinitionRegistrar {

    private static Logger log = LoggerFactory.getLogger(MultiDataSourceRegister.class);
	
	private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases(); //别名

    static {
        //由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
        aliases.addAliases("url", new String[]{"jdbc-url"});
        aliases.addAliases("username", new String[]{"user"});
    }

    private Environment evn; //配置上下文（也可以理解为配置文件的获取工具）

    private Map<String, DataSource> sourceMap;  //数据源列表

    private Binder binder; //参数绑定工具
    

    /**
     * ImportBeanDefinitionRegistrar接口的实现方法，通过该方法可以按照自己的方式注册bean
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map config, properties, defaultConfig = binder.bind("spring.datasource", Map.class).get(); //获取所有数据源配置
        sourceMap = new HashMap<>(); //默认配置
        //默认数据源类型
        String typeStr = evn.getProperty("spring.datasource.type"); 
        log.debug("application.yml's spring.datasource value :"+JSONUtil.toJson(defaultConfig));
        //获取数据源类型
        Class<? extends DataSource> defaultClazz = getDataSourceType(typeStr); 
        log.debug("default datasource drive class:"+defaultClazz.toString());
        DataSource defaultDataSource = null;
        //绑定数据源参数 方法一
        List<DataSourceProperty> configs = binder.bind("spring.datasource.druid", Bindable.listOf(DataSourceProperty.class)).get();
        
        if(null!=configs && configs.size()>0){
        	log.info("spring.datasource.druid init start ...");
        	for (int i = 0; i < configs.size(); i++) { //遍历生成其他数据源
            	DataSourceProperty dp = configs.get(i);
            	log.info("datasource "+" key "+dp.getKey());
            	
                DataSource consumerDatasource;
				try {
					consumerDatasource = AtomikosDataSourceUtil.getDataSource(dp);
					//获取数据源的key，以便通过该key可以定位到数据源
	                sourceMap.put(dp.getKey(), consumerDatasource); 
	                if(dp.getKey().equals(CommonConstants.DEFAULT_DATASOURCE_KEY)){
	                	defaultDataSource = consumerDatasource;
	                }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					log.error(e.getMessage());
				} 
                
            }
        	log.info("spring.datasource.druid init end  ...");
        }else{
        	log.info("spring.datasource.druid can not found - key: dn value");
        }
        //绑定数据源参数 方法二
        /*
        List<Map> configs = binder.bind("spring.datasource.druid", Bindable.listOf(Map.class)).get();
        if(null!=configs && configs.size()>0){
        	for (int i = 0; i < configs.size(); i++) {
                config = configs.get(i);
                properties = config;
                DataSource consumerDatasource = bind(defaultClazz, properties);
                sourceMap.put(config.get("key").toString(), consumerDatasource); 
                if(config.get("key").toString().equals(CommonConstants.DEFAULT_DATASOURCE_KEY)){
                	defaultDataSource = consumerDatasource;
                }
        	}
        }*/
        
        
        log.info(JSONUtil.beanToJson(sourceMap));
        //bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition(); 
        //设置bean的类型，此处MultiDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(MultiRoutingDataSource.class); 
        //需要注入的参数，类似spring配置文件中的<property/>
        MutablePropertyValues mpv = define.getPropertyValues(); 
        //添加默认数据源，避免key不存在的情况没有数据源可用
        mpv.add("defaultTargetDataSource", defaultDataSource); 
        //添加其他数据源
        mpv.add("targetDataSources", sourceMap); 
        mpv.add("keySet", sourceMap.keySet());
        //将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("dynamicDataSource", define); 
      
    }

    /**
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
    private Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) { //字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                type = HikariDataSource.class;  //默认为hikariCP数据源，与springboot默认数据源保持一致
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }

    /**
     * 绑定参数，以下三个方法都是参考DataSourceBuilder的bind方法实现的，目的是尽量保证我们自己添加的数据源构造过程与springboot保持一致
     *
     * @param result
     * @param properties
     */
    private void bind(DataSource result, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(result));  //将参数绑定到对象
    }
    
    private <T extends DataSource> T bind(Class<T> clazz) {
        Binder binder = new Binder();
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get(); //通过类型绑定参数并获得实例对象
    }
    

    private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get(); //通过类型绑定参数并获得实例对象
    }

    /**
     * @param clazz
     * @param sourcePath 参数路径，对应配置文件中的值，如: spring.datasource
     * @param <T>
     * @return
     */
    private <T extends DataSource> T bind(Class<T> clazz, String sourcePath) {
        Map properties = binder.bind(sourcePath, Map.class).get();
        return bind(clazz, properties);
    }

    /**
     * EnvironmentAware接口的实现方法，通过aware的方式注入，此处是environment对象
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.evn = environment;
        binder = Binder.get(evn); //绑定配置器
    }
    
}