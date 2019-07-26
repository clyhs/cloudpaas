package com.cloudpaas.gateway.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "cpaas-admin")
public interface IHelloService {
	
	@RequestMapping(value="/user/sayHello",method = RequestMethod.GET)
	public String sayHello(@RequestParam(value = "name") String name);

}
