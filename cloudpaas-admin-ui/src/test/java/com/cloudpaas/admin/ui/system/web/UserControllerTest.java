/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.utils.ShiroPassUtil;
import com.cloudpaas.common.model.User;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月20日 下午6:25:28
 */
public class UserControllerTest extends BaseControllerTest {

	@Test
    public void apiUserTest()throws Exception{
		User user = new User();
		user.setUsername("aaa");
		user.setName("测试");
		user.setAge(18);
		user.setSex(1);
		user.setTel("138");
		user.setPassword("123456");
		
		
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/user/add.json")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JSON.toJSONString(user)))
        		.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                              
        System.out.println(status);
        TestCase.assertEquals(status, 200);
    }
}
