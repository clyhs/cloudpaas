/**
 * 
 */
package com.cloudpaas.admin.rest;

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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午3:51:36
 */
@Api(tags="auth api",value = "/auth", description = "认证接口")
@RestController
@RequestMapping("auth")
public class AdminAuthController {
	
	@Autowired
	AdminAuthBiz adminAuthBiz;
	
	@ApiOperation(value="获取令牌和用户信息接口",notes="")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "JwtRequest", name = "jwtRequest", value = "用户名和密码", required = true) })
	@RequestMapping(value = "/login.json",method = RequestMethod.GET)
	public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		JwtResponse jr = new JwtResponse();
		jr = adminAuthBiz.login(jwtRequest);
		
		return jr;
	}
	
	@ApiOperation(value="通过令牌获取用户信息接口",notes="")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "string", name = "token", value = "令牌", required = true) })
	@RequestMapping(value = "/info.json",method = RequestMethod.GET)
	public JWTInfo getInfo(@RequestParam String token) throws Exception{
		return adminAuthBiz.getInfofromToken(token);
	}

}
