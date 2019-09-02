/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.admin.ui.base.BaseController;
import com.cloudpaas.admin.ui.base.UISimpleController;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.system.biz.UserBiz;
import com.cloudpaas.admin.ui.utils.ShiroPassUtil;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.result.ObjectResponse;
import com.google.common.collect.Maps;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 上午10:42:03
 */
@Controller
@RequestMapping("user")
public class UserController extends UISimpleController<UserBiz,User>{
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/user";
	}
	
	@RequestMapping("/add.html")
	public String add(){
		return "admin/layui/system/userAdd";
	}
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		User entity = baseBiz.get(id, ApiConstants.API_USER_SINGLE_URL,params);
		modelMap.put("user", entity);
		return "admin/layui/system/userEdit";
	}
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	//@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<User> add(@RequestBody User user){
	
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		ObjectResponse result = new ObjectResponse();
		String salt = ShiroPassUtil.genSalt();
		user.setSalt(salt);
		String password = ShiroPassUtil.shiroEncryp2timeAndMd5(user.getPassword(), salt);
		user.setPassword(password);
		baseBiz.add(user, ApiConstants.API_USER_ADD_URL,params);
		
		return result;
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	//@CacheClear(prefix="UISC",pkg="true")
	public ObjectResponse<User> update(@RequestBody User entity){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		ObjectResponse<User> result= baseBiz.update(entity, entity.getId(), 
				ApiConstants.API_USER_SINGLE_URL,params);
		return result;
	}



	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#singleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_USER_SINGLE_URL;
	}

	
	

}
