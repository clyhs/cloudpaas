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
import com.cloudpaas.admin.ui.system.biz.UserBiz;
import com.cloudpaas.admin.ui.utils.ShiroPassUtil;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.BaseResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 上午10:42:03
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	@Autowired
	UserBiz userBiz;
	
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
		User user = userBiz.getUserByID(id);
		modelMap.put("user", user);
		return "admin/layui/system/userEdit";
	}
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(@RequestBody User user){
		BaseResponse result = new BaseResponse();
		String salt = ShiroPassUtil.genSalt();
		user.setSalt(salt);
		String password = ShiroPassUtil.shiroEncryp2timeAndMd5(user.getPassword(), salt);
		user.setPassword(password);
		userBiz.addUser(user);
		
		return result;
	}

}
