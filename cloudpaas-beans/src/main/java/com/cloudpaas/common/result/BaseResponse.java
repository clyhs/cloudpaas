package com.cloudpaas.common.result;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 下午2:38:27
 */
public class BaseResponse {
    private int code = 0;
    private String msg;

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse() {
    }

  

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

  


}
