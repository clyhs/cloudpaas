/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;
import com.cloudpaas.common.result.TableResultResponse;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月21日 上午8:36:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc  //测试接口用
public class RoleBizTest {
	
	protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext context;
	
	@Autowired
	private RoleBiz roleBiz;
	
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
    public void RoleByIdTest(){
    	Role r = roleBiz.get(1,ApiConstants.API_ROLE_SINGLE_URL);
    	System.out.println(JSON.toJSONString(r));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    @Test
    public void RoleByEntityTest(){
    	Role r = new Role();
    	r.setId(2);
    	Role r1 = roleBiz.get(r,ApiConstants.API_ROLE_SELECTONE_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    @Test
    public void RoleRemoveTest(){
    	
    	ObjectRestResponse<Role> r1 = roleBiz.remove(4,ApiConstants.API_ROLE_SINGLE_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    
    @Test
    public void RoleUpdateTest(){
    	Role r = new Role();
    	r.setId(5);
    	r.setCode("ROLE_TEST111");
    	ObjectRestResponse<Role> r1 = roleBiz.update(r, r.getId(),ApiConstants.API_ROLE_SINGLE_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    @Test
    public void RoleDelBatchTest(){
    	Role r = new Role();
    	r.setId(5);
    	Role r2 = new Role();
    	r2.setId(4);
    	List<Role> lists = new ArrayList<>();
    	lists.add(r);
    	lists.add(r2);
    	ObjectRestResponse<Role> r1 = roleBiz.deleteBatch(lists,ApiConstants.API_ROLE_DELBATCH_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    
    @Test
    public void RolePageTest(){
    	Map<String, Object> params = new HashMap<>();
    	params.put("name", "管理员");
    	TableResultResponse<Role> r1 = roleBiz.list(params,ApiConstants.API_ROLE_PAGE_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    @Test
    public void RoleALLTest(){
    	
    	TableResultResponse<Role> r1 = roleBiz.all(ApiConstants.API_ROLE_ALL_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }
    
    @Test
    public void RoleADDTest(){
    	Role r2 = new Role();
    	r2.setRemark("1");
    	r2.setCode("ROLE_222");
    	r2.setName("222");
    	r2.setCreateTime(new Date());
    	
    	ObjectRestResponse<Role> r1 = roleBiz.add(r2,ApiConstants.API_ROLE_ADD_URL);
    	System.out.println(JSON.toJSONString(r1));
    	//TestCase.assertEquals(1, r.getId());
    }

}
