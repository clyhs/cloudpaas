/**
 * 
 */
package com.cloudpaas.admin.ui.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大鱼
 *
 * @date 2019年9月5日 上午11:18:58
 */
@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 401){
            return "admin/401";
        }else if(statusCode == 404){
            return "admin/404";
        }else if(statusCode == 403){
            return "admin/403";
        }else{
            return "admin/500";
        }

    }
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
