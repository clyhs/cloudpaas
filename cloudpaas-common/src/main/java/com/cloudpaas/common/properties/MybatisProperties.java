/**
 * 
 */
package com.cloudpaas.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年8月5日 下午11:38:53
 */
@Component
@Data
@ConfigurationProperties(prefix="mybatis")
public class MybatisProperties {
	
    private String configLocations;
    
    private String mapperLocations;
    
    private String basepackage;

}
