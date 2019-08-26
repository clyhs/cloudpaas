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

import com.cloudpaas.admin.ui.base.BaseBiz;
import com.cloudpaas.admin.ui.base.IBaseBiz;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.constants.CommonConstants;
import com.cloudpaas.admin.ui.system.web.RoleController;
import com.cloudpaas.admin.ui.utils.RestTemplateUtils;
import com.cloudpaas.cache.anno.CacheWrite;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectResponse;
import com.google.gson.Gson;

/**
 * 角色业务类
 * 
 * @author 大鱼
 *
 * @date 2019年8月14日 下午6:57:19
 */
@Service
public class RoleBiz extends BaseBiz<Role>{
	
	/**
	 * 根据id获取角色实体
	 * @param id
	 * @return
	 */
	public Role getRoleByID(Integer id){
		ParameterizedTypeReference<ObjectResponse<Role>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<Role>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectResponse<Role> result = restTemplate.exchange(getBaseUrl()+ApiConstants.API_ROLE_SINGLE_URL+id, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		Role entity =  result.getData();
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.BaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.ADMIN_SERVICE_ID;
	}


//	/**
//	 * 
//	 * @param t
//	 * @return
//	 */
//	@CacheWrite(key="'base_'+#t.id")
//	public Role get(Role t) {
//		// TODO Auto-generated method stub
//		return super.get(t, ApiConstants.API_ROLE_SELECTONE_URL);
//	}

	

	
	
	

}
