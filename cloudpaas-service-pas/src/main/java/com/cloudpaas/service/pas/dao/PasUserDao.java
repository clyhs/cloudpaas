/**
 * 
 */
package com.cloudpaas.service.pas.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cloudpaas.common.mybatis.DataSource;
import com.cloudpaas.common.mybatis.DataSourceContextHolder;
import com.cloudpaas.service.pas.mapper.PasUserMapper;
import com.cloudpaas.common.base.biz.ABaseBiz;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午2:40:31
 */
@Repository
public class PasUserDao extends ABaseBiz<PasUserMapper,User>{
	
	private static Logger log = LoggerFactory.getLogger(PasUserDao.class);

//	@Autowired
//	private PasUserMapper userMapper;

	/**
	 * 通过@DataSource注解参数切换数据源
	 * @param db
	 * @return
	 */
	public List<User> selecttest(@DataSource String db) {
		return mapper.select_test(null);
	}

	/**
	 * 通过DataSourceContextHolder切换数据源
	 * @param db
	 * @return
	 */
	public List<User> selecttest2(String db) {
		DataSourceContextHolder.setDataSource(db);
		return mapper.select_test(null);
	}
	
	/**
	 * 通过@DataSource切换数据源，验证事物
	 * @param db
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer insert_test(@DataSource String db){
		User u = new User();
		u.setName("ccc");
		Integer res = mapper.insert_test(u);
		return res;
	}
	/**
	 * 通过DataSourceContextHolder切换数据源
	 * @param db
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer insert_test2(String db){
		DataSourceContextHolder.setDataSource(db);
		User u = new User();
		u.setName("ccc");
		Integer res = mapper.insert_test(u);
		int i = 5/0;
		return res;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Integer insert_test3(@DataSource String db){
		User u = new User();
		u.setName("ccc");
		Integer res = mapper.insert_test(u);
		Integer res2 = mapper.insert_test(u);
		int i = 5/0;
		return res;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Integer insert_test4(String db){
		DataSourceContextHolder.setDataSource(db);
		User u = new User();
		u.setName("ccc");
		Integer res = mapper.insert_test(u);
		Integer res2 = mapper.insert_test(u);
		int i = 5/0;
		return res;
	}
}
