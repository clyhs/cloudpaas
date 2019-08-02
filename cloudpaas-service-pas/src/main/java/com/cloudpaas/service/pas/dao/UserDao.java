/**
 * 
 */
package com.cloudpaas.service.pas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloudpaas.common.mybatis.DataSourceContextHolder;
import com.cloudpaas.service.pas.mapper.UserMapper;
import com.cloudpaas.service.pas.model.User;

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
}
