package com.cloudpaas.admin.ui.config;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

//@Configuration
public class RestAutoConfig {

    public static class RestTemplateConfig {

        @Bean//负载均衡的restTemplate
        //@LoadBalanced //spring 对restTemplate bean进行定制，加入loadbalance拦截器进行ip:port的替换
                    //"http://user/getusername，就能解析成http://127.0.0.1:8083//getusername
        RestTemplate lbRestTemplate(HttpClient httpclient) {
            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpclient));
            template.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("utf-8")));
            template.getMessageConverters().add(1,new FastJsonHttpMessageConvert5());
            return template;
        }
        
        @Bean //直连的restTemplat，这时只能使用http://127.0.0.1:8083//getusername地址，不能解析http://user/getusername
        RestTemplate directRestTemplate(HttpClient httpclient) {
            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpclient));
            template.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("utf-8")));
            template.getMessageConverters().add(1,new FastJsonHttpMessageConvert5());
            return template;
        }
        
        // FastJsonHttpMessageConvert4有一个bug，它是默认支持MediaType.ALL，spring在处理MediaType.ALL的时候会识别成字节流，而不是json，这里就对他进行改造和处理
         public static class FastJsonHttpMessageConvert5 extends FastJsonHttpMessageConverter4{
              
              static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
              
                  public FastJsonHttpMessageConvert5(){
                setDefaultCharset(DEFAULT_CHARSET);
                setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,new MediaType("application","*+json")));
              }

            }
    }

}