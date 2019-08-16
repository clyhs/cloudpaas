/**
 * 
 */
package com.cloudpaas.common.exception;

import com.cloudpaas.common.utils.ErrorCode;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午12:40:22
 */
public class MultiDataSourceException extends BaseException {

	public MultiDataSourceException(String message){
		super(message,ErrorCode.DBEX.getCode());
	}
}
