/**
 * 
 */
package com.cloudpaas.admin.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.AdminApplication;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月28日 下午2:26:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class UserBizTest {
	@Autowired
	AdminUserBiz userBiz;
	
	@Test
	public void loginTest(){
		User t =new User();
		t.setUsername("admin");
		User user = userBiz.login(t, "dn0");
		System.out.println(JSON.toJSONString(user));
		
	}

}
