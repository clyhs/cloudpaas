/**
 * 
 */
package com.cloudpaas.service.demo.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cloudpaas.demo.model.Grade;
import com.cloudpaas.demo.model.Student;

/**
 * @author 大鱼
 *
 * @date 2019年9月3日 上午11:39:27
 */
@Service
public class DemoGradeBiz {

	@Autowired
    private MongoTemplate mongoTemplate;
	
	public void save(Grade grade) {
        mongoTemplate.save(grade);
    }
}
