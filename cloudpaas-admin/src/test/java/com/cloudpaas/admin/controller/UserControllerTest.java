/**
 * 
 */
package com.cloudpaas.admin.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.cloudpaas.admin.AdminApplication;
import com.cloudpaas.common.model.User;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 上午8:57:19
 */

public class UserControllerTest extends ParentControllerTest {

	/**
	 * 测试查询单实例用户
	 * @throws Exception
	 */
    @Test
    public void apiUserSelectOneTest()throws Exception{
    	
    	User user = new User();
    	user.setUsername("admin");
    	
    	String requestJson = JSONObject.toJSONString(user);
    	
    	MvcResult mvcResult=mockMvc.perform(
    			MockMvcRequestBuilders.get("/user/selectOne.json")
    			.contentType(MediaType.APPLICATION_JSON).content(requestJson)
    			.param("db", "dn2")
    			)
    			.andDo(MockMvcResultHandlers.print())
    			.andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                              
        System.out.println(status);
        TestCase.assertEquals(status, 200);
    }
}
