/**
 * 
 */
package com.cloudpaas.demo.ui.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 前端属性类
 * 
 * @author 大鱼
 *
 * @date 2019年8月26日 上午10:59:41
 */
@Component
@Data
@ConfigurationProperties(prefix = "ui")
public class UIProperites {

	private String type;
	
	private String apiUrl;
}
