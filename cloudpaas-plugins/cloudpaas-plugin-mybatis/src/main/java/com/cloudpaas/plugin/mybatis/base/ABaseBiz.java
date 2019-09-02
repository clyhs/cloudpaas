/**
 * 
 */
package com.cloudpaas.plugin.mybatis.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.plugin.mybatis.datasource.DataSource;
import com.cloudpaas.plugin.mybatis.utils.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 大鱼
 *
 * @date 2019年7月26日 下午2:32:29
 */
public abstract class ABaseBiz<M extends Mapper<T>, T> {
	
	@Autowired
    protected M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity,@DataSource String db) {
        return mapper.selectOne(entity);
    }


    public T selectById(Object id,@DataSource String db) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity,@DataSource String db) {
        return mapper.select(entity);
    }


    public List<T> selectListAll(@DataSource String db) {
        return mapper.selectAll();
    }


    public Long selectCount(T entity,@DataSource String db) {
        return new Long(mapper.selectCount(entity));
    }


    public void insert(T entity,@DataSource String db) {
        mapper.insert(entity);
    }


    public void insertSelective(T entity,@DataSource String db) {
        mapper.insertSelective(entity);
    }


    public void delete(T entity,@DataSource String db) {
        mapper.delete(entity);
    }


    public void deleteById(Object id,@DataSource String db) {
        mapper.deleteByPrimaryKey(id);
    }


    public void updateById(T entity,@DataSource String db) {
        mapper.updateByPrimaryKey(entity);
    }


    public void updateSelectiveById(T entity,@DataSource String db) {
        mapper.updateByPrimaryKeySelective(entity);

    }

    public List<T> selectByExample(Object example,@DataSource String db) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example,@DataSource String db) {
        return mapper.selectCountByExample(example);
    }

    public PageResponse<T> selectByQuery(Query query,@DataSource String db) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if(query.entrySet().size()>0) {
            Example.Criteria criteria = example.createCriteria();
            
            for (Map.Entry<String, Object> entry : query.entrySet()) {
            	if(entry.getKey().equals("db")){
            		
            	}else{
            		criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            	}
                
            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = mapper.selectByExample(example);
        return new PageResponse<T>(result.getTotal(), list);
    }

}
