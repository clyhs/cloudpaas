/**
 * 
 */
package com.cloudpaas.admin.ui.studio.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudpaas.admin.ui.studio.biz.StudioBiz;
import com.cloudpaas.common.table.FieldInfo;
import com.cloudpaas.common.table.TableInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 大鱼
 *
 * @date 2019年9月5日 上午11:28:12
 */
@Slf4j
@Controller
@RequestMapping("studio")
public class StudioController {
	
	@Autowired
	StudioBiz studioBiz;

	@RequestMapping("/index.html")
	public String index(ModelMap modelMap){
		modelMap.addAttribute("table", studioBiz.getTable());
		modelMap.addAttribute("buttons", studioBiz.getButtons());
		modelMap.addAttribute("templets", studioBiz.getTempletInfos());
		//cols();
		return "admin/studio/index";
	}
	@RequestMapping("/add.html")
	public String add(ModelMap modelMap){
		modelMap.addAttribute("form", studioBiz.getForm());
		return "admin/studio/add";
	}
	
	
	
}
