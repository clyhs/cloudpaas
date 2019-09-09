/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.springframework.stereotype.Repository;

import com.cloudpaas.admin.mapper.AdminRouterMapper;
import com.cloudpaas.gateway.model.Router;
import com.cloudpaas.plugin.mybatis.biz.ABaseBiz;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午10:14:18
 */
@Repository
public class AdminRouterBiz extends ABaseBiz<AdminRouterMapper, Router> {

}
