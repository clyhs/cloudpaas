package com.cloudpaas.admin.ui.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudpaas.admin.ui.prop.HttpClientProperties;

@Configuration
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {

    private final HttpClientProperties properties;
    
    
    public HttpClientAutoConfiguration(HttpClientProperties properties){
        this.properties = properties;
    }
    
    /**
     * httpclient bean 的定义
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(HttpClient.class)
    public HttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(properties.getConnectTimeOut())
                .setSocketTimeout(properties.getSocketTimeOut()).build();// 构建requestConfig
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setUserAgent(properties.getAgent())
                .setMaxConnPerRoute(properties.getMaxConnPerRoute())
                .setMaxConnTotal(properties.getMaxConnTotaol())
                .build();
        return client;
    }
}