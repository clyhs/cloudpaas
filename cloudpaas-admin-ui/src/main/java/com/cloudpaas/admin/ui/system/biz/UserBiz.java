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
import com.cloudpaas.common.result.ObjectRestResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午5:20:40
 */
@Service
public class UserBiz extends BaseBiz<User>{
	
	
	public User findUser(String username){
		ParameterizedTypeReference<ObjectRestResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<User>>() {};
		User user = new User();
		user.setUsername(username);
		HttpEntity<User> httpEntity = new HttpEntity<>(user,getHttpHeaders());
		ObjectRestResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_SELECT_URL, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		User entity =  result.getData();
		return entity;
	}
	
	public ObjectRestResponse addUser(User user){
		ParameterizedTypeReference<ObjectRestResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<User>>() {};
		HttpEntity<User> httpEntity = new HttpEntity<>(user,getHttpHeaders());
		ObjectRestResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_ADD_URL, 
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
		ParameterizedTypeReference<ObjectRestResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<User>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectRestResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_GET_URL+id, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		User entity =  result.getData();
		return entity;
	}


}
