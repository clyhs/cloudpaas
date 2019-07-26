package com.springcloud.admin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:12:47
 */
@Controller
public class HomeController {
    @GetMapping(value = {"/api", "/"})
    public String api() {
        return "redirect:/swagger-ui.html";
    }

}