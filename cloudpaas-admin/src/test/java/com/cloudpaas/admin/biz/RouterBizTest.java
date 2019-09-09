/**
 * 
 */
package com.cloudpaas.admin.biz;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudpaas.admin.AdminApplication;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.gateway.model.Router;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午10:16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class RouterBizTest {

	@Autowired
	AdminRouterBiz routerBiz ;
	
	@Test
	public void addRouterTest(){
		Router r = new Router();
		r.setRouterId("cpaas-admin");
		r.setUri("lb://CPAAS-ADMIN");
		r.setRouterOrder(8000);
		r.setPath("admin");
		r.setEnabled(1);
		r.setStripPrefix("2");
		r.setCreateTime(new Date());
		routerBiz.insert(r, CommonConstants.DEFAULT_DATASOURCE_KEY);
	}
}
