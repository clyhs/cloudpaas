/**
 * 
 */
package com.cloudpaas.gateway.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.gateway.model.Router;
import com.cloudpaas.gateway.service.RouterService;

import reactor.core.publisher.Mono;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午11:16:21
 */
@RestController
@RequestMapping("/route")
public class RouterController {
	
	@Autowired
	private RouterService routerService;
	
	@PostMapping("/add.json")
	public BaseResponse add(@RequestBody Router router){
		BaseResponse response = new BaseResponse();
		RouteDefinition definition = convertRouter(router);
		response = routerService.add(definition);
		return response;
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> delete(@PathVariable String id){
		return routerService.delete(id);
	}
	
	//更新路由
    @PostMapping("/update.json")
    public BaseResponse update(@RequestBody Router router) {
    	BaseResponse response = new BaseResponse();
    	RouteDefinition definition = convertRouter(router);
        return routerService.update(definition);
    }
    
    private RouteDefinition convertRouter(Router router){
    	RouteDefinition definition = new RouteDefinition();
    	definition.setId(router.getRouterId());
    	definition.setOrder(router.getRouterOrder());
    	
    	//设置断言
        List<PredicateDefinition> pdList=new ArrayList<>();
        
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");
        predicate.addArg("_genkey_0", "/api/"+router.getPath()+"/**");
        pdList.add(predicate);
        definition.setPredicates(pdList);
        
        //设置过滤器
        List<FilterDefinition> filters = new ArrayList();
        FilterDefinition filter = new FilterDefinition();
        filter.setName("StripPrefix");
        filter.addArg("_genkey_0", router.getStripPrefix());
        filters.add(filter);
        definition.setFilters(filters);
        URI uri = null;
        uri = URI.create(router.getUri());
        definition.setUri(uri);
    	return definition;
    }


}
