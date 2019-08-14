/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloudpaas.admin.ui.system.web.RoleController;
import com.cloudpaas.admin.ui.utils.RestTemplateUtils;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;
import com.google.gson.Gson;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午6:57:19
 */
@Service
public class RoleBiz {
	
	private static Logger log = LoggerFactory.getLogger(RoleBiz.class);
	
	public Role getRoleByID(Integer id){
		
		ParameterizedTypeReference<ObjectRestResponse<Role>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<Role>>() {};
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
       
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
		ObjectRestResponse<Role> result = RestTemplateUtils.exchange("http://localhost:8200/api/role/"+id, HttpMethod.GET, httpEntity, responseBodyType).getBody();
		//ResponseEntity<ObjectRestResponse> result = RestTemplateUtils.get("http://localhost:8200/api/role/"+id, ObjectRestResponse.class);
		
		Gson g = new Gson();
		log.info(g.toJson(result));
		
		Role role = (Role) result.getData();
		return role;
	}

}
