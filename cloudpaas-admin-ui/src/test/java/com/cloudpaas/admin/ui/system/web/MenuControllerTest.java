/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cloudpaas.admin.ui.AdminUIApplication;
import com.cloudpaas.admin.ui.system.biz.MenuBiz;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月20日 上午11:27:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc  //测试接口用
public class MenuControllerTest {

protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext context;
	
	@Autowired
	private   MenuBiz menuBiz;

	
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
    public void apiTreeTest()throws Exception{
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/menu/tree.json")).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                              
        System.out.println(status);
        TestCase.assertEquals(status, 200);
    }
    
}
