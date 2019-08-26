/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

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
import com.cloudpaas.common.jwt.JwtResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月26日 下午3:58:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc  //测试接口用
public class AuthBizTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext context;
	@Autowired
	AuthBiz authBiz;
	
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
    public void loginTest(){
    	JwtResponse jr = authBiz.login("admin");
    	System.out.println(JSON.toJSONString(jr));
    }

}
