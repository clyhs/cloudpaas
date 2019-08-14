/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午6:58:36
 */
@Controller
@RequestMapping("menu")
public class MenuController {
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/menu";
	}

}
