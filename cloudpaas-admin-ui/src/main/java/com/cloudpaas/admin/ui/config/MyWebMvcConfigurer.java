package com.cloudpaas.admin.ui.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月12日 下午3:05:54
 */
@SpringBootConfiguration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * 配置静态资源路径
	 */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}