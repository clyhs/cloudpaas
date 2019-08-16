/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudpaas.admin.ui.base.BaseController;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月4日 下午10:46:03
 */
@Controller
@RequestMapping("main")
public class MainController extends BaseController{

	@RequestMapping("/hello.html")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
	
	@RequestMapping("/index.html")
	public String index(ModelMap modelMap){
		User user = getLogin();
		modelMap.addAttribute("username", user.getUsername());
		return "admin/layui/index";
	}
	
	@RequestMapping("/main.html")
	public String main(){
		return "admin/layui/main";
	}
	
	@RequestMapping("/demo.html")
	public String demo(){
		return "admin/layui/demo";
	}
}
