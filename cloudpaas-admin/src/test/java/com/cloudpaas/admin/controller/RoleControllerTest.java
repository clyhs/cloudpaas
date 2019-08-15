/**
 * 
 */
package com.cloudpaas.admin.controller;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cloudpaas.admin.AdminApplication;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 上午8:56:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
@AutoConfigureMockMvc  //测试接口用

public class RoleControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext context;
	
	@Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();
	
	
	
	@Before
    public void testBefore(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        System.out.println("测试前");
       
        
    }

    @After
    public void testAfter(){
    	
        System.out.println("测试后");
        
    }
    
    /**
     * 性能测试：
     * 1：
     *  samples: 1000
	 *  max:     32297
	 *	average: 5337.858
	 *	median:  4093
	 * 2:
	 * samples: 1000
     * max:     36179
     * average: 5406.205
     * median:  4009
	 *
     * @throws Exception
     */
    @Test
    @PerfTest(invocations = 1000,threads = 100)
    public void apiPageTest()throws Exception{
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/role/page.json")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        int status=mvcResult.getResponse().getStatus();                              
        System.out.println(status);
        TestCase.assertEquals(status, 200);
    }
    @Test
    public void apiRoleTest()throws Exception{
    	MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/role/1")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        int status=mvcResult.getResponse().getStatus();                              
        System.out.println(status);
        TestCase.assertEquals(status, 200);
    }
}
