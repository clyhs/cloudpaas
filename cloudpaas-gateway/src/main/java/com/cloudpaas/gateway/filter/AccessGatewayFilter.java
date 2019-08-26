/**
 * 
 */
package com.cloudpaas.gateway.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.jwt.IJWTInfo;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.utils.ErrorCode;
import com.cloudpaas.gateway.feign.IUserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 大鱼
 *
 * @date 2019年8月17日 下午5:21:49
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {
	
	@Autowired
	IUserService userService;
	
	private static final String GATE_WAY_PREFIX = "/api";

	/* (non-Javadoc)
	 * @see org.springframework.cloud.gateway.filter.GlobalFilter#filter(org.springframework.web.server.ServerWebExchange, org.springframework.cloud.gateway.filter.GatewayFilterChain)
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		log.info("check token");
		LinkedHashSet requiredAttribute = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
		ServerHttpRequest request = exchange.getRequest();
		String requestUri = request.getPath().pathWithinApplication().value();
		if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()){
                URI next = iterator.next();
                if(next.getPath().startsWith("/api")){
                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
                    
                }
            }
        }
		log.debug("requestUri:{}",requestUri);
		ServerHttpRequest.Builder mutate = request.mutate();
		/*
		IJWTInfo user = null;
		ServerHttpRequest.Builder mutate = request.mutate();
		try {
            user = getJWTUser(request, mutate);
        } catch (Exception e) {
            log.error("用户Token过期异常", e);
            return getVoidMono(exchange, new BaseResponse(ErrorCode.TOKENEX.getCode(),ErrorCode.TOKENEX.getDesc()));
        }
		*/
		
		ServerHttpRequest build = mutate.build();
		
		return chain.filter(exchange.mutate().request(build).build());
	}
	
	/**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IJWTInfo getJWTUser(ServerHttpRequest request, ServerHttpRequest.Builder ctx) throws Exception {
        List<String> strings = request.getHeaders().get(CommonConstants.TOKEN_HEADER_KEY);
        String token = null;
        if (strings != null) {
            token = strings.get(0);
        }
        if (StringUtils.isBlank(token)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                token = strings.get(0);
            }
        }
        
        return userService.getInfo(token);
    }
    
    /**
     * 网关抛异常
     *
     * @param body
     */
    @NotNull
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, BaseResponse body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
