/**
 * 
 */
package com.cloudpaas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.common.EnableAPISwagger2;
import com.cloudpaas.common.mybatis.MultiDataSourceRegister;



/**
 * @author 大鱼
 *
 * @date 2019年7月26日 下午12:42:12
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
		XADataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
@EnableAPISwagger2
@RestController
@ComponentScan(basePackages = {"com.cloudpaas"})
@EnableAspectJAutoProxy(exposeProxy = true)
@ServletComponentScan //配置myFilter
public class AllApplication {

	public static void main(String[] args) {
        SpringApplication.run(AllApplication.class, args);
    }
}
