/**
 * 
 */
package com.cloudpaas.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.utils.ErrorCode;

import reactor.core.publisher.Mono;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午10:35:24
 */
@Service
public class RouterService implements ApplicationEventPublisherAware {
	
	@Autowired
    private RouteDefinitionWriter     routeDefinitionWriter;
	@Autowired
	private RouteDefinitionService redisRouterWriter;
	
    private ApplicationEventPublisher publisher;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher(org.springframework.context.ApplicationEventPublisher)
	 */
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.publisher = applicationEventPublisher;
	}
	
	//增加路由
    public BaseResponse add(RouteDefinition definition) {
    	redisRouterWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return new BaseResponse();
    }
    //更新路由
    public BaseResponse update(RouteDefinition definition) {
        try {
            delete(definition.getId());
        } catch (Exception e) {
            return new BaseResponse(ErrorCode.GATEWAYEX.getCode(),"update fail,not find route  routeId: "+definition.getId());
        }
        try {
        	redisRouterWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return new BaseResponse();
        } catch (Exception e) {
            return new BaseResponse(ErrorCode.GATEWAYEX.getCode(),ErrorCode.GATEWAYEX.getDesc());
        }
    }
    //删除路由
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.redisRouterWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (t) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }


}
