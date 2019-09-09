/**
 * 
 */
package com.cloudpaas.admin.ui.setting.biz;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.base.AbstractBaseBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.gateway.model.Router;
import com.cloudpaas.plugin.redis.anno.CacheClear;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 下午3:50:37
 */
@Service
public class GatewayRouterBiz extends AbstractBaseBiz {
	
	/**
	 * 添加路由
	 * @param router
	 * @return
	 */
	public BaseResponse add(Router router){
		ParameterizedTypeReference<BaseResponse> responseBodyType = new ParameterizedTypeReference<BaseResponse>() {};
		
		//log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<Router> httpEntity = new HttpEntity<>(router,getHttpHeaders());
		BaseResponse result = restTemplate.exchange(auiProp.getApiUrl()+"/router/add.json", 
				HttpMethod.POST, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	public BaseResponse update(Router router){
		ParameterizedTypeReference<BaseResponse> responseBodyType = new ParameterizedTypeReference<BaseResponse>() {};
		
		//log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<Router> httpEntity = new HttpEntity<>(router,getHttpHeaders());
		BaseResponse result = restTemplate.exchange(auiProp.getApiUrl()+"/router/update.json", 
				HttpMethod.POST, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	public BaseResponse delete(String routerId){
		ParameterizedTypeReference<BaseResponse> responseBodyType = new ParameterizedTypeReference<BaseResponse>() {};
		
		//log.info("params:{}",JSON.toJSONString(params));
		log.info(auiProp.getApiUrl());
		HttpEntity<Router> httpEntity = new HttpEntity<>(getHttpHeaders());
		BaseResponse result = restTemplate.exchange(auiProp.getApiUrl()+"/router/"+routerId, 
				HttpMethod.DELETE, httpEntity, responseBodyType)
				.getBody();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.AbstractBaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.GATEWAY_SERVICE_ID;
	}

}
