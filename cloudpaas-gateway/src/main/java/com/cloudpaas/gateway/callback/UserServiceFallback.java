package com.cloudpaas.gateway.callback;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudpaas.common.jwt.IJWTInfo;
import com.cloudpaas.common.jwt.JWTInfo;
import com.cloudpaas.gateway.feign.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceFallback implements IUserService {/* (non-Javadoc)
	 * @see com.cloudpaas.gateway.feign.IUserService#getInfo(java.lang.String)
	 */
	@Override
	public JWTInfo getInfo(@RequestParam String token) {
		// TODO Auto-generated method stub
		log.debug("token:{}异常",token);
		return null;
	}
    
	
}
