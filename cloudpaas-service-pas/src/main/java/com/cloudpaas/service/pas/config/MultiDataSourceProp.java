/**
 * 
 */
package com.cloudpaas.service.pas.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author 大鱼
 *
 * @date 2019年8月5日 下午5:50:41
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class MultiDataSourceProp {

}
