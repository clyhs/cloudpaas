/**
 * 
 */
package com.cloudpaas.common.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年8月5日 下午4:57:57
 */
@Component
@Data
@ConfigurationProperties(prefix="spring.datasource")
public class MultiDataSourceProperties {

	private List<DataSourceProperty> druid = new ArrayList<DataSourceProperty>(); 
}
