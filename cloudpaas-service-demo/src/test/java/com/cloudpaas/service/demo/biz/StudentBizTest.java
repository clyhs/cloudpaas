/**
 * 
 */
package com.cloudpaas.service.demo.biz;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.demo.model.Grade;
import com.cloudpaas.demo.model.Student;
import com.cloudpaas.service.demo.DemoApplication;

import springfox.documentation.spring.web.json.Json;

/**
 * @author 大鱼
 *
 * @date 2019年9月3日 上午11:50:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentBizTest {

	@Autowired
	DemoStudentBiz stuBiz;
	
	@Test
	public void addStuTest(){
		Student stu = new Student();
	
		stu.setId(1l);
		stu.setName("test");
		stu.setGradeId(1);
		stu.setAge(12);
		stuBiz.save(stu);
		
	}
	@Test
	public void findAllStuTest(){
		List<Student> stus =  stuBiz.findAll(1, 20);
		//[{"age":12,"gradeId":1,"gradeName":"11111","id":1,"name":"test"}]
		System.out.println(JSON.toJSONString(stus));
	}
	
	@Test
	public void findByIdTest(){
		Student stu = stuBiz.findById(1l);
		System.out.println(JSON.toJSONString(stu));
		
	}
}
