/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import org.springframework.stereotype.Service;

import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午5:20:40
 */
@Service
public class UserBiz {

	public User getUser(String username){
		User u = new User();
		u.setName("admin");
		u.setUsername("admin");
		u.setPassword("123456");
		return u;
	}
}
