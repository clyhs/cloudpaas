/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.result.PageResponse;
import com.google.common.collect.Maps;

/**
 * 对每个继续默认实现增删查。
 * 
 * @author 大鱼
 *
 * @date 2019年8月21日 下午1:59:22
 */
public abstract class UISimpleController<Biz extends IBaseBiz<T>,T> extends BaseController {

	@Autowired
	private Biz baseBiz;
	
	protected String addUrl = "add.json";
	protected String pageUrl = "page.json";
	protected String delBatchUrl = "deleteBatch.json";
	protected String allUrl = "all.json";
	protected String selectOneUrl = "selectOne.json";

	protected Biz getBaseBiz(){
		setTokenHeader();
		return baseBiz;
	}
	
	
	/**
	 * 添加对象
	 * @param entity
	 * @return
	 * 
	 * 自动清除缓存key：UISC:com.cloudpaas.admin.ui.system.web.RoleController*
	 * 批量清除
	 */
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	//@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<T> add(@RequestBody T entity){
		User user = getLogin();
		Map<String, Object> params = Maps.newHashMap();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		
		ObjectResponse<T> result= getBaseBiz().add(entity,singleUrl()+ addUrl,params);
		return result;
	}
	/**
	 * 分页查询
	 * @param params
	 * @return
	 * 
	 * 自动生成缓存key:UISC:com.cloudpaas.admin.ui.system.web.RoleController.page({page=1, limit=15})
	 * 自动获取参数
	 */
	@RequestMapping(value="/page.json",method=RequestMethod.GET)
	@ResponseBody
	//@Cacheable(value = "pageList", keyGenerator = "keyGenerator") 
	//@CacheWrite(key="'page_'+#params+'_'+#db+'_'+#id+'_'+#price")
	//@CacheWrite(prefix="UISC",pkg="true")
	public PageResponse<T> page(@RequestParam Map<String, Object> params/*,
			@RequestParam(value="db",defaultValue="db1",required=false) String db,
			@RequestParam(value="id",defaultValue="1",required=false) Integer id,
			@RequestParam(value="price",defaultValue="1.0",required=false) float  price*/){
		User user = getLogin();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		
		PageResponse<T> result= getBaseBiz().list(params, singleUrl()+pageUrl);
		return result;
	}
	
	@RequestMapping(value="/all.json",method=RequestMethod.GET)
	@ResponseBody
	public PageResponse<T> page(){
		User user = getLogin();
		Map<String, Object> params = Maps.newHashMap();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		
		PageResponse<T> result= getBaseBiz().all(singleUrl()+allUrl,params);
		return result;
	}
	
	@RequestMapping(value="/delete.json",method=RequestMethod.DELETE)
	@ResponseBody
	//@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<T> remove(@RequestParam Integer id){
		User user = getLogin();
		Map<String, Object> params = Maps.newHashMap();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		
		ObjectResponse<T> result= getBaseBiz().remove(id, singleUrl(),params);
		return result;
	}
	
	@RequestMapping(value="/deleteBatch.json",method=RequestMethod.DELETE)
	@ResponseBody
	//@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<T> remove(@RequestBody List<T> lists){
		User user = getLogin();
		Map<String, Object> params = Maps.newHashMap();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		
		ObjectResponse<T> result= getBaseBiz().deleteBatch(lists, singleUrl()+delBatchUrl,params);
		return result;
	}
	
	public abstract String singleUrl();
	
	private void setTokenHeader(){
		Map<String,String> headers = new HashMap<>();
		headers.put(CommonConstants.TOKEN_HEADER_KEY, getToken());
		log.info("token:{}",getToken());
		baseBiz.setHttpHeaders(headers);
	}

}
