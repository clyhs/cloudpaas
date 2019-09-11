/**
 * 
 */
package com.cloudpaas.demo.ui.pas.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudpaas.demo.ui.base.UISimpleController;
import com.cloudpaas.demo.ui.constants.ApiConstants;
import com.cloudpaas.demo.ui.pas.biz.GryjBiz;
import com.cloudpaas.pas.model.PasGryj;


/**
 * 个人业绩控制类
 * 
 * @author 大鱼
 *
 * @date 2019年8月28日 上午11:15:04
 */
@Controller
@RequestMapping("gryj")
public class GryjController extends UISimpleController<GryjBiz,PasGryj>{
	

	@RequestMapping("/index.html")
	public String index(){
		return "admin/pas/gryj";
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.UISimpleController#singleUrl()
	 */
	@Override
	public String singleUrl() {
		// TODO Auto-generated method stub
		return ApiConstants.API_GRYJ_SINGLE_URL;
	}

}
