/**
 * 
 */
package com.cloudpaas.demo.ui.base;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.common.utils.JSONUtil;
import com.cloudpaas.demo.ui.prop.UIProperites;
import com.cloudpaas.plugin.redis.anno.CacheClear;
import com.cloudpaas.plugin.redis.anno.CacheWrite;
import com.cloudpaas.plugin.redis.anno.IgnoreField;
import com.google.common.collect.Maps;


/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午8:17:46
 */
public abstract class BaseBiz<T> extends AbstractBaseBiz implements IBaseBiz<T>{
	

	
	/**
	 * 取得T的clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	@CacheClear(prefix="BASEBIZ",pkg="true")
	public ObjectResponse<T> add(T t,String url,Map<String, Object> params){
		ParameterizedTypeReference<ObjectResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<T>>() {};
		
		log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<T> httpEntity = new HttpEntity<>(t,getHttpHeaders());
		ObjectResponse<T> result = restTemplate.exchange(getBaseUrl()+url, 
				HttpMethod.POST, httpEntity, responseBodyType,params)
				.getBody();
		return result;
	}
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public PageResponse<T> all(String url,Map<String, Object> params){
		ParameterizedTypeReference<PageResponse<T>> responseBodyType = new ParameterizedTypeReference<PageResponse<T>>() {};
	
		log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		PageResponse<T> result = restTemplate.exchange(getBaseUrl()+url, 
				HttpMethod.GET, httpEntity, responseBodyType,params)
				.getBody();
		return result;
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	@CacheWrite(prefix="BASEBIZ",pkg="true")
	public PageResponse<T> list(Map<String, Object> params,@IgnoreField String url){
		ParameterizedTypeReference<PageResponse<T>> responseBodyType = new ParameterizedTypeReference<PageResponse<T>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		
		MultiValueMap<String,String> urlparams = new LinkedMultiValueMap<>();
		if(null!=params){
			Iterator it=params.keySet().iterator();
			while(it.hasNext()){
				String key=(String) it.next();
				String value=(String) params.get(key);
				urlparams.add(key, value);
			}
			
		}

		log.info("params:{}",JSON.toJSONString(urlparams));
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBaseUrl()+url).queryParams(urlparams);
		//解决中文转码问题
		UriComponents uriComponents = builder.build().encode();
		
		PageResponse<T> result = restTemplate.exchange(uriComponents.toUri(), 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 
	 * @param lists
	 * @return
	 */
	@CacheClear(prefix="BASEBIZ",pkg="true")
	public ObjectResponse<T> deleteBatch(List<T> lists,String url,Map<String, Object> params){
		ParameterizedTypeReference<ObjectResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<T>>() {};

		log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<List<T>> httpEntity = new HttpEntity<>(lists,getHttpHeaders());
		ObjectResponse<T> result = restTemplate.exchange(auiProp.getApiUrl()+url, 
				HttpMethod.DELETE, httpEntity, responseBodyType,params)
				.getBody();
		return result;
	}
	
	/**
	 * 更新
	 * @param t
	 * @param id
	 * @return
	 */
	//@CacheClear(prefix="BBIZ",key="'id_'+#t.id" ,model="true")
	@CacheClear(prefix="BASEBIZ",pkg="true")
	public ObjectResponse<T> update(T t,Integer id,String url,Map<String, Object> params){
		ParameterizedTypeReference<ObjectResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<T>>() {};

		log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<T> httpEntity = new HttpEntity<>(t,getHttpHeaders());
		ObjectResponse<T> result = restTemplate.exchange(getBaseUrl()+url+id, 
				HttpMethod.PUT, httpEntity, responseBodyType,params)
				.getBody();
		return result;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	//@CacheClear(prefix="BBIZ",key="'id_'+#id" ,model="true")
	@CacheClear(prefix="BASEBIZ",pkg="true")
	public ObjectResponse<T> remove(Integer id,String url,Map<String, Object> params){
		ParameterizedTypeReference<ObjectResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<T>>() {};

		log.info("params:{}",JSON.toJSONString(params));
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectResponse<T> result = restTemplate.exchange(getBaseUrl()+url+id, 
				HttpMethod.DELETE, httpEntity, responseBodyType,params)
				.getBody();
		return result;
	}
	
	/**
	 * 获取对象
	 * {id}
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	//@CacheWrite(prefix="BBIZ",key="'id_'+#id" ,model="true")
	public T get(Integer id,String url,Map<String, Object> params){
		ParameterizedTypeReference<ObjectResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<T>>() {};

		log.info("params:{}",JSON.toJSONString(params));
		
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectResponse<T> result = restTemplate.exchange(getBaseUrl()+url+id, 
				HttpMethod.GET, httpEntity, responseBodyType,params)
				.getBody();
	
		T entity = JSON.parseObject( JSON.toJSONString(result.getData()) ,getTClass());
		return entity;
	}
	
	/**
	 * 获取对象
	 * selectOne.json
	 * @param t
	 * @return
	 * 
	 * model:true
	 * key:BBIZ:com.cloudpaas.common.model.Role_id_1
	 */
	@SuppressWarnings("unchecked")
	//@CacheWrite(prefix="BBIZ",key="'id_'+#t.id" ,model="true")
	public T get(T t,String url,Map<String, Object> params){
		ParameterizedTypeReference<ObjectResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<T>>() {};
		HttpEntity<T> httpEntity = new HttpEntity<>(t,getHttpHeaders());
		
		log.info("params:{}",JSON.toJSONString(params));
		ObjectResponse<T> result = restTemplate.exchange(getBaseUrl()+url, 
				HttpMethod.GET, httpEntity, responseBodyType,params)
				.getBody();
		//这里没法对对象进行自动转换，借助JSONUtil将map转为T
		T entity = JSON.parseObject( JSON.toJSONString(result.getData()) ,getTClass());
		return entity;
	}
	
	
	//public abstract String getEntityByIDUrl();
}
