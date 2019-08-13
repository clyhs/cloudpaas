/**
 * 
 */
package com.cloudpaas.admin.dao;

import org.springframework.stereotype.Repository;

import com.cloudpaas.admin.mapper.AdminMenuMapper;
import com.cloudpaas.admin.model.AdminMenu;
import com.cloudpaas.common.base.dao.BaseDao;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午2:36:11
 */
@Repository
public class AdminMenuDao extends BaseDao<AdminMenuMapper, AdminMenu> {

}
