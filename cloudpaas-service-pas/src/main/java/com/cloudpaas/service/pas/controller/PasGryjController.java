/**
 * 
 */
package com.cloudpaas.service.pas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudpaas.common.controller.BaseController;
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
@Api
@RestController
@RequestMapping("gryj")
public class PasGryjController extends BaseController<PasGryjBiz,PasGryj> {

}
