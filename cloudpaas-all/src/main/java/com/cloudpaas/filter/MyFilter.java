/**
 * 
 */
package com.cloudpaas.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author 大鱼
 *
 * @date 2019年8月29日 上午9:57:21
 */
@Configuration
public class MyFilter {
	
	@Bean
    public FilterRegistrationBean<Filter> sessionExpireFilter(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();
        registrationBean.setFilter(this.accessFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
	
	@Bean
    public Filter accessFilter() {
        return new AccessFilter();
    }

}
