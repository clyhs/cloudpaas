/**
 * 
 */
package com.cloudpaas.demo.ui.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudpaas.common.model.User;
import com.cloudpaas.demo.ui.base.BaseController;

/**
 * @author 大鱼
 *
 * @date 2019年8月4日 下午10:46:03
 */
@Controller
@RequestMapping("main")
public class MainController extends BaseController{


	@RequestMapping("/index.html")
	public String index(ModelMap modelMap){
		User user = getLogin();
		modelMap.addAttribute("username", user.getUsername());
		return "admin/index";
	}
	
	@RequestMapping("/main.html")
	public String main(){
		return "admin/main";
	}
	
}
