/**
 * 
 */
package com.cloudpaas.common.jwt;

import java.io.Serializable;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午3:52:22
 */
public class JwtRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
