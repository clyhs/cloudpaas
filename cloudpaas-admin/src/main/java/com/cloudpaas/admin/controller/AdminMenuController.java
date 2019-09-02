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

import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.common.utils.JSONUtil;
import com.cloudpaas.common.utils.TreeUtil;
import com.cloudpaas.plugin.mybatis.controller.BaseController;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午2:34:34
 */
@Api(tags="menu api",value = "/menu", description = "菜单操作接口")
@RestController
@RequestMapping("menu")
public class AdminMenuController extends BaseController<AdminMenuBiz,Menu> {

	//@Autowired
	//private AdminMenuDao adminMenuDao;
	
	
	@ApiOperation(value="获取菜单树接口",notes="默认获取要显示的菜单")
	@GetMapping("/tree.json")
	public List<AdminMenuTreeVo> getAdminMenuTree(
			@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db,
			@ApiParam(value = "是否显示", required = false) @RequestParam(value = "isShow" ,defaultValue = "1",required=false) Integer isShow){
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
