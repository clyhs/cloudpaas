/**
 * 
 */
package com.cloudpaas.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.admin.biz.AdminRoleBiz;
import com.cloudpaas.common.controller.BaseController;
import com.cloudpaas.common.model.Role;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:41:52
 */
@Api
@RestController
@RequestMapping("role")
public class AdminRoleController extends BaseController<AdminRoleBiz, Role> {

}
