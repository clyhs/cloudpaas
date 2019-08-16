/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.cloudpaas.admin.ui.system.biz.RoleBiz;
import com.cloudpaas.admin.ui.utils.RestTemplateUtils;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午8:17:46
 */
public abstract class BaseBiz<T> {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	HttpHeaders headers;
	
	
	protected HttpHeaders getHttpHeaders(){
		headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");
        headers.add("Accept", "application/json");
        return headers;
	}
	
//	public <T> T getEntityByID(Integer id){
//		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
//		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
//		ObjectRestResponse<T> result = RestTemplateUtils.exchange(getEntityByIDUrl()+id, 
//				HttpMethod.GET, httpEntity, responseBodyType)
//				.getBody();
//		T entity = (T) result.getData();
//		return entity;
//	}
	
	//public abstract String getEntityByIDUrl();
}
