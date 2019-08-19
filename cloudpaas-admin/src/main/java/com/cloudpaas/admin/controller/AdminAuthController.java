/**
 * 
 */
package com.cloudpaas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.admin.biz.AdminAuthBiz;
import com.cloudpaas.admin.model.JwtRequest;
import com.cloudpaas.admin.model.JwtResponse;
import com.cloudpaas.common.jwt.IJWTInfo;
import com.cloudpaas.common.jwt.JWTInfo;

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
		
		JwtResponse jr = new JwtResponse();
		jr = adminAuthBiz.login(jwtRequest);
		
		return jr;
	}
	
	@RequestMapping(value = "/info.json",method = RequestMethod.GET)
	public JWTInfo getInfo(@RequestParam String token) throws Exception{
		return adminAuthBiz.getInfofromToken(token);
	}

}
