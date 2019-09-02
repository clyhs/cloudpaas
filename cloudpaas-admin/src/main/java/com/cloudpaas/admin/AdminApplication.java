package com.cloudpaas.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.common.EnableAPISwagger2;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.mybatis.spring.annotation.MapperScan;



/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:13:21
 */

@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
		XADataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
@EnableAPISwagger2
@RestController
@ComponentScan(basePackages = {"com.cloudpaas"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
    
    

//    @Configuration
//    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().permitAll()
//                .and().csrf().disable();
//        }
//    }
//    
    

}