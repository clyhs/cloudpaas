/**
 * 
 */
package com.cloudpaas.demo.ui.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import lombok.extern.slf4j.Slf4j;



/**
 * @author 大鱼
 *
 * @date 2019年8月20日 下午6:20:43
 */
@Slf4j
public class ShiroPassUtil {
	
	public static String shiroEncryp(String password, String salt,String algorithmName,int times) {
		String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
		return encodedPassword;
	}
	
	public static String shiroEncryp2time(String password, String salt,String algorithmName) {
		return shiroEncryp(password,salt,algorithmName,2);
	}
	
	public static String shiroEncryp2timeAndMd5(String password, String salt) {
		return shiroEncryp(password,salt,"md5",2);
	}
	
	public static String shiroEncryp2timeAndMd5AndSalt(String password) {
		String salt = genSalt();
		return shiroEncryp(password,salt,"md5",2);
	}
	
	public static String genSalt(){
		return new SecureRandomNumberGenerator().nextBytes().toString();
	}

}
