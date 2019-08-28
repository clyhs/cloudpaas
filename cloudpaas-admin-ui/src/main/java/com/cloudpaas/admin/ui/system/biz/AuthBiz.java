/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.base.AbstractBaseBiz;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.jwt.JwtRequest;
import com.cloudpaas.common.jwt.JwtResponse;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月26日 下午3:52:31
 */
@Service
public class AuthBiz extends AbstractBaseBiz {
	
	public JwtResponse login(String username){
		ParameterizedTypeReference<JwtResponse> responseBodyType = new ParameterizedTypeReference<JwtResponse>() {};
		JwtRequest user = new JwtRequest();
		user.setUsername(username);
		HttpEntity<JwtRequest> httpEntity = new HttpEntity<>(user,getHttpHeaders());
		JwtResponse result =  restTemplate.exchange(getBaseUrl()+ApiConstants.API_AUTH_LOGIN_URL, 
				HttpMethod.GET, httpEntity, responseBodyType).getBody();
		log.info("---------jwtresponse:"+JSON.toJSONString(result));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.AbstractBaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.ADMIN_SERVICE_ID;
	}

}
