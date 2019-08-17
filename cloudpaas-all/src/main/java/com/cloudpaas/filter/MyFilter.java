package com.cloudpaas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月17日 下午5:27:00
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if(requestURI.contains("my")){
            System.out.println("成功啦。。。, 请求URI是:" + requestURI);
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}