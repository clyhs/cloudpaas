package com.springcloud.admin.controller;
import com.google.common.collect.Lists;
import com.springcloud.admin.entity.User;

import io.swagger.annotations.Api;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:12:55
 */
@Api
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/lists")
    public List<User> lists() {
    	List<User> users = Lists.newArrayList(
    	        new User(4, "abigfish", 150, LocalDate.now()),
    	        new User(6, "clyhs", 150, LocalDate.now())
    	);
        return users;
    }
    
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name){
    	return "hi "+name+" ,nice to meet you !";
    }
}