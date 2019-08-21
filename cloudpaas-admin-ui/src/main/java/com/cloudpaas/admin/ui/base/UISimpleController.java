/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;
import com.cloudpaas.common.result.TableResultResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月21日 下午1:59:22
 */
public abstract class UISimpleController<Biz extends BaseBiz<T>,T> extends BaseController {

	@Autowired
	protected Biz baseBiz;
	
	protected String addUrl = "add.json";
	protected String pageUrl = "page.json";
	protected String delBatchUrl = "deleteBatch.json";
	protected String allUrl = "all.json";
	protected String selectOneUrl = "selectOne.json";
	
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	public ObjectRestResponse<T> add(@RequestBody T entity){
		ObjectRestResponse<T> result= baseBiz.add(entity,singleUrl()+ addUrl);
		return result;
	}
	
	@RequestMapping(value="/page.json",method=RequestMethod.GET)
	@ResponseBody
	@Cacheable(value = "pageList", keyGenerator = "keyGenerator") 
	public TableResultResponse<T> page(@RequestParam Map<String, Object> params){
		TableResultResponse<T> result= baseBiz.list(params, singleUrl()+pageUrl);
		return result;
	}
	
	@RequestMapping(value="/all.json",method=RequestMethod.GET)
	@ResponseBody
	public TableResultResponse<T> page(){
		TableResultResponse<T> result= baseBiz.all(singleUrl()+allUrl);
		return result;
	}
	
	@RequestMapping(value="/delete.json",method=RequestMethod.DELETE)
	@ResponseBody
	public ObjectRestResponse<T> remove(@RequestParam Integer id){
		ObjectRestResponse<T> result= baseBiz.remove(id, singleUrl());
		return result;
	}
	
	@RequestMapping(value="/deleteBatch.json",method=RequestMethod.DELETE)
	@ResponseBody
	public ObjectRestResponse<T> remove(@RequestBody List<T> lists){
		ObjectRestResponse<T> result= baseBiz.deleteBatch(lists, singleUrl()+delBatchUrl);
		return result;
	}
	
	
	public abstract String singleUrl();

}
