/**
 * 
 */
package com.cloudpaas.service.pas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.service.pas.dao.PasUserDao;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.properties.MultiDataSourceProperties;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午2:44:50
 */
@Api
@RestController
@RequestMapping("user")
public class PasUserController {
	
	private static Logger log = LoggerFactory.getLogger(PasUserController.class);

	@Autowired
	private PasUserDao userDao;
	

	@Autowired
	private MultiDataSourceProperties dataSourceProperties;
	
	@GetMapping("users.json")
	@ResponseBody
	public List<User> getUsers(@RequestParam(value = "db", defaultValue = "dn1", required = true) String db){
		
		//log.info(dataSourceProperties.getDn().length+"");
//		if("db2".equals(db)){
//			return userDao.selectall2();
//		}
		return userDao.selectall3(db);
	}
	
	@GetMapping("adddn1Users.json")
	@ResponseBody
	public Integer adddn1Users(){
		
		return userDao.insert_test();
	}
	
	@GetMapping("adddn2Users.json")
	@ResponseBody
	public Integer adddn2Users(){
		
		return userDao.insert_test2();
	}
	
	@GetMapping("add3Users.json")
	@ResponseBody
	public Integer add3Users(){
		
		return userDao.insert_test3();
	}
}
