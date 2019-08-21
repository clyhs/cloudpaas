/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.base.BaseController;
import com.cloudpaas.admin.ui.base.UISimpleController;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.system.biz.MenuBiz;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;
import com.cloudpaas.common.vo.MenuTreeVo;
import com.cloudpaas.common.vo.TreeVo;
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
	@Autowired
	private MenuBiz menuBiz;
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/menu";
	}
	
	@RequestMapping("/tree.json")
	@ResponseBody
	public List<MenuTreeVo> getMenuTrees(){
		List<MenuTreeVo> trees = new ArrayList<MenuTreeVo>();
		trees = menuBiz.getMenuTree();
		return trees;
	}
	
	@RequestMapping("/add.html")
	public String add(@RequestParam(value = "pId",required = false) String pId, ModelMap modelMap){
	    log.debug("menu pid:{}",pId);
		if(pId != null){
            Menu menu = menuBiz.get(Integer.valueOf(pId), ApiConstants.API_MENU_SINGLE_URL);
            modelMap.put("parentMenu",menu);
        }
		return "admin/layui/system/menuAdd";
	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Menu menu = menuBiz.get(id, ApiConstants.API_MENU_SINGLE_URL);
		modelMap.put("menu", menu);
		return "admin/layui/system/menuEdit";
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	public ObjectRestResponse<Menu> update(@RequestBody Menu entity){
		ObjectRestResponse<Menu> result= baseBiz.update(entity, entity.getId(), ApiConstants.API_MENU_SINGLE_URL);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.BaseController#pageUrl()
	 */
	@Override
	public String pageUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_MENU_PAGE_URL;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#singleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_MENU_SINGLE_URL;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#delBatchUrl()
	 */
	@Override
	public String delBatchUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_MENU_DELBATCH_URL;
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#allUrl()
	 */
	@Override
	public String allUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_MENU_ALL_URL;
	}
	
	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#addUrl()
	 */
	@Override
	public String addUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_MENU_ADD_URL;
	}

}
