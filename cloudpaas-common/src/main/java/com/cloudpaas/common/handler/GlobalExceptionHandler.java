/**
 * 
 */
package com.cloudpaas.common.handler;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.common.exception.MultiDataSourceException;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.utils.ErrorCode;

/**
 * 全局异常拦截类
 * 
 * @author 大鱼
 *
 * @date 2019年8月8日 下午6:45:06
 */
@ControllerAdvice("com.cloudpaas")
@ResponseBody
public class GlobalExceptionHandler {

	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MultiDataSourceException.class)
    public BaseResponse userInvalidExceptionHandler(HttpServletResponse response, MultiDataSourceException ex) {
        response.setStatus(ErrorCode.DBEX.getCode());
        log.error(ex.getMessage(),ex);
        return new BaseResponse(ErrorCode.DBEX.getCode(), ex.getMessage());
    }

	@ExceptionHandler(Exception.class)
	public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
		response.setStatus(500);
		log.error(ex.getMessage(), ex);
		return new BaseResponse(500, ex.getMessage());
	}
}
