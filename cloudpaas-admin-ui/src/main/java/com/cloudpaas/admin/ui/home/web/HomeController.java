/**
 * 
 */
package com.cloudpaas.admin.ui.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大鱼
 *
 * @date 2019年9月10日 下午7:13:18
 */
@Controller
@RequestMapping("home")
public class HomeController {

	@RequestMapping("/index.html")
	public String main(){
		return "home/index";
	}
}
