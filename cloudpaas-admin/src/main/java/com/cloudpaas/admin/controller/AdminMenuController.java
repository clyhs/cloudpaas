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
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.admin.dao.AdminMenuDao;
import com.cloudpaas.admin.model.AdminMenu;
import com.cloudpaas.admin.vo.AdminMenuTreeVo;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.controller.BaseController;
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
public class AdminMenuController extends BaseController<AdminMenuDao,AdminMenu> {

	//@Autowired
	//private AdminMenuDao adminMenuDao;
	
	@GetMapping("/tree.json")
	public List<AdminMenuTreeVo> getAdminMenuTree(){
		List<AdminMenu> menus = new ArrayList<AdminMenu>();
		menus = baseDao.selectListAll(CommonConstants.DEFAULT_DATASOURCE_KEY);
		return getMenuTree(menus,CommonConstants.ROOT);
	}
	
	private List<AdminMenuTreeVo> getMenuTree(List<AdminMenu> menus,int root) {
        List<AdminMenuTreeVo> trees = new ArrayList<AdminMenuTreeVo>();
        
        AdminMenuTreeVo node = null;
        
        Gson json = new Gson();
        log.info(json.toJson(menus));
        for (AdminMenu menu : menus) {
            node = new AdminMenuTreeVo();
           
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }
	
	
}
