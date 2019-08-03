/**
 * 
 */
package com.cloudpaas.service.pas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:53:03
 */
@Api
@RestController
@RequestMapping("quota")
public class PasQuotaController {
	
	@GetMapping("/getQuota")
	public String getQuota(){
		return "quota";
	}

}
