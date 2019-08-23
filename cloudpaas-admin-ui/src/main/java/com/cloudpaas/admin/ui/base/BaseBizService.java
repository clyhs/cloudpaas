/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import java.util.List;
import java.util.Map;

import com.cloudpaas.common.result.ObjectRestResponse;
import com.cloudpaas.common.result.TableResultResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月23日 上午11:27:05
 */
public interface BaseBizService<T> {

	public ObjectRestResponse<T> add(T t,String url);
	
	public TableResultResponse<T> all(String url);
	
	public TableResultResponse<T> list(Map<String, Object> params,String url);
	
	public ObjectRestResponse<T> deleteBatch(List<T> lists,String url);
	
	public ObjectRestResponse<T> update(T t,Integer id,String url);
	
	public ObjectRestResponse<T> remove(Integer id,String url);
	
	public T get(Integer id,String url);
	
	public T get(T t,String url);
}
