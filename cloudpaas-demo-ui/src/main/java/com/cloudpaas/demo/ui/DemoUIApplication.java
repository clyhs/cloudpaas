package com.cloudpaas.demo.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月4日 下午10:35:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloudpaas"})
@EnableWebMvc
@EnableAspectJAutoProxy(exposeProxy = true)
public class DemoUIApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(DemoUIApplication.class, args);
    }
}
