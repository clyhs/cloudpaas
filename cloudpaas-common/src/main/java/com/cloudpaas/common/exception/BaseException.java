/**
 * 
 */
package com.cloudpaas.common.exception;

/**
 * @author 大鱼
 *
 * @date 2019年8月8日 下午6:22:32
 */
public class BaseException extends RuntimeException {
	
	private int code = 200;

   

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BaseException() {
    }

    public BaseException(String message,int code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
