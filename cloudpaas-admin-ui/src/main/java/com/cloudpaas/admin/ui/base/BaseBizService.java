/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import java.util.List;
import java.util.Map;

import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.result.PageResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月23日 上午11:27:05
 */
public interface BaseBizService<T> {

	public ObjectResponse<T> add(T t,String url);
	
	public PageResponse<T> all(String url);
	
	public PageResponse<T> list(Map<String, Object> params,String url);
	
	public ObjectResponse<T> deleteBatch(List<T> lists,String url);
	
	public ObjectResponse<T> update(T t,Integer id,String url);
	
	public ObjectResponse<T> remove(Integer id,String url);
	
	public T get(Integer id,String url);
	
	public T get(T t,String url);
}
