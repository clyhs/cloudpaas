/**
 * 
 */
package com.cloudpaas.admin.ui.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 大鱼
 *
 * @date 2019年8月4日 下午10:37:29
 */
@RestController
public class HelloController {
	
	@RequestMapping("/hello.json")
    public String index() {
        return "Hello World !";
    }
	
	

}
