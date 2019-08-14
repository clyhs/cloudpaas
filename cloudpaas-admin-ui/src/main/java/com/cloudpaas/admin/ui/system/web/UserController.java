/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 上午10:42:03
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/user";
	}

}
