/**
 * 
 */
package com.cloudpaas.admin.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSONObject;
import com.cloudpaas.admin.model.JwtRequest;
import com.cloudpaas.common.model.User;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午5:16:31
 */
public class AuthControllerTest extends ParentControllerTest {

	
	@Test
    public void apiLoginTest()throws Exception{
    	
		JwtRequest user = new JwtRequest();
    	user.setUsername("admin");
    	
    	String requestJson = JSONObject.toJSONString(user);
    	
    	MvcResult mvcResult=mockMvc.perform(
    			MockMvcRequestBuilders.get("/auth/login.json")
    			.contentType(MediaType.APPLICATION_JSON).content(requestJson)
    			)
    			.andDo(MockMvcResultHandlers.print())
    			.andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                              
        System.out.println(status);
        TestCase.assertEquals(status, 200);
        //token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjEiLCJuYW1lIjoiYWRtaW4iLCJleHAiOjE1NjU5NTQ1MzR9.Q4MNbedU4_xy6XMAikkIdrwOZ4NerpnYF3PffyB3F4ZleQ1FdQ4WCu28F7dtYQZ5MfLJZUDqq-22VhDWHfmzduW7AWJFYERjGhegDrBnr5q4xOikK_VarQEYDz1OLJWaFAWC89x_uMVLKYeHK7oUhupJaT1lMc0zOvmOH14nntM
    }
}
