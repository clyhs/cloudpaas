/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.springframework.stereotype.Repository;

import com.cloudpaas.admin.mapper.AdminMenuMapper;

import com.cloudpaas.common.model.Menu;
import com.cloudpaas.plugin.mybatis.base.ABaseBiz;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午2:36:11
 */
@Repository
public class AdminMenuBiz extends ABaseBiz<AdminMenuMapper, Menu> {

}
