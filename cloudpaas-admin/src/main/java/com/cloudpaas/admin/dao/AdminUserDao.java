/**
 * 
 */
package com.cloudpaas.admin.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudpaas.common.model.User;
import com.cloudpaas.admin.mapper.AdminUserMapper;
import com.cloudpaas.common.base.dao.BaseDao;

/**
 * @author 大鱼
 *
 * @date 2019年7月26日 下午3:33:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminUserDao extends BaseDao<AdminUserMapper, User> {

}
