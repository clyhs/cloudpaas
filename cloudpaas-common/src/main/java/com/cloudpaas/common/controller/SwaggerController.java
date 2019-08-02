/**
 * 
 */
package com.cloudpaas.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 下午1:38:43
 * 
 * swagger2接口页面
 */
@Controller
public class SwaggerController {

	@GetMapping(value = {"/api", "/"})
    public String api() {
        return "redirect:/swagger-ui.html";
    }
}
