/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.springframework.stereotype.Repository;

import com.cloudpaas.admin.mapper.AdminRoleMapper;

import com.cloudpaas.common.model.Role;
import com.cloudpaas.plugin.mybatis.biz.ABaseBiz;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:41:07
 */
@Repository
public class AdminRoleBiz extends ABaseBiz<AdminRoleMapper, Role> {

}
