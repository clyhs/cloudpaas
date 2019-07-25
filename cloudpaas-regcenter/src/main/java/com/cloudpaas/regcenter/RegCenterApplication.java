/**
 * 
 */
package com.cloudpaas.regcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @date 2019年7月25日 上午9:49:28
 *
 * @author 大鱼
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class RegCenterApplication {
	
	public static void main(String[] args) {
        SpringApplication.run( RegCenterApplication.class, args );
    }

}
