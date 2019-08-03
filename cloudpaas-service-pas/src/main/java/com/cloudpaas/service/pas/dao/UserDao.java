/**
 * 
 */
package com.cloudpaas.service.pas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloudpaas.common.mybatis.DataSourceContextHolder;
import com.cloudpaas.service.pas.mapper.UserMapper;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午2:40:31
 */
@Repository
public class UserDao {

	@Autowired
	private UserMapper userMapper;

	public List<User> selectall() {
		DataSourceContextHolder.setDataSource("dn1");
		return userMapper.select_test(null);
	}

	public List<User> selectall2() {
		DataSourceContextHolder.setDataSource("dn2");
		return userMapper.select_test(null);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Integer insert_test(){
		DataSourceContextHolder.setDataSource("dn1");
		User u = new User();
		u.setName("ccc");
		
		Integer res = userMapper.insert_test(u);
		
		return res;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Integer insert_test2(){
		DataSourceContextHolder.setDataSource("dn2");
		User u = new User();
		u.setName("ccc");
		
		Integer res = userMapper.insert_test(u);
		int i = 5/0;
		return res;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Integer insert_test3(){
		
		User u = new User();
		u.setName("ccc");
		DataSourceContextHolder.setDataSource("dn2");
		Integer res = userMapper.insert_test(u);
		DataSourceContextHolder.setDataSource("dn1");
		Integer res2 = userMapper.insert_test(u);
		int i = 5/0;
		return res;
	}
}
