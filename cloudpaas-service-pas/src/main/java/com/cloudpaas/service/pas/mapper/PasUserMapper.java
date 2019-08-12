/**
 * 
 */
package com.cloudpaas.service.pas.mapper;

import java.util.List;
import java.util.Map;

import com.cloudpaas.common.model.User;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午2:37:42
 */
public interface PasUserMapper extends Mapper<User> {
	
	public List<User> select_test(Map map);
	
	public int insert_test(User t);

}