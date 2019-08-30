/**
 * 
 */
package com.cloudpaas.service.pas.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.controller.BaseController;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.common.utils.Query;
import com.cloudpaas.service.pas.biz.PasGryjBiz;
import com.cloudpaas.service.pas.model.PasGryj;

import io.swagger.annotations.Api;

/**
 * 个人业绩接口类
 * 
 * @author 大鱼
 *
 * @date 2019年8月28日 上午11:07:12
 */
@Api(tags="gryj api",value = "/gryj", description = "个人业绩操作接口")
@RestController
@RequestMapping("gryj")
public class PasGryjController extends BaseController<PasGryjBiz,PasGryj> {
	

}
