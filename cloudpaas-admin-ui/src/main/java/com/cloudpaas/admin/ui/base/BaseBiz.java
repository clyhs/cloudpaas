/**
 * 
 */
package com.cloudpaas.admin.ui.base;

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
import com.cloudpaas.common.result.ObjectRestResponse;
import com.cloudpaas.common.result.TableResultResponse;
import com.cloudpaas.common.utils.JSONUtil;


/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午8:17:46
 */
public abstract class BaseBiz<T> {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	HttpHeaders headers;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	
	protected HttpHeaders getHttpHeaders(){
		headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	
	public ObjectRestResponse<T> add(T t,String url){
		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
		HttpEntity<T> httpEntity = new HttpEntity<>(t,getHttpHeaders());
		ObjectRestResponse<T> result = restTemplate.exchange(url, 
				HttpMethod.POST, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public TableResultResponse<T> all(String url){
		ParameterizedTypeReference<TableResultResponse<T>> responseBodyType = new ParameterizedTypeReference<TableResultResponse<T>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		TableResultResponse<T> result = restTemplate.exchange(url, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	public TableResultResponse<T> list(Map<String, Object> params,String url){
		ParameterizedTypeReference<TableResultResponse<T>> responseBodyType = new ParameterizedTypeReference<TableResultResponse<T>>() {};
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
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParams(urlparams);
		//解决中文转码问题
		UriComponents uriComponents = builder.build().encode();
		
		TableResultResponse<T> result = restTemplate.exchange(uriComponents.toUri(), 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 
	 * @param lists
	 * @return
	 */
	public ObjectRestResponse<T> deleteBatch(List<T> lists,String url){
		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
		HttpEntity<List<T>> httpEntity = new HttpEntity<>(lists,getHttpHeaders());
		ObjectRestResponse<T> result = restTemplate.exchange(url, 
				HttpMethod.DELETE, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 更新
	 * @param t
	 * @param id
	 * @return
	 */
	public ObjectRestResponse<T> update(T t,Integer id,String url){
		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
		HttpEntity<T> httpEntity = new HttpEntity<>(t,getHttpHeaders());
		ObjectRestResponse<T> result = restTemplate.exchange(url+id, 
				HttpMethod.PUT, httpEntity, responseBodyType)
				.getBody();
		return result;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public ObjectRestResponse<T> remove(Integer id,String url){
		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectRestResponse<T> result = restTemplate.exchange(url+id, 
				HttpMethod.DELETE, httpEntity, responseBodyType)
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
	public T get(Integer id,String url){
		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
		HttpEntity<String> httpEntity = new HttpEntity<>(getHttpHeaders());
		ObjectRestResponse<T> result = restTemplate.exchange(url+id, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
	
		T entity = (T) JSONUtil.parseObject(JSONUtil.toJson(result.getData()), getTClass()) ;
		return entity;
	}
	
	/**
	 * 获取对象
	 * selectOne.json
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(T t,String url){
		ParameterizedTypeReference<ObjectRestResponse<T>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<T>>() {};
		HttpEntity<T> httpEntity = new HttpEntity<>(t,getHttpHeaders());
		ObjectRestResponse<T> result = restTemplate.exchange(url, 
				HttpMethod.GET, httpEntity, responseBodyType)
				.getBody();
		T entity = (T) JSONUtil.parseObject(JSONUtil.toJson(result.getData()), getTClass()) ;
		return entity;
	}
	
	//public abstract String getEntityByIDUrl();
}
