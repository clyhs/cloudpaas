package com.cloudpaas.common.result;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 下午2:38:27
 */
public class BaseResponse {
    private int code = 200;
    private String message;

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

  


}
