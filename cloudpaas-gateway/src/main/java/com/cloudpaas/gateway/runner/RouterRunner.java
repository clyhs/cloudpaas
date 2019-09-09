/**
 * 
 */
package com.cloudpaas.gateway.runner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.gateway.feign.IRouterService;
import com.cloudpaas.gateway.model.Router;
import com.cloudpaas.gateway.service.RouterServiceAware;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 下午3:10:15
 */
@Slf4j
@Configuration
public class RouterRunner implements CommandLineRunner{
	
	@Autowired
	RouterServiceAware routerServiceAware;
	@Autowired
	IRouterService routerService;
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		PageResponse<Router> routers = routerService.getAll();
		if(null!=routers && routers.getData().size()>0){
			for(Router r:routers.getData()){
				log.debug("routerId:{}",r.getRouterId());
				RouteDefinition routerDef = convertRouter(r);
				if(r.getEnabled()==1){
					routerServiceAware.update(routerDef);
				}else{
					routerServiceAware.delete(r.getRouterId());
				}
				
			}
		}*/
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
