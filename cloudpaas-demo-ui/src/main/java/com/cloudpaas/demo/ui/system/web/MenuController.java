/**
 * 
 */
package com.cloudpaas.demo.ui.system.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.vo.MenuTreeVo;
import com.cloudpaas.common.vo.TreeVo;
import com.cloudpaas.demo.ui.base.BaseController;
import com.cloudpaas.demo.ui.base.UISimpleController;
import com.cloudpaas.demo.ui.constants.ApiConstants;
import com.cloudpaas.demo.ui.system.biz.MenuBiz;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sun.tools.javac.util.Log;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午6:58:36
 */
@Controller
@RequestMapping("menu")
public class MenuController extends UISimpleController<MenuBiz,Menu>{

	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/system/menu";
	}
	
	@RequestMapping("/tree.json")
	@ResponseBody
	public List<MenuTreeVo> getMenuTrees(){
		List<MenuTreeVo> trees = new ArrayList<MenuTreeVo>();
		trees = getBaseBiz().getMenuTree();
		return trees;
	}
	
	@RequestMapping("/add.html")
	public String add(@RequestParam(value = "pId",required = false) String pId, ModelMap modelMap){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		if(pId != null){
            Menu menu = getBaseBiz().get(Integer.valueOf(pId), ApiConstants.API_MENU_SINGLE_URL,params);
            modelMap.put("parentMenu",menu);
        }
		return "admin/system/menuAdd";
	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		Menu menu = getBaseBiz().get(id, ApiConstants.API_MENU_SINGLE_URL,params);
		modelMap.put("menu", menu);
		return "admin/system/menuEdit";
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	public ObjectResponse<Menu> update(@RequestBody Menu entity){
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", CommonConstants.DEFAULT_DATASOURCE_KEY);
		ObjectResponse<Menu> result= getBaseBiz().update(entity, entity.getId(), 
				ApiConstants.API_MENU_SINGLE_URL,params);
		return result;
	}


	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#singleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_MENU_SINGLE_URL;
	}



}
