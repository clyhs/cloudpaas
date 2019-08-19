/**
 * 
 */
package com.cloudpaas.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudpaas.common.jwt.IJWTInfo;
import com.cloudpaas.common.jwt.JWTInfo;
import com.cloudpaas.gateway.callback.UserServiceFallback;



/**
 * @author 大鱼
 *
 * @date 2019年8月19日 上午9:45:14
 */
@FeignClient(value = "cpaas-admin",fallback = UserServiceFallback.class)
public interface IUserService {
	
	@RequestMapping(value="/admin/auth/info.json",method = RequestMethod.GET)
	JWTInfo getInfo(@RequestParam String token);

}
