/**
 * 
 */
package com.cloudpaas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * @author 大鱼
 *
 * @date 2019年7月26日 下午12:42:12
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cloudpaas.admin.mapper")
public class AllApplication {

	public static void main(String[] args) {
        SpringApplication.run(AllApplication.class, args);
    }
}
