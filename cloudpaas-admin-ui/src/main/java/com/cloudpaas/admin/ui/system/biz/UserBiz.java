/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.cloudpaas.admin.ui.base.BaseBiz;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.utils.RestTemplateUtils;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午5:20:40
 */
@Service
public class UserBiz extends BaseBiz<User>{
	
	
	public User findUser(String username){
		ParameterizedTypeReference<ObjectResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<User>>() {};
		User user = new User();
		user.setUsername(username);
		HttpEntity<User> httpEntity = new HttpEntity<>(user,getHttpHeaders());
		ObjectResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_SELECTONE_URL, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		User entity =  result.getData();
		return entity;
	}
	
	public ObjectResponse addUser(User user){
		ParameterizedTypeReference<ObjectResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<User>>() {};
		HttpEntity<User> httpEntity = new HttpEntity<>(user,getHttpHeaders());
		ObjectResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_ADD_URL, 
				HttpMethod.POST, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 根据id获取用户实体
	 * @param id
	 * @return
	 */
	public User getUserByID(Integer id){
		ParameterizedTypeReference<ObjectResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<User>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_SINGLE_URL+id, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		User entity =  result.getData();
		return entity;
	}


}
