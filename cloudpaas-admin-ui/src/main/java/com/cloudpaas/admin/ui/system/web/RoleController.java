/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:35:55
 */
@Controller
@RequestMapping("role")
public class RoleController {

	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/role";
	}
}
