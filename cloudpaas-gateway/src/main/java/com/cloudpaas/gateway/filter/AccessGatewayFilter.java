/**
 * 
 */
package com.cloudpaas.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author 大鱼
 *
 * @date 2019年8月17日 下午5:21:49
 */
public class AccessGatewayFilter implements GlobalFilter {

	/* (non-Javadoc)
	 * @see org.springframework.cloud.gateway.filter.GlobalFilter#filter(org.springframework.web.server.ServerWebExchange, org.springframework.cloud.gateway.filter.GatewayFilterChain)
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		return null;
	}

}
