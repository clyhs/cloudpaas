/**
 * 
 */
package com.cloudpaas.common.exception;

import com.cloudpaas.common.utils.ErrorCode;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午4:06:33
 */
public class UserInvalidException extends BaseException {

	public UserInvalidException(String message){
		super(message,ErrorCode.ACCOUNTEX.getCode());
	}
}
