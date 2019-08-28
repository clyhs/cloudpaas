/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudpaas.common.base.biz.ABaseBiz;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.mybatis.DataSource;
import com.cloudpaas.admin.mapper.AdminUserMapper;

/**
 * @author 大鱼
 *
 * @date 2019年7月26日 下午3:33:37
 */
@Repository
public class AdminUserBiz extends ABaseBiz<AdminUserMapper, User> {

	public User login(User user,@DataSource String db){
		return super.mapper.login(user);
	}
}
