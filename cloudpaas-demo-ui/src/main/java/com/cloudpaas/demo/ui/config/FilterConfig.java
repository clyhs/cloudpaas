package com.cloudpaas.demo.ui.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月15日 下午5:41:57
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.setEnabled(true);
        registration.addUrlPatterns("/*");
        return registration;
    }

}