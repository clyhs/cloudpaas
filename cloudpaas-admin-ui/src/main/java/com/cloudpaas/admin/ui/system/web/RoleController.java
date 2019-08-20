/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudpaas.admin.ui.system.biz.RoleBiz;
import com.cloudpaas.common.model.Role;
import com.google.gson.Gson;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:35:55
 */
@Controller
@RequestMapping("role")
public class RoleController {
	
	private static Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleBiz roleBiz;

	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/role";
	}
	
	@RequestMapping("/add.html")
	public String add(){
		return "admin/layui/system/roleAdd";
	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Role role = roleBiz.getRoleByID(id);
		modelMap.put("role", role);
		return "admin/layui/system/roleEdit";
	}
}
