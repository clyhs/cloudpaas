/**
 * 
 */
package com.cloudpaas.service.pas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.service.pas.dao.UserDao;
import com.cloudpaas.service.pas.model.User;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午2:44:50
 */
@Api
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@GetMapping("users.json")
	@ResponseBody
	public List<User> getUsers(@RequestParam(value = "db", defaultValue = "dn1", required = true) String db){
		if("db2".equals(db)){
			return userDao.selectall2();
		}
		return userDao.selectall();
	}
}
