package com.springcloud.admin.controller;
import com.google.common.collect.Lists;
import com.springcloud.admin.entity.User;

import io.swagger.annotations.Api;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供用户信息查询API.
 **/
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
}