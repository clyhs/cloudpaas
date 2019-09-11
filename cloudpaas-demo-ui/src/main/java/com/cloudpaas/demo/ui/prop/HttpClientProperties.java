package com.cloudpaas.demo.ui.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置HTTP请求的属性
 * 
 * @author 大鱼
 *
 * @date 2019年8月16日 上午10:50:55
 */
@ConfigurationProperties(prefix="spring.httpclient")
public class HttpClientProperties {
    
    private Integer connectTimeOut = 1000;
    
    private Integer socketTimeOut = 1000000;

    private String agent = "agent";
    private Integer maxConnPerRoute = 10;
    private Integer maxConnTotaol   = 50;
    public Integer getConnectTimeOut() {
        return connectTimeOut;
    }
    public void setConnectTimeOut(Integer connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }
    public Integer getSocketTimeOut() {
        return socketTimeOut;
    }
    public void setSocketTimeOut(Integer socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }
    public String getAgent() {
        return agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }
    public Integer getMaxConnPerRoute() {
        return maxConnPerRoute;
    }
    public void setMaxConnPerRoute(Integer maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }
    public Integer getMaxConnTotaol() {
        return maxConnTotaol;
    }
    public void setMaxConnTotaol(Integer maxConnTotaol) {
        this.maxConnTotaol = maxConnTotaol;
    }
    
    
    
}