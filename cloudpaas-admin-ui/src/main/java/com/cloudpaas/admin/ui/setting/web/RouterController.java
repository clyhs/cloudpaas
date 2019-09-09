/**
 * 
 */
package com.cloudpaas.admin.ui.setting.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.admin.ui.base.UISimpleController;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.setting.biz.GatewayRouterBiz;
import com.cloudpaas.admin.ui.setting.biz.RouterBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.gateway.model.Router;
import com.cloudpaas.plugin.redis.anno.CacheClear;
import com.google.common.collect.Maps;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午11:48:24
 */
@Controller
@RequestMapping("router")
public class RouterController extends UISimpleController<RouterBiz, Router> {
	@Autowired
	private GatewayRouterBiz gatewayRouterBiz;
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/setting/router";
	}
	
	@RequestMapping("/add.html")
	public String add(){
		return "admin/setting/routerAdd";
	}
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	//@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<Router> add(@RequestBody Router entity){
		User user = getLogin();
		Map<String, Object> params = Maps.newHashMap();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		ObjectResponse<Router> result= baseBiz.add(entity,singleUrl()+ addUrl,params);
		gatewayRouterBiz.add(entity);
		return result;
	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		Router rp = new Router();
		rp.setId(id);
		Router router =baseBiz.get(rp, ApiConstants.API_ROUTER_SELECTONE_URL,params);
		modelMap.put("router", router);
		return "admin/setting/routerEdit";
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	public ObjectResponse<Router> update(@RequestBody Router entity){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		ObjectResponse<Router> result= baseBiz.update(entity, entity.getId(), 
				ApiConstants.API_ROUTER_SELECTONE_URL,params);
		
		gatewayRouterBiz.update(entity);
		
		return result;
	}
	
	@RequestMapping(value="/delete2.json",method=RequestMethod.DELETE)
	@ResponseBody
	public ObjectResponse<Router> remove(@RequestParam Integer id,@RequestParam String routerId){
		User user = getLogin();
		Map<String, Object> params = Maps.newHashMap();
		if(null!=user){
			params.put("db", user.getCorp().getCorpdbcode());
		}
		ObjectResponse<Router> result= baseBiz.remove(id, singleUrl(),params);

		gatewayRouterBiz.delete(routerId);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#singleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_ROUTER_SINGLE_URL;
	}

}
