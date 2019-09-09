/**
 * 
 */
package com.cloudpaas.admin.ui.setting.biz;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.AdminUIApplication;
import com.cloudpaas.admin.ui.system.biz.AuthBiz;
import com.cloudpaas.common.result.BaseResponse;
import com.cloudpaas.gateway.model.Router;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 下午4:20:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc 
public class GatewayRouterBizTest {
	
protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext context;
	@Autowired
	GatewayRouterBiz grBiz;
	
	@Before
    public void testBefore(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        System.out.println("测试前");   
    }

    @After
    public void testAfter(){
        System.out.println("测试后");   
    }
    @Test
    public void deleteRouter(){
    	BaseResponse r = grBiz.delete("cpaas-service-pas");
    	System.out.print(JSON.toJSONString(r));
    }

    @Test
    public void updateRouter(){
    	Router r = new Router();
		r.setRouterId("cpaas-service-pas");
		r.setUri("lb://CPAAS-SERVICE-PAS");
		r.setRouterOrder(8001);
		r.setPath("pas");
		r.setEnabled(1);
		r.setStripPrefix("2");
		r.setCreateTime(new Date());
		BaseResponse r1 = grBiz.update(r);
		System.out.print(JSON.toJSONString(r1));
    }
}
