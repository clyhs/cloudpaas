/**
 * 
 */
package com.cloudpaas.admin.model;

import java.io.Serializable;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午3:53:49
 */
public class JwtResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
