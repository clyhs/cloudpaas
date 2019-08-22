package com.cloudpaas.admin.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月4日 下午10:35:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloudpaas"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class AdminUIApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AdminUIApplication.class, args);
    }
}
