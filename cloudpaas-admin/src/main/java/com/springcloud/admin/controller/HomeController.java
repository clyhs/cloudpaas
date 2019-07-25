package com.springcloud.admin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * api首页.
 **/
@Controller
public class HomeController {
    @GetMapping(value = {"/api", "/"})
    public String api() {
        return "redirect:/swagger-ui.html";
    }

}