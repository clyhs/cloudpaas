/**
 * 
 */
package com.cloudpaas.trace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

/**
 * @date 2019年7月25日 下午11:01:44
 *
 * @author 大鱼
 *
 */
@SpringBootApplication
@EnableZipkinServer
public class TraceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TraceApplication.class, args);
	}
}
