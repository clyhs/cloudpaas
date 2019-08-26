/**
 * 
 */
package com.cloudpaas.common.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.common.base.biz.ABaseBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.common.utils.Query;

/**
 * @author 大鱼
 *
 * @date 2019年8月8日 下午6:40:59
 */
public abstract class BaseController<Dao extends ABaseBiz,T> {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Dao baseDao;
    
    @RequestMapping(value = "/add.json",method = RequestMethod.POST)
    @ResponseBody
    public ObjectResponse<T> add(@RequestBody T entity,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        baseDao.insertSelective(entity,db);
        return new ObjectResponse<T>();
    }
    
    @RequestMapping(value = "/all.json",method = RequestMethod.GET)
    @ResponseBody
    public PageResponse<T> all(@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        List<T> list = baseDao.selectListAll(db);
    	PageResponse<T> result = new PageResponse();
    	result.setData(list);
    	return result;
    }
    
    @RequestMapping(value = "/page.json",method = RequestMethod.GET)
    @ResponseBody
    public PageResponse<T> list(
    		/*
    		@RequestParam(name="page" ,required=false,defaultValue="1") Integer page,
    		@RequestParam(name="limit" ,required=false,defaultValue="20") Integer size,*/
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db,
    		@RequestParam Map<String, Object> params){
        //查询列表数据
    	log.info("---------------params:{}--------------",JSON.toJSONString(params));
        Query query = new Query(params);
        return baseDao.selectByQuery(query,db);
    }
    
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResponse<T> update(@RequestBody T entity,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        baseDao.updateSelectiveById(entity,db);
        return new ObjectResponse<T>();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse<T> remove(@PathVariable int id,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        baseDao.deleteById(id,db);
        return new ObjectResponse<T>();
    }
    
    @RequestMapping(value = "/deleteBatch.json",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse<T> deleteBatch(@RequestBody List<T> lists,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){    
        if(lists == null || lists.size()==0){
            return new ObjectResponse<T>();
        }
        for (T entity : lists){
        	baseDao.delete(entity, db);
        }
        return new ObjectResponse<T>();
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse<T> get(@PathVariable int id,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        ObjectResponse<T> entityObjectRestResponse = new ObjectResponse<>();
        Object o = baseDao.selectById(id,db);
        entityObjectRestResponse.data((T)o);
        return entityObjectRestResponse;
    }
    
    @RequestMapping(value = "/selectOne.json",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse<T> get(@RequestBody T entity,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        
    	ObjectResponse<T> entityObjectRestResponse = new ObjectResponse<>();
        Object o = baseDao.selectOne(entity, db);
        entityObjectRestResponse.data((T)o);
        return entityObjectRestResponse;
    }

}
