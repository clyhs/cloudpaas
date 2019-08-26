/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.AdminUIApplication;
import com.cloudpaas.admin.ui.base.BaseBiz;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.constants.CommonConstants;
import com.cloudpaas.admin.ui.utils.RestTemplateUtils;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectResponse;
import com.google.common.collect.Maps;



/**
 * @author 大鱼
 *
 * @date 2019年8月16日 上午9:52:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc  //测试接口用
public class UserBizTest extends BaseBiz<User>{
	
	
	
	@Autowired
    protected WebApplicationContext context;

	
	@Before
    public void testBefore(){
        System.out.println("测试前");   
    }

    @After
    public void testAfter(){
        System.out.println("测试后");   
    }

	@Test
	public void UserSelectOneTest(){
		ParameterizedTypeReference<ObjectResponse<User>> responseBodyType = new ParameterizedTypeReference<ObjectResponse<User>>() {};
		
		User user = new User();
		user.setUsername("admin");
		
		Map<String, Object> params = Maps.newHashMap();
		params.put("db", "dn2");
		HttpEntity<User> httpEntity = new HttpEntity<User>(user,getHttpHeaders());
	
		ObjectResponse<User> result = RestTemplateUtils.exchange(ApiConstants.API_USER_SELECTONE_URL+"?db={db}", 
				HttpMethod.GET, httpEntity, responseBodyType,params)
				.getBody();

		User entity =  result.getData();
		System.out.print(JSON.toJSONString(entity));
		
	}

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.BaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.ADMIN_SERVICE_ID;
	}
	

}
