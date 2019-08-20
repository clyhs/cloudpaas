/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cloudpaas.admin.ui.AdminUIApplication;
import com.cloudpaas.admin.ui.system.biz.MenuBiz;

/**
 * @author 大鱼
 *
 * @date 2019年8月20日 下午6:25:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc  //测试接口用
public abstract class BaseControllerTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext context;

	
	@Before
    public void testBefore(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        System.out.println("测试前");   
    }

    @After
    public void testAfter(){
        System.out.println("测试后");   
    }

}
