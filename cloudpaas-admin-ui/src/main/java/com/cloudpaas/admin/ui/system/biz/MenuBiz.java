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
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月19日 下午2:49:41
 */
@Service
public class MenuBiz extends BaseBiz<Menu> {
	
	/**
	 * 根据id获取角色实体
	 * @param id
	 * @return
	 */
	public Menu getMenuByID(Integer id){
		ParameterizedTypeReference<ObjectRestResponse<Menu>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<Menu>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectRestResponse<Menu> result = RestTemplateUtils.exchange(ApiConstants.API_MENU_GET_URL+id, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		Menu entity =  result.getData();
		return entity;
	}

}
