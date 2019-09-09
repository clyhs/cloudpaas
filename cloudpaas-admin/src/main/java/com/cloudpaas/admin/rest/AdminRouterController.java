/**
 * 
 */
package com.cloudpaas.admin.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.admin.biz.AdminRouterBiz;
import com.cloudpaas.gateway.model.Router;
import com.cloudpaas.plugin.mybatis.rest.BaseController;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午11:42:21
 */
@Api(tags="router api",value = "/router", description = "路由操作接口")
@RestController
@RequestMapping("router")
public class AdminRouterController extends BaseController<AdminRouterBiz, Router> {

}
