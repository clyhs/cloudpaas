package com.cloudpaas.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.biz.AdminAuthBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.exception.TokenInvalidException;
import com.cloudpaas.common.jwt.IJWTInfo;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.utils.ErrorCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月17日 下午5:27:00
 */
@Slf4j
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
public class AccessFilter implements Filter {
	@Autowired
	AdminAuthBiz adminAuthBiz;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        log.debug("url:{}" , requestURI);
        
//        PrintWriter writer = null;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        
//        IJWTInfo user = null;
//        try {
//            user = getJWTUser(request);
//            filterChain.doFilter(request, servletResponse);
//        } catch (Exception e) {
//            log.error("用户Token过期异常", e);
//            //throw new TokenInvalidException(e.getMessage());
//            
//            OutputStreamWriter osw = null;
//            try {
//                osw = new OutputStreamWriter(response.getOutputStream(),
//                        "UTF-8");
//                writer = new PrintWriter(osw, true);
//                String jsonStr = JSON.toJSONString(new BaseResponse(ErrorCode.TOKENEX.getCode(),e.getMessage()));
//                writer.write(jsonStr);
//                writer.flush();
//                
//            } catch (IOException ex) {
//                log.error("获取OutputStreamm失败" + ex.getMessage());
//            }finally{
//            	osw.close();
//            }
//        }finally{
//        	writer.close();
//        }
        
        filterChain.doFilter(request, servletResponse);
        
        
    }
    
    private IJWTInfo getJWTUser(HttpServletRequest request) throws Exception {
    	String token = null;
        token = request.getHeader(CommonConstants.TOKEN_HEADER_KEY);
       
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
            
        }
        
        return adminAuthBiz.getInfofromToken(token);
        
    }

    @Override
    public void destroy() {
    	log.debug("destroy...");
    }
}