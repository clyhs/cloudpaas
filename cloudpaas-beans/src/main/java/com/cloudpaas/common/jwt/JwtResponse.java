/**
 * 
 */
package com.cloudpaas.common.jwt;

import java.io.Serializable;

import com.cloudpaas.common.model.User;

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
	
	private User   user;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
