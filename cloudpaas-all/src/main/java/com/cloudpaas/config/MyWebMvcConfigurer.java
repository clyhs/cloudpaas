package com.cloudpaas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer  {

    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")
		        .allowedOrigins("*")  
		        .allowCredentials(true)
		        .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
		        .maxAge(3600);    
    }

}