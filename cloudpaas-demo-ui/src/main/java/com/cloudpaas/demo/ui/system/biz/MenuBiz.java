/**
 * 
 */
package com.cloudpaas.demo.ui.system.biz;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.vo.MenuTreeVo;
import com.cloudpaas.demo.ui.base.BaseBiz;
import com.cloudpaas.demo.ui.constants.ApiConstants;
import com.cloudpaas.demo.ui.utils.RestTemplateUtils;

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
		ParameterizedTypeReference<ObjectResponse<Menu>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<Menu>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectResponse<Menu> result = restTemplate.exchange(getBaseUrl()+ApiConstants.API_MENU_SINGLE_URL+id, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		Menu entity =  result.getData();
		return entity;
	}
	//@CacheWrite(prefix="MENU",key="tree")
	public List<MenuTreeVo> getMenuTree(){
		ParameterizedTypeReference<List<MenuTreeVo>> responseBodyType = new ParameterizedTypeReference<List<MenuTreeVo>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		log.info("menu api url:"+getBaseUrl()+ApiConstants.API_MENU_TREE_URL);
		List<MenuTreeVo> result = restTemplate.exchange(getBaseUrl()+ApiConstants.API_MENU_TREE_URL, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.BaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.ADMIN_SERVICE_ID;
	}

}
