/**
 * 
 */
package com.cloudpaas.admin.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.admin.biz.AdminMenuBiz;
import com.cloudpaas.admin.vo.AdminMenuTreeVo;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.controller.BaseController;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.common.utils.JSONUtil;
import com.cloudpaas.common.utils.TreeUtil;
import com.google.gson.Gson;

import io.swagger.annotations.Api;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午2:34:34
 */
@Api
@RestController
@RequestMapping("menu")
public class AdminMenuController extends BaseController<AdminMenuBiz,Menu> {

	//@Autowired
	//private AdminMenuDao adminMenuDao;
	
	
	
	@GetMapping("/tree.json")
	public List<AdminMenuTreeVo> getAdminMenuTree(
			@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db,
    		@RequestParam(value = "isShow" ,defaultValue = "1",required=false) Integer isShow){
		List<Menu> menus = new ArrayList<Menu>();
		Menu adminMenu = new Menu();
		adminMenu.setIsShow(isShow);
		menus = baseDao.selectList(adminMenu,db);
		return getMenuTree(menus,CommonConstants.ROOT);
	}
	
	private List<AdminMenuTreeVo> getMenuTree(List<Menu> menus,int root) {
        List<AdminMenuTreeVo> trees = new ArrayList<AdminMenuTreeVo>();
        AdminMenuTreeVo node = null;
        Gson json = new Gson();
        for (Menu menu : menus) {
            node = new AdminMenuTreeVo();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }
	
//	private void filterMenuTree(List<AdminMenuTreeVo> oldTrees,List<AdminMenuTreeVo> newTrees){
//		if(null!=oldTrees && oldTrees.size()>0){
//			for(AdminMenuTreeVo && )
//		}
//	}
	
	
}
