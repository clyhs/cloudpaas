/**
 * 
 */
package com.cloudpaas.service.pas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:53:03
 */
@RestController
@RequestMapping("quota")
public class QuotaController {
	
	@GetMapping("/getQuota")
	public String getQuota(){
		return "quota";
	}

}
