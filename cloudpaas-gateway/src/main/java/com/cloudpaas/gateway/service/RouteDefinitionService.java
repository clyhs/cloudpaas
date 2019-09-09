/**
 * 
 */
package com.cloudpaas.gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.common.constants.CommonConstants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午10:41:37
 */
@Service
public class RouteDefinitionService implements RouteDefinitionRepository {
	
	//public static final String  GATEWAY_ROUTES = "geteway_routes";
    @Autowired
    private StringRedisTemplate redisTemplate;

	/* (non-Javadoc)
	 * @see org.springframework.cloud.gateway.route.RouteDefinitionLocator#getRouteDefinitions()
	 */
	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		// TODO Auto-generated method stub
		List<RouteDefinition> routeDefinitions = new ArrayList<>();
        redisTemplate.opsForHash().values(CommonConstants.ROUTER_KEY).stream().forEach(routeDefinition -> {
            routeDefinitions.add(JSON.parseObject(routeDefinition.toString(), RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitions);
	}

	/* (non-Javadoc)
	 * @see org.springframework.cloud.gateway.route.RouteDefinitionWriter#delete(reactor.core.publisher.Mono)
	 */
	@Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisTemplate.opsForHash().hasKey(CommonConstants.ROUTER_KEY, id)) {
                redisTemplate.opsForHash().delete(CommonConstants.ROUTER_KEY, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }

	/* (non-Javadoc)
	 * @see org.springframework.cloud.gateway.route.RouteDefinitionWriter#save(reactor.core.publisher.Mono)
	 */
	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		// TODO Auto-generated method stub
		return route
                .flatMap(routeDefinition -> {
                    redisTemplate.opsForHash().put(CommonConstants.ROUTER_KEY, routeDefinition.getId(),
                            JSON.toJSONString(routeDefinition));
                    return Mono.empty();
                });
	}

}
