/**
 * 
 */
package com.cloudpaas.admin.ui.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 大鱼
 *
 * @date 2019年8月4日 下午10:46:03
 */
@Controller
@RequestMapping("admin")
public class MainController {

	@RequestMapping("/hello.html")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
	
	@RequestMapping("/index.html")
	public String index(){
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
