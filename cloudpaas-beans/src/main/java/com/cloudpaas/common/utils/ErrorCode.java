/**
 * 
 */
package com.cloudpaas.common.utils;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午1:36:23
 */
public enum ErrorCode {
	
	SUCCESS(0,"操作成功"),
	
    ERROR(20001,"操作失败"),
    
    TIMEOUT(30001,"时间超时"),
    
    EXCEPTION(40001,"服务异常"),
    
    PARAMEX(40002,"参数错误"),
    
    ACCOUNTEX(40003,"用户不存在"),
    
    AUTHEX(40004,"该用户未授权或授权过期"),
	
	DBEX(40005,"数据库异常"),
	
	LOGINCODEEX(40006,"验证码错误"),
	
	LOGINPASSEX(40006,"密码错误"),
	
	LOGINLOCKEX(40006,"用户被锁定");
	
	
	
	private Integer code;
	
	private String  desc;
	
	private ErrorCode(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(Integer code){
		for(ErrorCode c:ErrorCode.values()){
			if(c.getCode() == code){
				return c.getDesc();
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
