package com.cloudpaas.service.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.cloudpaas.common.EnableAPISwagger2;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年9月4日 上午11:00:04
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
		XADataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
@EnableAPISwagger2 
@ComponentScan(basePackages = {"com.cloudpaas"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class DemoApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(DemoApplication.class);
    }
}
