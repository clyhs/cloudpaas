package com.cloudpaas.gateway.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.gateway.service.IHelloService;




@RestController
public class HelloController {
	
	@Autowired
    IHelloService helloService;

    @GetMapping(value = "/hello")
    public String sayHello(@RequestParam String name) {
        return helloService.sayHello(name);
    }

}
