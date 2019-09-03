/**
 * 
 */
package com.cloudpaas.service.pas.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudpaas.demo.model.Grade;
import com.cloudpaas.service.pas.PasServiceApplication;



/**
 * @author 大鱼
 *
 * @date 2019年9月3日 上午11:41:02
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasServiceApplication.class)
public class GradeBizTest {
	
	@Autowired
	PasGradeBiz gradebiz;
	
	@Test
	public void addTest(){
		Grade g = new Grade();
		g.setId(1l);
		g.setGradeName("11111");
		gradebiz.save(g);
		
	}

}
