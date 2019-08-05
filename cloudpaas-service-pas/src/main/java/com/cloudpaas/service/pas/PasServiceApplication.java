/**
 * 
 */
package com.cloudpaas.service.pas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.common.EnableAPISwagger2;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.cloudpaas.common.datasource.DataSourceProperties;


/**
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:49:50
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAPISwagger2 
public class PasServiceApplication {

	public static void main(String[] args) {
        SpringApplication.run(PasServiceApplication.class, args);
    }
	
//	@Bean
//	public DataSourceProperties DataSourceProperties(){
//		return new DataSourceProperties();
//	}
	
}
