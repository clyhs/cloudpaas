/**
 * 
 */
package com.cloudpaas.common.exception;

import com.cloudpaas.common.utils.ErrorCode;

/**
 * @author 大鱼
 *
 * @date 2019年8月19日 下午12:35:36
 */
public class TokenInvalidException extends BaseException {

	public TokenInvalidException(String message){
		super(message,ErrorCode.TOKENEX.getCode());
	}
}
