/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

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
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.common.result.ObjectRestResponse;

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
		User user = baseBiz.get(id, ApiConstants.API_USER_SINGLE_URL);
		modelMap.put("user", user);
		return "admin/layui/system/userEdit";
	}
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	public ObjectRestResponse<User> add(@RequestBody User user){
		ObjectRestResponse result = new ObjectRestResponse();
		String salt = ShiroPassUtil.genSalt();
		user.setSalt(salt);
		String password = ShiroPassUtil.shiroEncryp2timeAndMd5(user.getPassword(), salt);
		user.setPassword(password);
		baseBiz.add(user, ApiConstants.API_USER_ADD_URL);
		
		return result;
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	public ObjectRestResponse<User> update(@RequestBody User entity){
		ObjectRestResponse<User> result= baseBiz.update(entity, entity.getId(), ApiConstants.API_USER_SINGLE_URL);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.BaseController#pageUrl()
	 */
	@Override
	public String pageUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_USER_PAGE_URL;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#singleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_USER_SINGLE_URL;
	}

	@Override
	public String delBatchUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_USER_DELBATCH_URL;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#allUrl()
	 */
	@Override
	public String allUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_USER_ALL_URL;
	}
	
	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#addUrl()
	 */
	@Override
	public String addUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_USER_ADD_URL;
	}

}
