package com.cloudpaas.admin.controller;
import com.cloudpaas.admin.dao.UserDao;
import com.cloudpaas.admin.entity.User;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
    private UserDao userDao;
	
    @GetMapping("/lists")
    public List<User> lists() {
    	List<User> users = userDao.selectListAll();
        return users;
    }
    
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name){
    	return "hi "+name+" ,nice to meet you !";
    }
}