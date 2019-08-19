/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudpaas.admin.ui.base.BaseController;
import com.cloudpaas.admin.ui.system.biz.MenuBiz;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.Role;
import com.google.gson.Gson;
import com.sun.tools.javac.util.Log;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午6:58:36
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController{
	@Autowired
	private MenuBiz menuBiz;
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/menu";
	}
	
	@RequestMapping("/add.html")
	public String add(@RequestParam(value = "pId",required = false) String pId, ModelMap modelMap){
	    log.debug("menu pid:{}",pId);
		if(pId != null){
            Menu menu = menuBiz.getMenuByID(Integer.valueOf(pId));
            modelMap.put("parentMenu",menu);
        }
		
		return "admin/layui/system/menuAdd";
	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){

		return "admin/layui/system/menuEdit";
	}

}
