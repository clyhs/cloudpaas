/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午2:53:55
 */
@Controller
public class LoginController {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
    private Producer captchaProducer = null;
	
    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
	
	@RequestMapping("/login.html")
	public String index(){
		return "admin/layui/login";
	}
	
	@RequestMapping("/403.html")
	public String for403(){
		return "admin/layui/403";
	}
	
//	@RequestMapping("/redis.json")
//	@ResponseBody
//	public String test(){
//		redisTemplate.opsForValue().set("springboot", "hello admin");
//		return (String) redisTemplate.opsForValue().get("springboot");
//	}
	
	

}
