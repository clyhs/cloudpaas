package com.cloudpaas.admin.ui.exception;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月15日 下午5:37:06
 */
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String msg) {
		super(msg);
	}

	public UnauthorizedException() {
		super();
	}
}