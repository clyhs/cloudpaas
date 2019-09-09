/**
 * 
 */
package com.cloudpaas.gateway.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.gateway.callback.RouterServiceFallBack;
import com.cloudpaas.gateway.callback.UserServiceFallback;
import com.cloudpaas.gateway.model.Router;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 下午3:05:20
 */
@FeignClient(value = "cpaas-admin",fallback = RouterServiceFallBack.class)
public interface IRouterService {

	@RequestMapping(value="/router/all.json",method = RequestMethod.GET)
	public PageResponse<Router> getAll();
}
