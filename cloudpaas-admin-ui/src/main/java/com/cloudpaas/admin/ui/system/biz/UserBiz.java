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

//	public User getUser(String username,String password){
//		if(password.equals("4280d89a5a03f812751f504cc10ee8a5")){
//			User u = new User();
//			u.setName("admin");
//			u.setUsername("admin");
//			u.setPassword("4280d89a5a03f812751f504cc10ee8a5");
//			return u;
//		}
//		
//		return null;
//	}
//	
//	public User getUser(String username){
//		
//		User u = new User();
//		u.setName("admin");
//		u.setUsername("admin");
//		u.setPassword("4280d89a5a03f812751f504cc10ee8a5");
//		
//		
//		return u;
//	}
}
