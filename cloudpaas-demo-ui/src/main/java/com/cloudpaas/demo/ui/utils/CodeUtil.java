package com.cloudpaas.demo.ui.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 验证码对比类
 * 
 * @author 大鱼
 *
 * @date 2019年8月15日 下午3:07:20
 */
public class CodeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CodeUtil.class);
    /**
     * 将获取到的前端参数转为string类型
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if(result != null) {
                result = result.trim();
            }
            if("".equals(result)) {
                result = null;
            }
            return result;
        }catch(Exception e) {
            return null;
        }
    }
    /**
     * 验证码校验
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //获取用户输入的验证码
        String verifyCodeActual = CodeUtil.getString(request, "captcha");
        
        log.info("-----verifyCodeActual:{},verifyCodeExpected:{}-----", verifyCodeActual, verifyCodeExpected);
        if(verifyCodeActual == null ||!verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}