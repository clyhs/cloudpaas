/**
 * 
 */
package com.cloudpaas.admin.dao;

import org.springframework.stereotype.Repository;

import com.cloudpaas.admin.mapper.AdminRoleMapper;
import com.cloudpaas.common.base.dao.BaseDao;
import com.cloudpaas.common.model.Role;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:41:07
 */
@Repository
public class AdminRoleDao extends BaseDao<AdminRoleMapper, Role> {

}
