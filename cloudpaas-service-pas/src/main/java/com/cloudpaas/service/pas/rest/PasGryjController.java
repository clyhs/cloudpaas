/**
 * 
 */
package com.cloudpaas.service.pas.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.pas.model.PasGryj;
import com.cloudpaas.plugin.mybatis.rest.BaseController;
import com.cloudpaas.service.pas.biz.PasGryjBiz;

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
