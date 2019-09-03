/**
 * 
 */
package com.cloudpaas.service.pas.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cloudpaas.demo.model.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 大鱼
 *
 * @date 2019年9月3日 上午11:26:52
 */
@Slf4j
@Service
public class PasStudentBiz {

	@Autowired
    private MongoTemplate mongoTemplate;
	
	public void save(Student student) {
        mongoTemplate.save(student);
    }
	
	public void remove(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Student.class);
    }
	
	public void update(Student student) {
        Query query = new Query(Criteria.where("id").is(student.getId()));
        Update update = new Update();
        update.set("name", student.getName());
        update.set("age", student.getAge());
        update.set("gradeId", student.getGradeId());
        this.mongoTemplate.updateFirst(query, update, Student.class);
    }
	

    public Student findById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        Student one = this.mongoTemplate.findOne(query, Student.class);
        return one;
    }
    
    public List<Student> findAll(Integer page, Integer pageSize) {
        if (page < 0) {
            page = 0;
        }
        //多表进行关联
        LookupOperation lookupOperation = LookupOperation.newLookup().
                from("grade").//关联表名
                localField("gradeId").//关联字段
                foreignField("_id").//主表关联字段对应的次表字段
                as("grade");//查询结果集合名
        // 将条件封装到Aggregate管道
        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                Aggregation.project("id", "name", "age", "gradeId", "grade.gradeName"),//指定输出文档中的字段
                Aggregation.sort(Sort.Direction.ASC, "id"),//排序
                Aggregation.skip((page - 1) * pageSize),//过滤条数，跳过一定数量的数据
                Aggregation.limit(pageSize));//限制传递给下一步的文档数量
        
        

        log.info(aggregation.toString());
        //查询
        List<Student> students = mongoTemplate.aggregate(aggregation, "student", Student.class).getMappedResults();
        log.info(mongoTemplate.count(new Query(), Student.class)+"");
        return students;
    }


	
	


}
