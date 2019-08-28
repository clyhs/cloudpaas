/**
 * 
 */
package com.cloudpaas.admin.biz;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudpaas.admin.AdminApplication;
import com.cloudpaas.admin.biz.AdminRoleBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.model.Role;

import junit.framework.TestCase;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 上午8:45:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class RoleDaoTest {
	
	@Autowired
	AdminRoleBiz ard;
	
	@Test
	public void selectAll(){
		List<Role> lists = ard.selectListAll(CommonConstants.DEFAULT_DATASOURCE_KEY);
		TestCase.assertTrue(lists.size()>0);
	}

}
