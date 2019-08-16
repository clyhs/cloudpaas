/**
 * 
 */
package com.cloudpaas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.admin.biz.AdminAuthBiz;
import com.cloudpaas.admin.model.JwtRequest;
import com.cloudpaas.admin.model.JwtResponse;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午3:51:36
 */
@Api
@RestController
@RequestMapping("auth")
public class AdminAuthController {
	
	@Autowired
	AdminAuthBiz adminAuthBiz;
	
	@RequestMapping(value = "/login.json",method = RequestMethod.GET)
	public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception{
		String token = adminAuthBiz.login(jwtRequest);
		JwtResponse jr = new JwtResponse();
		jr.setToken(token);
		
		return jr;
	}

}
