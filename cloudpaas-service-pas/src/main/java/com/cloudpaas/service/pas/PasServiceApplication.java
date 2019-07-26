/**
 * 
 */
package com.cloudpaas.service.pas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



/**
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:49:50
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PasServiceApplication {

	public static void main(String[] args) {
        SpringApplication.run(PasServiceApplication.class, args);
    }
}
